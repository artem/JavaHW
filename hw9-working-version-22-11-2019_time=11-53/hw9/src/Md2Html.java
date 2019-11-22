import java.io.IOException;

public class Md2Html {

    public static void main(String[] args) {
        try (Parser kekParser = new Parser(args[0], args[1]); ) {
            kekParser.parse();
        } catch (IOException e) {
            System.out.println("Your Parser has been crashed :(");
        }
    }
}
