
/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        //// MENU
        Scanner scanner = new Scanner(System.in);
        int Choice;
        //Game game = new Game();
        GameManager game = new GameManager();

        System.out.println("***********************************************************************************");
        System.out.println("*               WELCOME THE GAME SHOW NETWORK (GSN) WHEEL OF FORTUNE              *");
        System.out.print(" **********************************--------------**********************************\n");
        System.out.print(" +--------------------------------[  MAIN MENU  ]---------------------------------+\n");
        System.out.print(" |                                                                                |\n");
        System.out.print(" |                            ENTER 1 TO BEGIN PLAY                               |\n");
        System.out.print(" |                            ENTER 2 TO EXIT GAME                                  |\n");
        System.out.println("***********************************************************************************");
        System.out.print("\t\tCHOICE : ");
        Choice = scanner.nextInt();
        switch (Choice) {
            case 1:
                //// STARTS GAME
                game.PlayRound();

                break;
            case 2:
                System.out.println("You are now leaving the game......");
                Thread.sleep(3000);

                System.exit(0);
                break;
        }

    }

}
