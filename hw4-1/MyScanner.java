import java.io.*;
import java.lang.*;

public class MyScanner implements AutoCloseable{
    private Reader stream;
    private String nameOfCharSet = "utf8";
    private String token = "";
    private boolean endFound = false;
    private int mode = -1;
    private int wl = -1, wr = -1;
    private String line = "";
    public MyScanner(InputStream io, String anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new InputStreamReader(io, nameOfCharSet);
    }
    public MyScanner(InputStream io) throws IOException {
        stream = new InputStreamReader(io, nameOfCharSet);
    }

    public MyScanner(File io, String anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new InputStreamReader(new FileInputStream(io), nameOfCharSet);
    }
    public MyScanner(File io) throws IOException {
        stream = new InputStreamReader(new FileInputStream(io), nameOfCharSet);
    }

    public MyScanner(String io, String anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new InputStreamReader(new StringBufferInputStream(io), nameOfCharSet);
    }
    public MyScanner(String io) throws IOException {
        stream = new InputStreamReader(new StringBufferInputStream(io), nameOfCharSet);
    }

    public void close() throws IOException {
        stream.close();
    }

    private int read() throws IOException {
        return stream.read();
    }

    private boolean checkChar(char x) {
        return !Character.isWhitespace(x);
    }
    public void setMode(int x) {
        mode = x;
    }
    public boolean checkLine(char x) {
        return !(Character.getType(x) == Character.CONTROL);
    }
    private void getToken() throws IOException {
        if (!token.isEmpty()) {
            return ;
        }
        StringBuilder str = new StringBuilder();
        int x = -1;
        boolean tokenCollected = false;
        if (mode == 2) tokenCollected = true;
        while (true) {
            x = read();
            if (x == -1) {
                break;
            }
            if ((mode == 1 && !checkChar((char) x)) || (mode == 0 && !charIsOk((char) x)) || (mode == 2 && !checkLine((char) x))) {
                if (tokenCollected) {
                    break;
                }
            } else {
                str.append((char)x);
                tokenCollected = true;
            }
        }
        token = str.toString();
    }
    public boolean hasNext() throws IOException {
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
    public boolean charIsOk(char c) {
        if (Character.isLetter(c) ||
                Character.getType(c) == Character.DASH_PUNCTUATION
                || c == '\'') {
            return true;
        } else {
            return false;
        }
    }
    public void getLine() throws IOException {
        if (line.length() > 0) {
            return ;
        }
        StringBuilder str = new StringBuilder();
        int x = -1;
        while(true) {
            x = read();
            if (x == -1) {
                break;
            }
            if (Character.getType((char)x) == Character.CONTROL) {
                break;
            }
            str.append((char) x);
        }
        if (str.length() == 0 && x != -1)  {
            str.append((char) x);
        }
        line = str.toString();
    }
    public boolean hasNextLine() throws IOException {
        getLine();
        return (!line.isEmpty());
    }
    public String nextLine() throws IOException {
        if (hasNextLine()) {
            String res = line;
            line = "";
            return res;
        } else {
            throw new IOException("eof");
        }
    }
}
