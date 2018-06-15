import java.util.Scanner;

/* ***************************
   Programm:    Tic Tac Toe
   Author:      Fabienne Kägi
   Schule:      Benedict Zürich IT1B
   Datum:       Mai 2018
   ***************************
   *
 */public class Method{
    /*
    Declare color
    */
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_CLEAN = "\u001B[0m";
    public static final String COLOR_MAGENTA = "\u001B[35;1m";

    static Scanner scanner = new Scanner(System.in);


    /*
      Exit game by specific user input (EXIT_CODE = 99).
      @param userInput numerical user input value
      @return keep playing
     */
    public static boolean isExitOfTheGame(int userInput) {
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
    public static String[] initBoard() {
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
    public static void printBoard(String[] board) {
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
    public static String readUserInput(Player actPlayer, Scanner scanner) {
        System.out.println("Spieler " + actPlayer.name() + " (" + actPlayer.value + "): Wähle ein Feld: ");
        return scanner.nextLine();
    }

    /*
     * search the pc choise
     * @param actPlayer actuel player
     * @return string of pc choise
     */
    public static String readPcInput(Player actPlayer, String[] board) {
        System.out.println("Spieler " + actPlayer.name() + " (" + actPlayer.value + "): Wähle ein Feld: ");
        int pcChoise = 0;
        if (board[0].equals(Player.A.value) && board[1].equals(Player.A.value)) {
            pcChoise = 3;
        }
        if (board[0] == Player.A.value && board[4] == Player.A.value) {
            pcChoise = 9;
            if (board[8] == Player.pc.value) {
                pcChoise = (int) (Math.random() * 9) + 1;
            }
        }
        if (board[3] == Player.A.value && board[4] == Player.A.value) {
            pcChoise = 6;
            if (board[5] == Player.pc.value) {
                pcChoise = (int) (Math.random() * 9) + 1;
            }
        } else {
            pcChoise = (int) (Math.random() * 9) + 1;
        }
        String s = String.valueOf(pcChoise);
        return s;
    }


    /*
      Check if the given player has won or not.
      @param board  Play board
      @param player enum based player
      @return true if player has won.
     */
    public static boolean checkWinner(String[] board, Player player) {
        boolean retVal = false;
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
    public enum Player {
        A(COLOR_RED + "X" + COLOR_CLEAN), B(COLOR_BLUE + "O" + COLOR_CLEAN), pc(COLOR_BLUE + "O" + COLOR_CLEAN);

        public String value;

        Player(String value) {
            this.value = value;
        }
    }

    /*
      Definition of what numbers are allowed.
     */
    public enum FieldsOfTheBoard {
        ONE(1), TWO(2), THREE(3),
        FOUR(4), FIVE(5), SIX(6),
        SEVEN(7), EIGHT(8), NINE(9);

        public final int fieldValue;

        FieldsOfTheBoard(int fieldValue) {
            this.fieldValue = fieldValue;
        }

        public static boolean contains(String fieldValue) {
            for (FieldsOfTheBoard fieldsOfTheBoard : FieldsOfTheBoard.values()) {
                if (fieldsOfTheBoard.fieldValue == Integer.valueOf(fieldValue)) {
                    return true;
                }
            }
            return false;
        }
    }
}
