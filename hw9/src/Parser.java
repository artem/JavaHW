import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Parser implements AutoCloseable {
    private BufferedReader reader;
    private BufferedWriter writer;
    private StringBuilder text;
    private StringBuilder result;
    private final Map markup = Map.of(
            1, "em", 2, "strong", 3, "s", 4, "code", 5, "p"
    );
    private final Map specialCharsMap = Map.of(
            '<', "&lt;", '>', "&gt;", '&', "&amp;"
    );
    private final Set specialChars = Set.of(
            '<', '>', '&'
    );
    private final Set emphasisChars = Set.of(
            '_', '*'
    );
    private final Set strongChars = Set.of(
            '_', '*'
    );
    private final Set strikeoutChars = Set.of(
            '-'
    );
    private final Set codeChars = Set.of(
            '`'
    );
    private int ind = 0;
    private boolean wasProcessed = false;
    Parser(String in, String out) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8));
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8));
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
        while (ind < text.length() && text.charAt(ind) == '\n') {
            ind++;
        }
        int l = ind;
        int r = -1;
        while (l < text.length()) {
            int pos = l;
            while (pos < text.length() && text.charAt(pos) != '\n') {
                pos++;
            }
            if (text.charAt(pos) == '\n' && pos == r + 1) {
                break;
            } else if (text.charAt(pos) == '\n') {
                r = pos;
                l = pos + 1;
            }
        }
        if (r == -1) {
            return "\n";
        }
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

    private boolean checkElement(String s, int l, int addition, Set<Character> set, boolean processed, boolean em) {
        return (l + addition < s.length() && set.contains(s.charAt(l))  &&
                set.contains(s.charAt(l + addition)) && s.charAt(l + addition) == s.charAt(l) &&
                ((!processed || !wasProcessed) ||
                        ((l >= 1 && s.charAt(l - 1) != '\\') && (!em || !set.contains(s.charAt(l - 1))) || (l == 0))));
    }

    private boolean checkEmphasis(String s, int l) {
        return checkElement(s, l, 0, emphasisChars, true, true);
    }

    private boolean checkStrong(String s, int l) {
        return checkElement(s, l, 1, strongChars, false, false);
    }

    private boolean checkStrikeout(String s, int l) {
        return checkElement(s, l, 1, strikeoutChars, false, false);
    }

    private boolean checkCode(String s, int l) {
        return checkElement(s, l, 0, codeChars, false, false);
    }

    private int getType(String s, int l) {
        if (checkStrong(s, l)) {
            return 2;
        } else if (checkEmphasis(s, l)) {
            return 1;
        } else if (checkStrikeout(s, l)) {
            return 3;
        } else if (checkCode(s, l)) {
            return 4;
        } else {
            return 0;
        }
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

    private int parseImage(String paragraph, int pos, int[] img) {
        int left = pos + 2;
        int right = img[pos + 1] - 1;
        String name = paragraph.substring(left, right + 1);
        int id = right + 3;
        int beg = id;
        while (paragraph.charAt(id) != ')') {
            id++;
        }
        String link = paragraph.substring(beg, id);
        result.append("<img alt='");
        result.append(name);
        result.append("' src='");
        result.append(link);
        result.append("'>");
        return id;
    }

    private void parseParagraph(String paragraph) {
        wasProcessed = false;
        int len = paragraph.length();
        Deque <ParserPair> stack = new ArrayDeque<>();
        List <ParserPair> match = new ArrayList<>();
        List <ParserPair> revMatch = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            match.add(new ParserPair(-1, '0', -1));
            revMatch.add(new ParserPair(-1, '0', -1));
            int curSymType = getType(paragraph, i);
            if (curSymType == 0) {
                wasProcessed = false;
                continue;
            }
            if (!stack.isEmpty()) {
                ParserPair curPair = stack.peek();
                int stackSymType = curPair.getType();
                if (curSymType == stackSymType && paragraph.charAt(curPair.getInd()) == paragraph.charAt(i)) {
                    match.set(curPair.getInd(), new ParserPair(i, curPair.getC(), curSymType));
                    revMatch.set(i, new ParserPair(curPair.getInd(), curPair.getC(), stackSymType));
                    stack.pop();
                } else {
                    stack.push(new ParserPair(i, paragraph.charAt(i), curSymType));
                }
            } else {
                stack.push(new ParserPair(i, paragraph.charAt(i), curSymType));
            }
            wasProcessed = true;
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
        wasProcessed = false;
        int[] img = new int[len];
        int last = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (paragraph.charAt(i) == '[') {
                img[i] = last;
                last = -1;
            }
            if (paragraph.charAt(i) == ']') {
                last = i;
            }
        }
        for (int i = headerLevel + offset; i < len; i++) {   // write String symbols
            int pos = i;
            ParserPair curMatchPair = match.get(pos);
            ParserPair curRevMatchPair = revMatch.get(pos);
            boolean matchFlag = false;
            int matchType = curRevMatchPair.getType();
            if (curMatchPair.getInd() != -1) {
                matchFlag = true;
                matchType = curMatchPair.getType();
            }
            if (curMatchPair.getInd() != -1 || curRevMatchPair.getInd() != -1) {
                pushMarkup(matchType, matchFlag, -1);
                if (2 <= getType(paragraph, pos) && getType(paragraph, pos) <= 3) {
                    i++;
                }
            } else  {
                if (specialChars.contains(paragraph.charAt(pos))) {
                    result.append(specialCharsMap.get(paragraph.charAt(pos)));
                } else if (pos + 1 < paragraph.length() && paragraph.charAt(pos) == '!' &&
                        paragraph.charAt(pos + 1) == '[' && img[pos + 1] != -1) {
                    i = parseImage(paragraph, pos, img);
                } else if (paragraph.charAt(pos) != '\\') {
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
            if (paragraph.isEmpty()) {
                continue;
            }
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

class ParserPair {
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
