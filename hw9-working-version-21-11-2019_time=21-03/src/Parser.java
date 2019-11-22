import java.io.*;
import java.util.*;

public class Parser implements AutoCloseable {
    private BufferedReader reader;
    private BufferedWriter writer;
    private StringBuilder text;
    private StringBuilder result;
    private final Map markup = Map.<Integer, String>of(
            1, "em", 2, "strong", 3, "s", 4, "code", 5, "p"
    );
    private final Map specialCharsMap = Map.<Character, String>of(
            '<', "&lt;", '>', "&gt;", '&', "&amp;"
    );
    private final Set specialChars = Set.<Character>of(
            '<', '>', '&'
    );
    private final Set emphasisChars = Set.<Character> of(
            '_', '*'
    );
    private final Set strongChars = Set.<Character>of(
            '_', '*'
    );
    private final Set strikeoutChars = Set.<Character>of(
            '-'
    );
    private final Set codeChars = Set.<Character>of(
            '`'
    );
    private int ind = 0;
    private boolean wasProcessed = false;
    public Parser() {}
    Parser(String in, String out) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), "utf8"));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "utf8"));
        text = new StringBuilder();
        result = new StringBuilder();
    }

    private void read() throws IOException {
        while (reader.ready()) {
            String line = reader.readLine();
            text.append(line);
            text.append("\n");
        }
    }

    private String getParagraph () {
        while (ind < text.length() && text.charAt(ind) == '\n')
            ind++;
        int l = ind;
        int r = -1;
        while (l < text.length()) {
            int pos = l;
            while (pos < text.length() && text.charAt(pos) != '\n')
                pos++;
            if (text.charAt(pos) == '\n' && pos == r + 1) {
                break;
            } else if (text.charAt(pos) == '\n') {
                r = pos;
                l = pos + 1;
            }
        }
        if (r == -1) return "\n";
        String res = text.substring(ind, r);
        ind = r + 2;
        return res;
    }

    private int checkHeader(String s) {
        int ind = 0;
        int res = 0;
        while (ind < s.length() && s.charAt(ind) == '#') {
            ind++;
        }
        if (ind != 0 && ind < s.length() && s.charAt(ind) == ' ') {
            res = ind;
        }
        return res;
    }

    private boolean checkEmphasis(String s, int l) {
        /* return (l < s.length() && (s.charAt(l) == '*' || s.charAt(l) == '_') &&
                (!wasProcessed || (l >= 1 && s.charAt(l - 1) != '\\' &&
                        ((s.charAt(l - 1) != '_' && s.charAt(l - 1) != '*'))) || (l == 0))); */
        return (l < s.length() && emphasisChars.contains(s.charAt(l)) &&
                (!wasProcessed || (l >= 1 && s.charAt(l - 1) != '\\' &&
                        !emphasisChars.contains(s.charAt(l - 1))) || (l == 0)));
    }

    private boolean checkStrong(String s, int l) {
        /*return (l + 1 < s.length() && ((s.charAt(l) == '*' && s.charAt(l + 1) == '*') ||
                (s.charAt(l) == '_' && s.charAt(l + 1) == '_')) &&
                ((l >= 1 && s.charAt(l - 1) != '\\') || (l == 0)));*/
        return (l + 1 < s.length() && strongChars.contains(s.charAt(l)) &&
                strongChars.contains(s.charAt(l + 1)) && s.charAt(l + 1) == s.charAt(l) &&
                ((l >= 1 && s.charAt(l - 1) != '\\') || (l == 0)));
    }

    private boolean checkStrikeout(String s, int l) {
        /*return (l + 1 < s.length() && s.charAt(l) == '-' && s.charAt(l + 1) == '-' &&
                ((l >= 1 && s.charAt(l - 1) != '\\') || (l == 0)));*/
        return (l + 1 < s.length() && strikeoutChars.contains(s.charAt(l)) &&
                strikeoutChars.contains(s.charAt(l + 1)) && s.charAt(l) == s.charAt(l + 1) &&
                ((l >= 1 && s.charAt(l - 1) != '\\') || (l == 0)));
    }

    private boolean checkCode(String s, int l) {
        /*return (l < s.length() && s.charAt(l) == '`' &&
                ((l >= 1 && s.charAt(l - 1) != '\\') || (l == 0)));*/
        return (l < s.length() && codeChars.contains(s.charAt(l)) &&
                ((l >= 1 && s.charAt(l - 1) != '\\') || (l == 0)));
    }

    private int getType(String s, int l) {
        if (checkStrong(s, l))
            return 2;
        if (checkEmphasis(s, l))
            return 1;
        if (checkStrikeout(s, l))
            return 3;
        if (checkCode(s, l))
            return 4;
        return 0;
    }

    private void pushMarkup(int type, boolean state, int header) {
        result.append("<");
        if (!state) {
            result.append("/");
        }
        if (type == -228) {
            result.append("h");
            result.append(header);
        } else {
            result.append(markup.get(type));
        }
        result.append(">");
    }

    private int parseImage(String paragraph, int pos, int img[]) {
        int left = pos + 1;
        int right = img[pos + 1];
        left++;
        right--;
        String name = paragraph.substring(left, right + 1);
        int id = right + 3;
        int beg = id;
        while (paragraph.charAt(id) != ')')
            id++;
        id--;
        String link = paragraph.substring(beg, id + 1);
        result.append("<img alt='");
        result.append(name);
        result.append("' src='");
        result.append(link);
        result.append("'>");
        return id + 1;
    }

    private void parseParagraph(String paragraph) {
        int l = 0;
        int len = paragraph.length();
        Stack stack = new Stack<ParserPair>();
        List match = new ArrayList<ParserPair>();
        List revMatch = new ArrayList<ParserPair>();
        for (int i = 0; i < len; i++) {
            match.add(new ParserPair(-1, '0', -1));
            revMatch.add(new ParserPair(-1, '0', -1));
            int curSymType = getType(paragraph, i);
            if (curSymType == 0) {
                wasProcessed = false;
                continue;
            }
            if (!stack.isEmpty()) {
                ParserPair curPair = (ParserPair) stack.peek();
                int stackSymType = curPair.getType();
                if (curSymType == stackSymType && paragraph.charAt(curPair.getInd()) == paragraph.charAt(i)) {
                    match.set(curPair.getInd(), new ParserPair(i, curPair.getC(), curSymType));
                    revMatch.set(i, new ParserPair(curPair.getInd(), curPair.getC(), stackSymType));
                    wasProcessed = true;
                    stack.pop();
                } else {
                    stack.push(new ParserPair(i, paragraph.charAt(i), curSymType));
                    wasProcessed = true;
                }
            } else {
                stack.push(new ParserPair(i, paragraph.charAt(i), curSymType));
                wasProcessed = true;
            }
        }
        int headerLevel = checkHeader(paragraph);
        int offset = 0;
        if (headerLevel == 0) {
            pushMarkup(5, true, -1);
        } else {
            offset = 1;
        }

        if (headerLevel > 0) {  // write header
            pushMarkup(-228, true, headerLevel);
        }
        // System.out.println(paragraph + " : " + headerLevel);
        wasProcessed = false;
        int curSegmentType = -1;
        int img[] = new int[len];
        int last = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (paragraph.charAt(i) == '[') {
                img[i] = last;
                last = -1;
            }
            if (paragraph.charAt(i) == ']')
                last = i;
        }
        for (int i = headerLevel + offset; i < len; i++) {   // write String symbols
            int pos = i;
            ParserPair curMatchPair = (ParserPair) match.get(pos);
            ParserPair curRevMatchPair = (ParserPair) revMatch.get(pos);
            if (curMatchPair.getInd() != -1) {
                pushMarkup(curMatchPair.getType(), true, -1);
                curSegmentType = curMatchPair.getType();
                if (2 <= getType(paragraph, pos) && getType(paragraph, pos) <= 3) i++;
            } else if (curRevMatchPair.getInd() != -1) {
                pushMarkup(curRevMatchPair.getType(), false, -1);
                curSegmentType = -1;
                if (2 <= getType(paragraph, pos) && getType(paragraph, pos) <= 3) i++;
            } else {
                /*if (pos + 1 < len && pos >= 1 && specialChars.contains(paragraph.charAt(pos)) &&
                        codeChars.contains(paragraph.charAt(pos - 1)) &&
                          codeChars.contains(paragraph.charAt(pos + 1))) {*/
                if (specialChars.contains(paragraph.charAt(pos))) {
                    result.append(specialCharsMap.get(paragraph.charAt(pos)));
                } else if (pos + 1 < paragraph.length() && paragraph.charAt(pos) == '!' &&
                        paragraph.charAt(pos + 1) == '[' && img[pos + 1] != -1) {
                    int newI = parseImage(paragraph, pos, img);
                    i = newI;
                } else if (paragraph.charAt(pos) != '\\'){
                    result.append(paragraph.charAt(pos));
                }
            }
        }
        if (headerLevel > 0) {  // write header
            pushMarkup(-228, false, headerLevel);
        }
        if (headerLevel == 0) {
            pushMarkup(5, false, -1);
        }
    }

    void parse() throws IOException {
        read();
        while (ind < text.length()) {
            String paragraph = getParagraph();
            if (paragraph == "\n") {
                ind++;
                continue;
            }
            if (paragraph.isEmpty()) continue;
            parseParagraph(paragraph);
            result.append("\n");
        }
        System.out.println(result.toString());
        writer.write(result.toString());
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
    }
}

class ParserPair{
    private int ind, type;
    private char c;
    ParserPair(int ind, char c, int type) {
        this.ind = ind;
        this.c = c;
        this.type = type;
    }
    int getInd() {
        return this.ind;
    }
    char getC() {
        return this.c;
    }
    int getType() {
        return this.type;
    }
}
