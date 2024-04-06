import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class GameManager {

    //// INITIALIZATIONS OF OBJECTS, VARIABLES
    private int LetterRemain = 0;
    String LettersGuessed = "";
    private String Name = "";
    //private Queue queue = new Queue();
    CircularLinkedList list = new CircularLinkedList();
    private String CategorySelected, PuzzleSelected;
    private int i = 0, PlayerCount = 3;
    private int RoundNumber = 0;
    CircularLinkedList Wheel = new CircularLinkedList();
    private RandomizePuzzle RoundList = new RandomizePuzzle();
    Card Spin = new Card();
    boolean puzzleSolved = false;
    private CircularLinkedList PlayersList = new CircularLinkedList();
    // private Random rand = new Random();
    private Node Players = new Node();
    boolean WheelSpin = false;
    int Money = 0, CorrectLetter = 0, Attempt = 1;
    // private int RoundTotal = 0;
    private String AcceptInput = " ";

    //// RETURN ROUND NUMBER
    public int getRoundNumber() {
        return RoundNumber;
    }

    //// SETS ROUND NUMBER
    public void setRoundNumber(int roundnumber) {
        RoundNumber = roundnumber;
    }

    //// REGISTERS PLAYER
    public void CreatePlayer() throws InterruptedException, FileNotFoundException {

        for (i = 0; i < PlayerCount; i++) {
            System.out.print(" Enter Player " + (i + 1) + "'s Name: ");
            Name = new Scanner(System.in).nextLine();

            Player player = new Player();/// player initialized
            player.setPlayerName(Name);
            player.setPlayerNumber(i + 1);
            PlayersList.PopulateList(null, player, null);
        }
        System.out.println("\n\nAre you ready to play Wheel of Jepoardy!!\n\n" +
                "\tLets spin that Wheel!!!!\n\n");

        Wheel.PopulateWheel();

    }

    //// CHECKS IF PLAYERS EXIST

    void PlayerExists() throws FileNotFoundException, InterruptedException {
        if (PlayersList.isEmpty()) {
            System.out.println("\n\nREGISTER PLAYERS BELOW\n");
            CreatePlayer();
        }
    }

    //// DISPLAYS WINNER AND OTHER DETAILS ABOUT THE PLAYER
    public void ViewPlayers() {

        int Highest = 0;
        String Winner = "";
        int Lowest = 0;
        String Loser = "";
        System.out.println("Results after each round \n");
        System.out.println("*******************************************************");
        for (int i = 0; i < PlayerCount; i++) {
            Players = PlayersList.GoToNextNode();
            Players.getPlayerData().display();
            if (Players.getPlayerData().getGrandTotal() > Highest) {
                Highest = Players.getPlayerData().getGrandTotal();
                Winner = Players.getPlayerData().getPlayerName();
            } else {
                if (Players.getPlayerData().getGrandTotal() < Highest) {
                    Lowest = Players.getPlayerData().getGrandTotal();
                    Loser = Players.getPlayerData().getPlayerName();
                }

            }
            setRoundNumber(0);// clear contestant round data

        }

        System.out.println("\nTHE WINNER IS : " + Winner + " WITH A SCORE OF " + Highest);
        System.out.println("\nTHE LOSER IS : " + Loser + " WITH A SCORE OF " + Lowest);
        // System.out.println("*******************************************************");

    }

    public void PlayRound() throws FileNotFoundException, InterruptedException {

        RoundNumber += 1;

        int vowelAmount = 50;

        String attempt = " ";

        if (RoundNumber > 3) {//// Force the program to close {You can output all the the players roundtotal
                              //// here and other information here}
            System.out.println("\n\nEND OF GAME!!!\n\n\n");

            ViewPlayers();

            System.exit(0);
        }

        PlayerExists();

        Scanner sc = new Scanner(System.in);
        Players = PlayersList.GoToNextNode();

        while (!puzzleSolved) {

            System.out.println("**********************************************");
            System.out.println(
                    "WELCOME TO ROUND " + getRoundNumber() + " PLAYER " + Players.getPlayerData().getPlayerName()
                            + " : " +
                            +Players.getPlayerData().getPlayerNumber());

            System.out.println("**********************************************");

            // while (!WheelSpin) {
            // System.out.println("\nSPIN THE WHEEL!!! \n\t\t\t <press enter>");
            // // sc.nextLine();
            // if (Players.getPlayerData().getGrandTotal() > 0) {
            // System.out.print("\n\n\t\t\t ....or You can buy a vowel for $50. Enter a
            // vowel: ");
            // // String choice = sc.nextLine().toUpperCase();
            // }

            // attempt = new java.util.Scanner(System.in).nextLine().toUpperCase();

            // if (!vowelExist(attempt)) {
            // Spin = Wheel.SpinWheel();
            // Spin.display();
            // } else {
            // Players.getPlayerData().setGrandTotal(Players.getPlayerData().getGrandTotal()
            // - vowelAmount);
            // }
            // break;
            // }
            // WheelSpin = false;

            if (Spin.getType().equalsIgnoreCase("BANKRUPT")) {
                if (RoundNumber == 1) {
                    Players.getPlayerData().setRound1Total(0);
                    System.out.println("!!BANKRUPT!!");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                }
                if (RoundNumber == 2) {
                    Players.getPlayerData().setRound2Total(0);
                    System.out.println("BANKRUPT");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    // PlayPuzzle();
                }
                if (RoundNumber == 3) {
                    Players.getPlayerData().setRound3Total(0);
                    System.out.println("BANKRUPT");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());

                }
                puzzleSolved = false;
                // break;
            } else {
                if (Spin.getType().equalsIgnoreCase("Lose a Turn")) {
                    System.out.println("LOSE A TURN");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " has lost their turn");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    // break;
                } else {
                    if (Spin.getType().equals("Money")) {
                        Money = Spin.getValue();
                        System.out.println("\nYou can now play!!!");
                        break;

                    }

                }
            }
            break;
        }
        WheelSpin = false;
        PlayPuzzle();

    }

    public void SpinForPlayer() throws FileNotFoundException, InterruptedException {

        int vowelAmount = 50;

        String attempt = " ";

        Scanner sc = new Scanner(System.in);
        Players = PlayersList.GoToNextNode();

        while (!puzzleSolved) {

            System.out.println("**********************************************");
            System.out.println(
                    "WELCOME TO ROUND " + getRoundNumber() + " PLAYER " + Players.getPlayerData().getPlayerName()
                            + " : " +
                            +Players.getPlayerData().getPlayerNumber());

            System.out.println("**********************************************");

            // while (!WheelSpin) {
            // System.out.println("\nSPIN THE WHEEL!!! \n\t\t\t <press enter>");
            // // sc.nextLine();
            // if (Players.getPlayerData().getGrandTotal() > 0) {
            // System.out.print("\n\n\t\t\t ....or You can buy a vowel for $50. Enter a
            // vowel:");
            // // String choice = sc.nextLine().toUpperCase();
            // }

            // attempt = new java.util.Scanner(System.in).nextLine().toUpperCase();
            // // System.out.print("Enter letter: ");
            // // AcceptInput = sc.nextLine().toUpperCase();

            // if (!vowelExist(attempt)) {
            // Spin = Wheel.SpinWheel();
            // Spin.display();
            // } else {
            // Players.getPlayerData().setGrandTotal(Players.getPlayerData().getGrandTotal()
            // - vowelAmount);
            // }
            // break;
            // }
            // WheelSpin = false;

            if (Spin.getType().equalsIgnoreCase("BANKRUPT")) {
                if (RoundNumber == 1) {
                    Players.getPlayerData().setRound1Total(0);
                    System.out.println("!!BANKRUPT!!");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                }
                if (RoundNumber == 2) {
                    Players.getPlayerData().setRound2Total(0);
                    System.out.println("BANKRUPT");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    // PlayPuzzle();
                }
                if (RoundNumber == 3) {
                    Players.getPlayerData().setRound3Total(0);
                    System.out.println("BANKRUPT");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());

                }
                puzzleSolved = false;
                System.out.println("\nSPIN THE WHEEL!!! \n\t\t\t <press enter>");
                sc.nextLine();
                Spin = Wheel.SpinWheel();
                Spin.display();

            } else {
                if (Spin.getType().equalsIgnoreCase("Lose a Turn")) {
                    System.out.println("LOSE A TURN");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " has lost their turn");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());

                } else {
                    if (Spin.getType().equals("Money")) {
                        Money = Spin.getValue();
                        System.out.println("\nYou can now play!!!");

                    }

                }

                break;
            }
        }
        // WheelSpin = false;
        PlayPuzzle();

    }

    //// WHEN A PLAYER IS BANKEDRUPT OR LOST A TURN, THIS FUNCTION WILL SPIN FOR THE
    //// NEXT PLAYER
    /*
     * public void SpinForPlayer() throws StringIndexOutOfBoundsException,
     * FileNotFoundException, InterruptedException {
     * 
     * Attempt++;
     * 
     * Scanner sc = new Scanner(System.in);
     * 
     * Players = PlayersList.GoToNextNode();
     * 
     * System.out.println("**********************************************");
     * System.out.println(
     * "WELCOME TO ROUND " + getRoundNumber() + " PLAYER " +
     * Players.getPlayerData().getPlayerName() + " : " +
     * +Players.getPlayerData().getPlayerNumber());
     * 
     * System.out.println("**********************************************");
     * 
     * while (!WheelSpin) {
     * System.out.println("\nSPIN THE WHEEL!!! \n\t\t\t <press enter>");
     * sc.nextLine();
     * Spin = Wheel.SpinWheel();
     * Spin.display();
     * 
     * if (Spin.getType().equalsIgnoreCase("BANKRUPT")) {
     * if (RoundNumber == 1) {
     * Players.getPlayerData().setRound1Total(0);
     * System.out.println("!!BANKRUPT!!");
     * System.out.println(Players.getPlayerData().getPlayerName() + " :"
     * + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
     * Players = PlayersList.GoToNextNode();
     * System.out.println("Next Player: " + Players.getPlayerData().getPlayerName()
     * + " :"
     * + Players.getPlayerData().getPlayerNumber());
     * 
     * }
     * if (RoundNumber == 2) {
     * Players.getPlayerData().setRound2Total(0);
     * System.out.println("!!BANKRUPT!!");
     * System.out.println(Players.getPlayerData().getPlayerName() + " :"
     * + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
     * Players = PlayersList.GoToNextNode();
     * System.out.println("Next Player: " + Players.getPlayerData().getPlayerName()
     * + " :"
     * + Players.getPlayerData().getPlayerNumber());
     * // Players.getPlayerData().display();
     * }
     * if (RoundNumber == 3) {
     * Players.getPlayerData().setRound3Total(0);
     * System.out.println("!!BANKRUPT!!");
     * System.out.println(Players.getPlayerData().getPlayerName() + " :"
     * + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
     * Players = PlayersList.GoToNextNode();
     * System.out.println("Next Player: " + Players.getPlayerData().getPlayerName()
     * + " :"
     * + Players.getPlayerData().getPlayerNumber());
     * }
     * System.out.println("\nSPIN THE WHEEL!!! \n\t\t\t <press enter>");
     * sc.nextLine();
     * Spin = Wheel.SpinWheel();
     * Spin.display();
     * PlayPuzzle();
     * } else {
     * if (Spin.getType().equalsIgnoreCase("Lose a Turn")) {
     * System.out.println("LOSE A TURN");
     * System.out.println("So sorry, you lose a turn.");
     * System.out.println(Players.getPlayerData().getPlayerName() + " :"
     * + Players.getPlayerData().getPlayerNumber() + " has lost their turn");
     * Players = PlayersList.GoToNextNode();
     * System.out.println("Next Player: " + Players.getPlayerData().getPlayerName()
     * + " :"
     * + Players.getPlayerData().getPlayerNumber());
     * PlayPuzzle();
     * 
     * } else {
     * if (Spin.getType().equals("Money")) {
     * Money = Spin.getValue();
     * System.out.println("\nYou can now play");
     * PlayPuzzle();
     * 
     * }
     * 
     * }
     * 
     * }
     * 
     * }
     * 
     * // return Attempt;
     * }
     * 
     */
    public boolean vowelExist(String attempt) {
        String Vowels = "AEIOU";
        if (attempt.length() > 0)
            for (int i = 0; i < Vowels.length(); i++) {
                if (Vowels.charAt(i) == attempt.toUpperCase().charAt(0)) {
                    return true;
                }
            }
        return false;
    }

    public void PlayPuzzle() throws InterruptedException, FileNotFoundException, StringIndexOutOfBoundsException {

        //// INITIALIZATIONS
        // String AcceptInput = "";
        String spin = "";
        int more = 0;
        int total = 0;
        String fileName = "CategoriesAndPuzzles.txt";
        String puzzleLetters = "";
        CorrectLetter = 0;
        int Occurence = 0;
        int vowelAmount = 50;
        /// CHECKS IF PUZZLE IS SOLVED...IF SO RANDOM CHOSE A NEW PUZZLE FOR THE NEXT
        /// ROUND
        if (LetterRemain == 0) {

            LettersGuessed = "";
            //// LOADS PUZZLE FOR FILE

            RoundList.LoadPuzzle();

            //// RANDOMIZES THE PUZZLE IN THE CIRCULARLINKED LIST

            LetterRemain = RoundList.RandomizePuzzles();

            //// COLLECTS THE PUZZLE THAT WAS SELECTED BY THE RANDOMIZEPUZZLES FUNCTION

            PuzzleSelected = RoundList.getSelectedPuzzle();
            CategorySelected = RoundList.getSelectedCategatory();

            //// LOOPS TO THE LENGTH OF THE STRING
            /*
             * If the character is a space, then the loop appends a space to the end of the
             * LettersGuessed string.
             * Otherwise, the loop appends an asterisk (*) to the end of the LettersGuessed
             * string.
             * 
             */

            for (int i = 0; i < PuzzleSelected.length(); i++) // iterates over the
            {
                //// CONCATINATES STRING WITH ASTRICKS AND SPACE DEPENDING ON THE LENGTH OF THE
                //// PUZZLE
                if (PuzzleSelected.charAt(i) == ' ') {
                    LettersGuessed += " ";
                } else {
                    LettersGuessed += "*";
                }
            }

            System.out.print("\n\t\t[Round " + getRoundNumber() + "]-> Category: " + CategorySelected);

            System.out.println("\n\t\tThe puzzle is: " + LettersGuessed);

            System.out.println(PuzzleSelected);//// reveals puzzle

            Queue guesses = new Queue();

            while (true) {

                Scanner scanner = new Scanner(System.in);

                System.out.print("\n\t\t\n\nGuess a letter: ");
                AcceptInput = scanner.nextLine().toUpperCase();

                if (vowelExist(AcceptInput) && Players.getPlayerData().getGrandTotal() > 50) {
                    System.out.println("You must pay for vowels"
                            + " so $50 will be from your round total ");
                    // String choice = scanner.nextLine().toUpperCase();
                    Players.getPlayerData().setGrandTotal(Players.getPlayerData().getGrandTotal() - vowelAmount);
                    continue;
                }

                // .charAt - returns the character at a specified index in a string
                char guess = AcceptInput.charAt(0); // return the first character in the user's input
                //// VALIDATION CHECK TO ONLY ALLOW SINGLE LETTERS
                if (AcceptInput.length() != 1) {
                    System.err.println("\t\tERROR: Invalid input. Please enter a single letter.");
                    continue;/// REPEATS WHILE (TRUE) IF CONDITION IS TRUE SO USERS CAN RE-ENTER THEIR GUESS
                }
                ///// ENSURES THE CHAR COLLECTED FROM THE SCAN FUNCTION IS A LETTER
                if (!Character.isLetter(guess)) {
                    System.err.println("\t\tERROR: Invalid input. Please enter a letter.");
                    continue;
                }
                Guess input = new Guess(AcceptInput);
                boolean foundInQueue = false;
                ///// CHECK IF LETTER IS ALREADY IN THE QUEUE
                if (guesses.Contains(input)) {
                    System.err.println("\t\tYou already guessed that letter. Please try again.");
                    foundInQueue = true;
                    continue; /// IF IT IS ALREADY IN THE QUEUE THE PROGRAM WILL ALLOW USER TO RE-ENTER THEIR
                    /// GUESS
                }
                //// IF LETTER IS NOT FOUND IN QUEUE THEN ADD LETTER THE QUEUE
                if (!foundInQueue) {

                    Guess GuessedLetter = new Guess();

                    GuessedLetter.setGuessedLetter(AcceptInput);

                    guesses.Enqueue(GuessedLetter);

                    System.out.print("\n\t\tGuessed Letters: ");
                    guesses.DisplayQueue("DEFAULT");

                    System.out.print("\n\t\tGuessed Vowels: ");
                    guesses.DisplayQueue("SPECIAL");

                    guess = AcceptInput.charAt(0);

                    // START OF PUZZLE ITERATION

                    // if the puzzleSelected contains the string equivalent of char guess
                    if (PuzzleSelected.contains(String.valueOf(guess))) {

                        Occurence = 0;

                        System.out.println(
                                "\n\n\t\tCorrect! The letter " + guess + " is in the puzzle " + PuzzleSelected);

                        for (int i = 0; i < PuzzleSelected.length(); i++) // ITERATES TO THE LENGTH OF THE STRING
                        {

                            if (PuzzleSelected.charAt(i) == guess) // CHECKS IF LETTER IS IN THE STRING
                            {
                                LetterRemain--;/// THIS VARIABLE SHOWS HOW MUCH LETTERS ARE LEFT TO SOLVE

                                if (PuzzleSelected.charAt(i) == guess) {
                                    Occurence++;
                                }
                                //// REVEALS LETTERS GUESSED BY THE USER
                                LettersGuessed = LettersGuessed.substring(0, i) + guess
                                        + LettersGuessed.substring(i + 1);

                                /*
                                 * LettersGuessed.substring(0, i) returns a substring of LettersGuessed that
                                 * includes all characters before the index i.
                                 * guess is the user's guess and is added to the substring returned in step 1.
                                 * LettersGuessed.substring(i + 1) returns a substring of LettersGuessed that
                                 * includes all characters after the index i.
                                 * 
                                 * 
                                 */

                                CorrectLetter++;

                                if (CorrectLetter > 0) {
                                    more = CorrectLetter * Money;

                                }
                            }

                        }

                        System.out.print("\n\n\t\tThere are (" + Occurence + ") ("
                                + AcceptInput.toUpperCase().charAt(0) + ") in the puzzle");

                        System.out.println("\n\t\tYou have won $" + more);
                        //// ASSIGNS USERS SCORES TO THEIR ROUNDTOTAL
                        System.out.println("\n\t\t*********ROUND NUMBER: " + RoundNumber + "************\n");
                        if (RoundNumber == 1) {
                            Players.getPlayerData().setRound1Total(more);
                            Players.getPlayerData().setGrandTotal(0);/// adds each round to the grand total
                        }
                        if (RoundNumber == 2) {
                            Players.getPlayerData().setRound2Total(more);
                            Players.getPlayerData().setGrandTotal(0);/// adds each round to the grand total
                        }
                        if (RoundNumber == 3) {
                            Players.getPlayerData().setRound3Total(more);
                            Players.getPlayerData().setGrandTotal(0);/// adds each round to the grand total
                        }
                        System.out.println(" \t\t" + LetterRemain + " letter/s now remain in puzzle");

                        System.out.println("\t\tThe puzzle is now: " + LettersGuessed);

                        if (LetterRemain == 0) { // if you have guessed all the letters correctly

                            CorrectLetter = 0;
                            if (!LettersGuessed.contains("*")) {

                                System.out.println("\n\t\tCongratulations, you guessed all the letters in the puzzle!");

                                System.out.print("\n\t\tThe letters you guessed were: ");

                                guesses.DisplayQueue("DEFAULT");

                                System.out.print("\n\t\tThe vowels you guessed were: ");

                                guesses.DisplayQueue("SPECIAL");

                                System.out.println("\n\n");
                                System.out.print("\nRound " + getRoundNumber() + " Results: ");
                                System.out.println("\n**********************************");
                                Players.getPlayerData().display();
                                System.out.println("\n*********************************");
                                System.out.println("\nTo the next player........");

                                if (RoundNumber >= 1 && RoundNumber <= 3) {
                                    PlayRound();
                                }

                            }
                        }
                        puzzleSolved = false;

                    } // END OF PUZZLE ITERATION
                    else { // IF THE PUZZLE DOES NOT CONTAIN THE CHAR GUESS

                        System.out.println("\n\n\t\t\tThe puzzle was not solved so you may continue!!!!");

                        System.out.print("\n\t\t[Round " + getRoundNumber() + "]-> Category: " + CategorySelected);

                        System.out.println("\n\t\tThe puzzle is: " + LettersGuessed);

                        System.out.println("Current player: ");

                        Players.getPlayerData().display();

                        SpinForPlayer();
                        // PlayRound();

                    }

                }

            }

        }

    }

    public void makePuzzle() {
        for (int i = 0; i < PuzzleSelected.length(); i++) // iterates over the
        {
            //// CONCATINATES STRING WITH ASTRICKS AND SPACE DEPENDING ON THE LENGTH OF THE
            //// PUZZLE
            if (PuzzleSelected.charAt(i) == ' ') {
                LettersGuessed += " ";
            } else {
                LettersGuessed += "*";
            }
        }
    }

}
