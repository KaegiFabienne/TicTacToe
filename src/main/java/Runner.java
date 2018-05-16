import java.util.Scanner;

/* ***************************
   Programm:    Tic Tac Toe
   Author:      Fabienne Kägi
   Schule:      Benedict Zürich IT1B
   Datum:       Mai 2018
   ***************************
 */
public class Runner {

    public static void main(String[] args) {

        /*
          Defined variables.
         */
        String[] board = initBoard();
        boolean keepPlayingTheGame = true;
        int turn = 1;
        Player actPlayer = Player.A;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n**************************************" +
                "\nWillkommen bei Fabienne's Tic Tac Toe" +
                "\n**************************************");
        System.out.println("\nRegeln: \nZiel des Spiels ist es drei seiner Zeichen in eine Reihe zu bekommen,\n" +
                "ob Diagonal, Senkrecht oder Waagrecht.\n" +
                "Die Felder werden mit der Eingabe der zugeordneten Zahl ausgewählt." +
                "\nJedes Feld kann nur einmal gewählt werden." +
                "\n-------------------------------------------------------------------" +
                "\nMit der Zahl '99' kann das Spiel jederzeit beendet werden." +
                "\n\nViel Spass;D");

        /*
          Board output.
         */
        System.out.println("Spielfeld:");
        printBoard(board);

        while (keepPlayingTheGame) {

            /*
              Which player's turn.
             */
            if (turn % 2 == 0) {
                actPlayer = Player.B;
            } else {
                actPlayer = Player.A;
            }

            /*
              Read the user input.
             */
            String userInputString = readUserInput(actPlayer, scanner);
            int userInput = 0;
            try {
                userInput = Integer.valueOf(userInputString);
            } catch (NumberFormatException e) {
                System.out.println("FEHLER: Eingabe muss eine Zahl von 1-9 sein und nicht: " + userInputString);
                continue;
            }

            /*
              Play or end the game.
             */
            keepPlayingTheGame = isExitOfTheGame(userInput);
            if (!keepPlayingTheGame) {
                break;
            }

            /*
              Check if input in the range of 1-9, otherwise 'continue'.
             */
            if (!FieldsOfTheBoard.contains(userInputString)) {
                System.out.println("Oppala, Eingabe ausserhalb des Ranges [1-9]. Versuch es noch einmal.");
                continue;
            }

            /*
               Check if field has already been set. if yes then a 'continue',
               otherwise the respective train will be issued.
             */
            if (board[userInput - 1].equals(userInputString)) {
                board[userInput - 1] = actPlayer.value;
            } else {
                System.out.println("Ups,  Feld [" + userInputString + "] wurde bereits gesetzt. Nimm ein anderes Feld.");
                continue;
            }

            /*
              Actual board output.
             */
            printBoard(board);

            /*
              Winner output.
             */
            if (checkWinner(board, actPlayer)) {
                System.out.println("\nBravo, Spieler " + actPlayer.name() + " (" + actPlayer.value + ") hat gewonnen.;D");
                break;
            } else if (turn == 9) {
                System.out.println("\nUnentschieden...");
                break;
            }
            turn++;
        }
    }

    /*
      Exit game by specific user input (EXIT_CODE = 99).

      @param userInput numerical user input value
      @return keep playing
     */
    private static boolean isExitOfTheGame(int userInput) {
        int EXIT_CODE = 99;
        boolean keepPlaying = true;
        if (userInput == EXIT_CODE) {
            System.out.println("Oh je, Spiel verlassen....:/ ");
            keepPlaying = false;
        }
        return keepPlaying;
    }

    /*
      Game board is initially felt.
     */
    private static String[] initBoard() {
        int arraySize = 9;
        String[] board = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            board[i] = String.valueOf(i + 1);
        }
        return board;
    }

    /*
      Structure of the game board.

      @param board board with all fields
     */
    private static void printBoard(String[] board) {
        System.out.println("-------------");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("-------------");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("-------------");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("-------------");
    }

    /*
      Reads user entered input value.

      @param actPlayer actual player
      @return string of validated user input
     */
    private static String readUserInput(Player actPlayer, Scanner scanner) {
        System.out.println("Spieler " + actPlayer.name() + " (" + actPlayer.value + "): Wähle ein Feld: ");
        return scanner.nextLine();
    }

    /*
      Check if the given player has won or not.

      @param board  Play board
      @param player enum based player
      @return true if player has won.
     */
    private static boolean checkWinner(String[] board, Player player) {
        boolean retVal = false;
        // may be switch instead of.....
        if (((board[0].equals(player.value)) && (board[1]).equals(player.value)) && (board[2].equals(player.value)) ||
                ((board[3].equals(player.value)) && (board[4].equals(player.value)) && (board[5].equals(player.value))) ||
                ((board[6].equals(player.value)) && (board[7].equals(player.value)) && (board[8].equals(player.value))) ||
                ((board[0].equals(player.value)) && (board[3].equals(player.value)) && (board[6].equals(player.value))) ||
                ((board[1].equals(player.value)) && (board[4].equals(player.value)) && (board[7].equals(player.value))) ||
                ((board[2].equals(player.value)) && (board[5].equals(player.value)) && (board[8].equals(player.value))) ||
                ((board[0].equals(player.value)) && (board[4].equals(player.value)) && (board[8].equals(player.value))) ||
                ((board[2].equals(player.value)) && (board[4].equals(player.value)) && (board[6].equals(player.value)))
                ) {
            retVal = true;
        }
        return retVal;
    }

    /*
      Contains all defined players.
      Each player has a specific toggle e.g. player A has a X toggle.
     */
    private enum Player {
        A("X"), B("O");

        private final String value;

        Player(String value) {
            this.value = value;
        }
    /*
        public String getValue() {
            return this.value;
        }
        */
    }

    /*
      Definition of what numbers are allowed.
     */
    private enum FieldsOfTheBoard {
        ONE(1), TWO(2), THREE(3),
        FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9);

        private final int fieldValue;

        FieldsOfTheBoard(int fieldValue) {
            this.fieldValue = fieldValue;
        }

        private static boolean contains(String fieldValue) {
            for (FieldsOfTheBoard fieldsOfTheBoard : FieldsOfTheBoard.values()) {
                if (fieldsOfTheBoard.fieldValue == Integer.valueOf(fieldValue)) {
                    return true;
                }
            }
            return false;
        }

// --Commented out by Inspection START (16.05.2018 11:07):
//        public int fieldValue() {
//            return fieldValue;
//        }
// --Commented out by Inspection STOP (16.05.2018 11:07)
    }
}