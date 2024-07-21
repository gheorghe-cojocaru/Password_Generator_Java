import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {

        keyboard = scanner;
    }

    public  Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
        alphabet = new Alphabet(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
    }

    public void mainLoop() {
        System.out.println("Wilkommen zu GC Password Service ");
        printMenu();

        String userOption = "-1";

        while (!userOption.equals("4")) {

            userOption = keyboard.next();

            switch (userOption) {
                case "1" -> {
                    requestPassword();
                    printMenu();
                    break;
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                    break;
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                    break;
                }
                case "4" -> {
                    this.printQuitMessage();
                    break;
                }
                default -> {
                    System.out.println();
                    System.out.println("Bitte suche eine Option aus.");
                    this.printMenu();
                }

            }
        }

    }

    private Password GeneratePassword(int length) {
        final StringBuilder pass = new StringBuilder("");

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));

        }

        return new Password(pass.toString());
    }

    private void printUsefulInfo() {
        System.out.println();
        System.out.println("Dein Password muss mindestens 8 Zeichen lang sein. ");
        System.out.println("Dein Password muss Grossbuchstaben, Kleinbuchstaben, Zahlen und Sonderzeichen enthalten.");
        System.out.println("Nach Möglichkeit wird das Passwort nach Zufallprinzip generiert.");
        System.out.println("Vermeiden Sie bitte doppelte Verwendung eines Passwords.");
        System.out.println("Vermeiden Sie bitte Zeichenwiederholungen, Tastaturmuster, Wörterbücher, Buchstaben oder Zahlenfolgen" + "\nBenutzernamen, Verwandtennamen" + " und biografische Informationen(z.B. ID-Nummern, Namen).");
        System.out.println("Vermeiden Sie bitte die Verwendung von Informationen, die mit anderen Personen in Verbindung stehen.");
        System.out.println("Verwenden Sie keine Passwörter, die ausschliesslich aus einer einfachen Kombination der oben genanten schwachen Komponenten bestehen.");


    }




    private void requestPassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Hallo, willkommen beim Passwortgenerator :) Antworte die folgenede Fragen mit Ja oder Nein \n" );

        do {
            String input;
            correctParams = false;
            do {
                System.out.println("Möchtest du dass auch Grossbuchstaben benutzt werden? ");
                input = keyboard.next();
                PasswortRequestError(input);
            } while(!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nein"));

            if (this.isInclude(input)) {
                IncludeUpper = true;
            }
            do {
                System.out.println("Möchtest du dass auch Kleinbuchstaben benutzt werden? ");
                input = keyboard.next();
                PasswortRequestError(input);
            } while(!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nein"));

            if (this.isInclude(input)) {
                IncludeLower = true;
            }

            do {
                System.out.println("Möchtest du dass auch Zahlen benutzt werden? ");
                input = keyboard.next();
                PasswortRequestError(input);
            } while(!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nein"));

            if (this.isInclude(input)) {
                IncludeNum = true;
            }

            do {
                System.out.println("Möchtest du dass auch Sonderzeichen benutzt werden? ");
                input = keyboard.next();
                this.PasswortRequestError(input);
            } while (!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nein"));

            if (this.isInclude(input)) {
                IncludeSym = true;
            }

            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("Du musst mindestens eine Wahl treffen um das Password generiert werden zu können.");
                correctParams = true;
            }
        } while (correctParams);

        System.out.println("Super! Jetzt gebe die Länge deines Passwords ein: ");
        int length = keyboard.nextInt();
        Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        Password password = generator.GeneratePassword(length);
        System.err.println("Dein generiertes Passwort ist: " + password);

    }

    private boolean isInclude(String Input) { return Input.equalsIgnoreCase("yes");}

    private void PasswortRequestError(String i) {
        if (!i.equalsIgnoreCase("yes") && !i.equalsIgnoreCase("no")) {
            System.out.println("Du hast etwas anderes eingegeben. Versuche es nochmal. ");
        }
    }
    private void checkPassword() {
        System.out.println("Gebe dein Password ein: ");
        String input = keyboard.next();
        Password p = new Password(input);
        System.out.println(p.CalculateScore());

    }

    private void printMenu() {

        System.out.println();
        System.out.println("Drucke 1 - für um ein Passwort zu generieren.");
        System.out.println("Drucke 2 - um dein Password zu prüfen.");
        System.out.println("Drucke 3 - für nützlische Info.");
        System.out.println("Drucke 4 - für Schliessen");
        System.out.println("Deine Wahl: ");

    }

    private void printQuitMessage() {
        System.out.println("Das Program schliesst. Tschüss.");;}

}
