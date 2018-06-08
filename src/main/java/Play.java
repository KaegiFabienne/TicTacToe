import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Random;
import java.util.Scanner;

public class Play extends Method{

    /*
    Declare color
    */

    private static final Logger logger = LoggerFactory.getLogger(Play.class);

    public static void main(String[] args) {
        MDC.put("reqID", generateMdcReqID());


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
        logger.debug("Play has been started....");

        Scanner gegner = new Scanner(System.in);
        Scanner next = new Scanner(System.in);
        boolean round = true;

        while (round == true) {

            System.out.println("Gegner-Wahl:\n 1. Gegen einen Fruend spielen\n 2. Gegen den Computer spielen");

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
        logger.info("Play is over....");
        System.out.println("Sie haben das Spiel verlassen");
        MDC.clear();
    }

    private static String generateMdcReqID() {
        Random random = new Random();
        int aStart = 1;
        int aEnd = Integer.MAX_VALUE;
        long range = (long) aEnd - (long) aStart + 1;
        long fraction = (long) (range * random.nextDouble());
        int randomNumber = (int) (fraction + aStart);
        return String.valueOf(randomNumber);
    }

}