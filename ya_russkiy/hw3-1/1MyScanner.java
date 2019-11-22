import java.io.*;
import java.util.*;
import java.lang.*;

public class MyScanner implements AutoCloseable{
    private Reader stream;
    private final static int LEN = 1024;
    private char[] buffer = new char[LEN];
    private int pos = 0, endOfBuffer;
    private String nameOfCharSet = "utf8";
    private boolean actualPos = true;
    public MyScanner(InputStream io, String anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new InputStreamReader(io, nameOfCharSet);
        reReadBuffer();
    }
    public MyScanner(InputStream io) throws IOException {
        stream = new InputStreamReader(io, nameOfCharSet);
        reReadBuffer();
    }

    public MyScanner(File io, String anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new InputStreamReader(new FileInputStream(io), nameOfCharSet);
        reReadBuffer();
    }
    public MyScanner(File io) throws IOException {
        stream = new InputStreamReader(new FileInputStream(io), nameOfCharSet);
        reReadBuffer();
    }

    public MyScanner(String io, String anotherNameOfCharSet) throws IOException {
        nameOfCharSet = anotherNameOfCharSet;
        stream = new InputStreamReader(new StringBufferInputStream(io), nameOfCharSet);
        reReadBuffer();
    }
    public MyScanner(String io) throws IOException {
        stream = new InputStreamReader(new StringBufferInputStream(io), nameOfCharSet);
        reReadBuffer();
    }

    private int reReadBuffer() throws IOException {
        int prevValueOfpos = pos;
        //System.out.println("READ = " + stream.read(buffer, 0, LEN));
        endOfBuffer = stream.read(buffer, 0, LEN);
        actualPos = false;
        return prevValueOfpos;
    }
    public void close() throws IOException {
        stream.close();
    }

    public void print() {
        for (int i = 0; i < LEN; i++) {
            System.out.print(buffer[i]);
        }
    }
    private int skipSequence(int constGroupNum, boolean negative) {
        while(pos < endOfBuffer && ((!negative && Character.getType(buffer[pos]) == constGroupNum)
                || (negative && Character.getType(buffer[pos]) != constGroupNum))) {
            pos++;
        }
        return pos;
    }
    public void updatePos() {
        if (!actualPos) {
            pos = 0;
            actualPos = true;
        }
    }
    public String next() throws IOException {
        updatePos();
        if (pos == endOfBuffer) {
            if (reReadBuffer() == -1) {
                return null;
            }
            //pos = 0;
        }
        int beg = pos;
        skipSequence(Character.DIRECTIONALITY_WHITESPACE, false);
        StringBuilder res = new StringBuilder("");
        while(pos == endOfBuffer) {
            if (reReadBuffer() == -1) {
                break;
            }
            //pos = 0;
            updatePos();
            skipSequence(Character.DIRECTIONALITY_WHITESPACE, false);
        }
        beg = pos;
        skipSequence(Character.DIRECTIONALITY_WHITESPACE, true);
        while(pos == endOfBuffer) {
            res.append(new String(buffer, beg, endOfBuffer - beg));
            if (reReadBuffer() == -1) {
                break;
            }
            //pos = 0;
            beg = 0;
            updatePos();
            skipSequence(Character.DIRECTIONALITY_WHITESPACE, true);
        }
        res.append(new String(buffer, beg, pos - beg));
        return new String(res);
    }
    public String nextLine() throws IOException {
        updatePos();
        if (pos == endOfBuffer) {
            if (reReadBuffer() == -1) {
                return null;
            }
            //pos = 0;
        }
        int beg = pos;
        StringBuilder res = new StringBuilder("");
        skipSequence(Character.CONTROL, true);
        while (pos == endOfBuffer) {
            res.append(new String(buffer, beg, endOfBuffer - beg));
            if (reReadBuffer() == -1) {
                break;
            }
            beg = 0;
            //pos = 0;
            updatePos();
            skipSequence(Character.CONTROL, true);
        }
        res.append(new String(buffer, beg, pos - beg));
        skipSequence(Character.CONTROL, false);
        if (new String(res).equals("")) {
            return null;
        } else {
            return new String(res);
        }
    }
    public int nextInt() throws IOException {
        return Integer.valueOf(next());
    }
    /*public boolean nextInt(Integer x) throws IOException {
        try {
            x = Integer.valueOf(next());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }*/
    public boolean hasNext() {
        //skipWhitespaces();
        //skipSequence(Character.DIRECTIONALITY_WHITESPACE, false);
        return (endOfBuffer > pos);
    }
    public String nextWord() throws IOException {
        return next();
    }
}
