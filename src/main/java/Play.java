import java.util.Scanner;

public class Play {

    /*
    Declare color
    */
    private static final String COLOR_MAGENTA = "\u001B[35;1m";
    private static final String COLOR_CLEAN = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println("\n**********************************************************************" +
                COLOR_MAGENTA + "\nWillkommen bei Fabienne's Tic Tac Toe" +
                COLOR_CLEAN);
        System.out.println("\nRegeln: \nZiel des Spiels ist es drei seiner Zeichen in eine Reihe zu bekommen,\n" +
                "ob Diagonal, Senkrecht oder Waagrecht.\n" +
                "Die Felder werden mit der Eingabe der zugeordneten Zahl ausgewählt." +
                "\nJedes Feld kann nur einmal gewählt werden." +
                "\n-------------------------------------------------------------------" +
                "\nMit der Zahl '99' kann das Spiel jederzeit beendet werden." +
                "\n**********************************************************************");

        Scanner gegner = new Scanner(System.in);
        Scanner next = new Scanner(System.in);
        boolean round = true;

        while (round == true) {

            System.out.println("Gegner-Wahl:\n 1. 2 Spieler\n 2. Gegen den Computer");

            /*
               Selection opponent
             */
            int wahl = gegner.nextInt();
            switch (wahl) {

                /*
                  Call the respective class
                 */
                case 1:
                    FriendPlayer verbinden = new FriendPlayer();
                    verbinden.twoPlayer();
                    break;
                case 2:
                    Computer uebernehmen = new Computer();
                    uebernehmen.pcPlayer();
                    break;
                default:
                    System.out.println("Gegner-Wahl ungültig");
                    continue;

            }

            /*
              Ask if the game should be picked up again
             */
            System.out.println("Nochmal Spielen? j/n");
            String answer = next.next();
            round = answer.equalsIgnoreCase("j");
        }
        System.out.println("Sie haben das Spiel verlassen");
    }
}