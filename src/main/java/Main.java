import java.util.Scanner;

public class Main {

    public static final Scanner keyboard;

    public Main() {

    }

    public static void main(String[] args) {
        Generator generator = new Generator(keyboard);
        generator.mainLoop();
        keyboard.close();
    }

    static {
        keyboard = new Scanner(System.in);
    }
}