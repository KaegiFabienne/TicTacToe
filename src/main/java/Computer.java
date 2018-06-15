import java.util.Scanner;
/* ***************************
   Programm:    Tic Tac Toe
   Author:      Fabienne Kägi
   Schule:      Benedict Zürich IT1B
   Datum:       Mai 2018
   ***************************
 */

public class Computer extends FriendPlayer {


    public static void pcPlayer() {

        System.out.println("Sie haben sich für den Computer als Gegner entschieden:\nViel Spass. :D");

     /*
       Defined variables.
     */
        String[] board = initBoard();
        boolean keepPlayingTheGame = true;
        int turn = 1;
        Player actPlayer = Player.A;




     /*
      Board output.
     */
        System.out.println("\nSpielfeld:");
        printBoard(board);


        while (keepPlayingTheGame)

        {

              /*
              Which player's turn.
             */
            if (turn % 2 == 0) {
                actPlayer = Player.pc;
            } else {
                actPlayer = Player.A;
            }

            if (actPlayer == Player.A) {
            /*
              Read the user input.
             */
                String userInputString = readUserInput(actPlayer, scanner);
                int userInput = 0;
                try {
                    userInput = Integer.valueOf(userInputString);
                } catch (NumberFormatException e) {
                    logger.error("Player " + actPlayer + " letter entered...");

                    System.out.println(COLOR_RED + "FEHLER" + COLOR_CLEAN + ": Eingabe muss eine Zahl von 1-9 sein und nicht: " + userInputString);
                    continue;
                }
                // logger.debug("Player " + actPlayer + " input...");


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
                    System.out.println("Oppala, Eingabe ausserhalb des Ranges [1-9]. \nVersuch es noch einmal.");
                    continue;
                }

            /*
               Check if field has already been set. if yes then a 'continue',
               otherwise the respective train will be issued.
             */
                if (board[userInput - 1].equals(userInputString)) {
                    board[userInput - 1] = actPlayer.value;
                } else {
                    System.out.println("Ups, Feld [" + userInputString + "] wurde bereits gesetzt. Nimm ein anderes Feld.");
                    continue;
                }
            } else {
                String pcInputString =readPcInput(actPlayer, board);
                int pcInput = 0;
                pcInput = Integer.valueOf(pcInputString);

                if (board[pcInput - 1].equals(pcInputString)) {
                    board[pcInput - 1] = actPlayer.value;
                } else {
                    System.out.println("Ups, Feld [" + pcInputString + "] wurde bereits gesetzt. Nimm ein anderes Feld.");
                    continue;
                }
            }
            /*
              Actual board output.
             */
            printBoard(board);

            /*
              Winner output.
             */
            if (checkWinner(board, actPlayer)) {
                logger.info("Game end...");
                System.out.println(COLOR_GREEN + "\nBravo " + COLOR_CLEAN + "Spieler " + actPlayer.name() + " (" + actPlayer.value + ") hat gewonnen.;D");
                break;
            } else if (turn == 9) {
                logger.info("Game end...");
                System.out.println("\nUnentschieden...");
                break;
            }
            turn++;
        }
    }
}
