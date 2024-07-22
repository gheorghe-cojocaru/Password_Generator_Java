import java.util.Scanner;

public class Generator {
    Alphabet alphabet;
    public static Scanner keyboard;

    public Generator(Scanner scanner) {
        keyboard = scanner;
    }

    public Generator(boolean IncludeUpper, boolean IncludeLower, boolean IncludeNum, boolean IncludeSym) {
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
                }
                case "2" -> {
                    checkPassword();
                    printMenu();
                }
                case "3" -> {
                    printUsefulInfo();
                    printMenu();
                }
                case "4" -> {
                    this.printQuitMessage();
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
        System.out.println("Dein Passwort muss mindestens 8 Zeichen lang sein.");
        System.out.println("Dein Passwort muss Großbuchstaben, Kleinbuchstaben, Zahlen und Sonderzeichen enthalten.");
        System.out.println("Nach Möglichkeit wird das Passwort nach Zufallsprinzip generiert.");
        System.out.println("Vermeiden Sie bitte doppelte Verwendung eines Passworts.");
        System.out.println("Vermeiden Sie bitte Zeichenwiederholungen, Tastaturmuster, Wörterbuchwörter, Buchstaben- oder Zahlenfolgen, Benutzernamen, Verwandtennamen und biografische Informationen (z.B. ID-Nummern, Namen).");
        System.out.println("Vermeiden Sie bitte die Verwendung von Informationen, die mit anderen Personen in Verbindung stehen.");
        System.out.println("Verwenden Sie keine Passwörter, die ausschließlich aus einer einfachen Kombination der oben genannten schwachen Komponenten bestehen.");
    }

    private void requestPassword() {
        boolean IncludeUpper = false;
        boolean IncludeLower = false;
        boolean IncludeNum = false;
        boolean IncludeSym = false;

        boolean correctParams;

        System.out.println();
        System.out.println("Hallo, willkommen beim Passwortgenerator :) Antworte die folgenden Fragen mit Ja oder Nein\n");

        do {
            correctParams = false;
            String input;

            // Abfrage für Großbuchstaben
            do {
                System.out.println("Möchtest du, dass auch Großbuchstaben benutzt werden? ");
                input = keyboard.next();
                PasswortRequestError(input);
            } while (!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nein"));

            if (this.isInclude(input)) {
                IncludeUpper = true;
            }

            // Abfrage für Kleinbuchstaben
            do {
                System.out.println("Möchtest du, dass auch Kleinbuchstaben benutzt werden? ");
                input = keyboard.next();
                PasswortRequestError(input);
            } while (!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nein"));

            if (this.isInclude(input)) {
                IncludeLower = true;
            }

            // Abfrage für Zahlen
            do {
                System.out.println("Möchtest du, dass auch Zahlen benutzt werden? ");
                input = keyboard.next();
                PasswortRequestError(input);
            } while (!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nein"));

            if (this.isInclude(input)) {
                IncludeNum = true;
            }

            // Abfrage für Sonderzeichen
            do {
                System.out.println("Möchtest du, dass auch Sonderzeichen benutzt werden? ");
                input = keyboard.next();
                this.PasswortRequestError(input);
            } while (!input.equalsIgnoreCase("ja") && !input.equalsIgnoreCase("nein"));

            if (this.isInclude(input)) {
                IncludeSym = true;
            }

            if (!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
                System.out.println("Du musst mindestens eine Wahl treffen, um das Passwort generiert werden zu können.");
                correctParams = true;
            }
        } while (correctParams);

        // Abfrage für Passwortlänge
        System.out.println("Super! Jetzt gib die Länge deines Passworts ein: ");
        while (!keyboard.hasNextInt()) {
            System.out.println("Bitte gib eine gültige Zahl ein.");
            keyboard.next(); // Konsumiert die ungültige Eingabe
        }
        int length = keyboard.nextInt();
        while (length < 8) {
            System.out.println("Die Passwortlänge muss mindestens 8 Zeichen lang sein. Bitte gib eine neue Länge ein: ");
            while (!keyboard.hasNextInt()) {
                System.out.println("Bitte gib eine gültige Zahl ein.");
                keyboard.next(); // Konsumiert die ungültige Eingabe
            }
            length = keyboard.nextInt();
        }

        Generator generator = new Generator(IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
        Password password = generator.GeneratePassword(length);
        System.err.println("Dein generiertes Passwort ist: " + password);
    }

    private boolean isInclude(String Input) {
        return Input.equalsIgnoreCase("ja");
    }

    private void PasswortRequestError(String i) {
        if (!i.equalsIgnoreCase("ja") && !i.equalsIgnoreCase("nein")) {
            System.out.println("Du hast etwas anderes eingegeben. Versuche es nochmal.");
        }
    }

    private void checkPassword() {
        try {
            System.out.println("Gib dein Passwort ein: ");
            String input = keyboard.next();
            Password p = new Password(input);
            System.out.println(p.CalculateScore());
        } catch (Exception e) {
            System.out.println("Ein Fehler ist aufgetreten. Bitte versuche es erneut.");
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Drücke 1 - um ein Passwort zu generieren.");
        System.out.println("Drücke 2 - um dein Passwort zu prüfen.");
        System.out.println("Drücke 3 - für nützliche Informationen.");
        System.out.println("Drücke 4 - zum Schließen.");
        System.out.println("Deine Wahl: ");
    }

    private void printQuitMessage() {
        System.out.println("Das Programm schließt. Tschüss.");
    }
}
