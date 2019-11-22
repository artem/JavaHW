import java.io.*;
import java.lang.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MyScanner implements AutoCloseable{
    private Reader stream;
    private Charset nameOfCharSet = StandardCharsets.UTF_8;
    private String token = "";
    private int mode = 1;
    private char[] buffer = new char[1024];
    private int pos = -1;
    private int lenOfBuf = 0;
    private boolean endFound = false;
    public MyScanner(InputStream io, Charset anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new InputStreamReader(io, nameOfCharSet);
        //stream = new BufferedReader(new InputStreamReader(io,  nameOfCharSet));
    }
    public MyScanner(InputStream io) throws IOException {
        stream = new InputStreamReader(io, nameOfCharSet);
        //stream = new BufferedReader(new InputStreamReader(io,  nameOfCharSet));
    }

    public MyScanner(File io, Charset anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new InputStreamReader(new FileInputStream(io), nameOfCharSet);
        //stream = new BufferedReader(new InputStreamReader(new FileInputStream(io), nameOfCharSet));
    }
    public MyScanner(File io) throws IOException {
        stream = new InputStreamReader(new FileInputStream(io), nameOfCharSet);
        //stream = new BufferedReader(new InputStreamReader(new FileInputStream(io), nameOfCharSet));
    }

    /*public MyScanner(String io, Charset anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new StringReader(io);
        //stream = new InputStreamReader(new StringBufferInputStream(io), nameOfCharSet);
        //stream = new BufferedReader(new InputStreamReader(new StringBufferInputStream(io), nameOfCharSet));
    }*/
    public MyScanner(String io) throws IOException {
        stream = new StringReader(io);
        //stream = new InputStreamReader(new StringBufferInputStream(io), nameOfCharSet);
        //stream = new BufferedReader(new InputStreamReader(new StringBufferInputStream(io), nameOfCharSet));
    }

    public void close() throws IOException {
        stream.close();
    }

    private int read() throws IOException {
        if (endFound) {
            return -1;
        }
        if (pos == lenOfBuf) {
            pos = -1;
        }
        if (pos == -1) {
            lenOfBuf = stream.read(buffer, 0, 1024);
            if (lenOfBuf == -1) {
                endFound = true;
                return -1;
            } else {
                pos = 0;
                return buffer[pos++];
            }
        } else {
            return buffer[pos++];
        }
        //return stream.read();
    }
    public void setMode(int x) {
        mode = x;
    }
    private boolean checkLine(char x) {
        //return !(Character.getType(x) == Character.CONTROL);
        return (x != '\n');
    }
    private boolean charIsOk(char c) {
        if (Character.isLetter(c) ||
                Character.getType(c) == Character.DASH_PUNCTUATION
                || c == '\'') {
            return true;
        } else {
            return false;
        }
    }
    private boolean checkChar(char x) {
        return !Character.isWhitespace(x);
    }
    private boolean chk(char x) {
        if (mode == 0) { // words
            return charIsOk(x);
        } else if (mode == 1) { // tokens
            return checkChar(x);
        } else if (mode == 2) { // line
            return checkLine(x);
        } else {
            return true;
        }
    }
    private void getToken() throws IOException {
        if (!token.isEmpty()) {
            return ;
        }
        StringBuilder str = new StringBuilder();
        int x = -1;
        boolean tokenCollected = false;
        if (mode == 2) {
            tokenCollected = true;
        }
        while (true) {
            x = read();
            if (x == -1) {
                break;
            }
            if (!chk((char) x)) {
                if (tokenCollected) {
                    break;
                }
            } else {
                str.append((char) x);
                tokenCollected = true;
            }
        }
        if (mode == 2 && str.length() == 0 && x != -1)  {
            str.append((char) x);
        }
        token = str.toString();
    }
    public boolean hasNextWord() throws IOException {
        mode = 0;
        getToken();
        return !token.isEmpty();
    }
    public boolean hasNext() throws IOException {
        mode = 1;
        getToken();
        return !token.isEmpty();
    }
    public String next() throws IOException {
        if (!hasNext()) {
            throw new IOException("eof");
        } else {
            String res = token;
            token = "";
            return res;
        }
    }
    public String nextWord() throws IOException {
        if (!hasNextWord()) {
            throw new IOException("eof");
        } else {
            String res = token;
            token = "";
            return res;
        }
    }
    public int nextInt() throws IOException {
        if (hasNextInt()) {
            return Integer.parseInt(next());
        } else {
            throw new IOException("eof");
        }
    }
    public boolean hasNextInt() throws IOException {
        if (!hasNext()) {
            return false;
        }
        try {
            if (hasNext()) {
                Integer.parseInt(token);
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public boolean hasNextLine() throws IOException {
        mode = 2;
        getToken();
        return (!token.isEmpty());
    }
    public String nextLine() throws IOException {
        if (hasNextLine()) {
            String res = token;
            token = "";
            return res;
        } else {
            throw new IOException("eof");
        }
    }
}
