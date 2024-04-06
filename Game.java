
/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class Game {

    //// INITIALIZATIONS OF OBJECTS, VARIABLES
    private int LetterRemain = 0;
    String LettersGuessed = "";
    private String Name = "";
    private Queue queue = new Queue();
    CircularLinkedList list = new CircularLinkedList();
    private String CategorySelected, PuzzleSelected;
    private int i = 0, PlayerCount = 3;
    private int RoundNumber = 0;
    CircularLinkedList Wheel = new CircularLinkedList();
    private RandomizePuzzle RoundList = new RandomizePuzzle();
    Card Spin = new Card();
    boolean puzzleSolved = false;
    private CircularLinkedList PlayersList = new CircularLinkedList();
    private Random rand = new Random();
    private Node Players = new Node();
    boolean WheelSpin = false;
    int Money = 0, CorrectLetter = 0, Attempt = 1;
    private int RoundTotal = 0;

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
        System.out.println("*******************************************************");
        System.out.println("\nTHE LOSER IS : " + Loser + " WITH A SCORE OF " + Lowest);

    }

    //// STARTS THE GAME AND CONTROLS WHICH ROUND THE PLAYERS ARE IN
    public void PlayRound() throws InterruptedException, FileNotFoundException {

        RoundNumber += 1;
        if (RoundNumber > 3) {//// Force the program to close {You can output all the the players roundtotal
                              //// here and other information here}
            System.out.println("\n\nEND OF GAME!!!\n\n\n");

            ViewPlayers();

            System.exit(0);
        }

        PlayerExists();

        Scanner sc = new Scanner(System.in);

        Players = PlayersList.GoToNextNode();

        System.out.println("**********************************************");
        System.out.println(
                "WELCOME TO ROUND " + getRoundNumber() + " PLAYER " + Players.getPlayerData().getPlayerName() + " : " +
                        +Players.getPlayerData().getPlayerNumber());

        System.out.println("**********************************************");

        if (!WheelSpin) {
            System.out.println("\nSPIN THE WHEEL!!! \n\t\t\t <press enter>");
            sc.nextLine();

            Spin = Wheel.SpinWheel();

            if (Spin.getType().equalsIgnoreCase("BANKRUPT")) {
                if (RoundNumber == 1) {
                    Players.getPlayerData().setRound1Total(0);
                    System.out.println("BANKRUPT");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    PlayPuzzle();

                }
                if (RoundNumber == 2) {
                    Players.getPlayerData().setRound2Total(0);
                    System.out.println("BANKRUPT");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    PlayPuzzle();

                }
                if (RoundNumber == 3) {
                    Players.getPlayerData().setRound3Total(0);
                    System.out.println("BANKRUPT");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    PlayPuzzle();

                }
            } else {
                if (Spin.getType().equalsIgnoreCase("Lose a Turn")) {
                    System.out.println("LOSE A TURN");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " has lost their turn");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    WheelSpin = false;
                } else {
                    if (Spin.getType().equals("Money")) {
                        Money = Spin.getValue();
                        System.out.println("\nYou can now play!!!");
                        PlayPuzzle();

                    }

                }

            }
            // forGuesses();
        }

    }

    //// WHEN A PLAYER IS BANKEDRUPT OR LOST A TURN, THIS FUNCTION WILL SPIN FOR THE
    //// NEXT PLAYER
    public int SpinForPlayer() throws StringIndexOutOfBoundsException, FileNotFoundException, InterruptedException {

        Attempt++;

        Scanner sc = new Scanner(System.in);

        Spin = Wheel.SpinWheel();

        Players = PlayersList.GoToNextNode();

        System.out.println("**********************************************");
        System.out.println(
                "WELCOME TO ROUND " + getRoundNumber() + " PLAYER " + Players.getPlayerData().getPlayerName() + " : " +
                        +Players.getPlayerData().getPlayerNumber());

        System.out.println("**********************************************");

        while (!WheelSpin) {
            System.out.println("\nSPIN THE WHEEL!!! \n\t\t\t <press enter>");
            sc.nextLine();

            if (Spin.getType().equalsIgnoreCase("BANKRUPT")) {
                if (RoundNumber == 1) {
                    Players.getPlayerData().setRound1Total(0);
                    System.out.println("!!BANKRUPT!!");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: ");
                    PlayPuzzle();

                }
                if (RoundNumber == 2) {
                    Players.getPlayerData().setRound2Total(0);
                    System.out.println("!!BANKRUPT!!");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: ");
                    Players.getPlayerData().display();
                    PlayPuzzle();
                }
                if (RoundNumber == 3) {
                    Players.getPlayerData().setRound3Total(0);
                    System.out.println("!!BANKRUPT!!");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " is now bankrupt");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    PlayPuzzle();

                }
            } else {
                if (Spin.getType().equalsIgnoreCase("Lose a Turn")) {
                    System.out.println("LOSE A TURN");
                    System.out.println("So sorry, you lose a turn.");
                    System.out.println(Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber() + " has lost their turn");
                    Players = PlayersList.GoToNextNode();
                    System.out.println("Next Player: " + Players.getPlayerData().getPlayerName() + " :"
                            + Players.getPlayerData().getPlayerNumber());
                    PlayPuzzle();

                } else {
                    if (Spin.getType().equals("Money")) {
                        Money = Spin.getValue();
                        System.out.println("\nYou can now play");
                        PlayPuzzle();

                    }

                }

            }

        }
        return Attempt;
    }

    //// RETURN ROUND NUMBER
    public int getRoundNumber() {
        return RoundNumber;
    }

    //// SETS ROUND NUMBER
    public void setRoundNumber(int roundnumber) {
        RoundNumber = roundnumber;
    }

    //// THIS FUNCTION STARTS THE PUZZLE GAME
    public void PlayPuzzle() throws InterruptedException, FileNotFoundException, StringIndexOutOfBoundsException {

        //// INITIALIZATIONS
        String AcceptInput = "";
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

            System.out
                    .print("\n\t\t[Round " + getRoundNumber() + "]-> Category: " + CategorySelected);

            System.out.println("\n\t\tThe puzzle is: " + LettersGuessed);

            Queue guesses = new Queue();

            while (true) {

                Scanner scanner = new Scanner(System.in);

                System.out.println(PuzzleSelected);//// reveals puzzle

                if (Players.getPlayerData().getGrandTotal() > 0) {
                    System.out.print("\n\n\t\t\t Press V to enter a vowel:");
                    spin = scanner.nextLine();
                    if (spin.equalsIgnoreCase("V")) {
                        System.out.print("\n\t\tGuess a vowel: ");
                        AcceptInput = scanner.nextLine().toUpperCase();
                        Players.getPlayerData().setGrandTotal(Players.getPlayerData().getGrandTotal() - vowelAmount);
                    } else {
                        System.out.print("\n\tGuess a constonant: ");
                        AcceptInput = scanner.nextLine().toUpperCase();
                        continue;
                    }
                } else {
                    System.out.print("\n\tGuess a letter: ");
                    AcceptInput = scanner.nextLine().toUpperCase();
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

                    // if (AcceptInput.equalsIgnoreCase("A") | AcceptInput.equalsIgnoreCase("E")
                    // | AcceptInput.equalsIgnoreCase("I") | AcceptInput.equalsIgnoreCase("O")
                    // | AcceptInput.equalsIgnoreCase("U"))

                    guesses.Enqueue(GuessedLetter);

                    System.out.print("\t\tGuessed Letters: ");
                    guesses.DisplayQueue("DEFAULT");

                    System.out.print("\n\t\tGuessed Vowels: ");
                    guesses.DisplayQueue("SPECIAL");

                    guess = AcceptInput.charAt(0);

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

                        if (LetterRemain == 0) {

                            CorrectLetter = 0;
                            if (!LettersGuessed.contains("*")) {

                                System.out.println("\n\t\tCongratulations, you guessed all the letters in the puzzle!");

                                System.out.print("\n\t\tThe letters you guessed were: ");

                                guesses.DisplayQueue("REFULAR");

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

                    } else {
                        System.out.println("\n\t\tSorry, the letter " + guess + " is not in the puzzle");
                        System.out.println("\t\tNow passing it to the next person.....");
                        System.out.println("\n\n");

                        SpinForPlayer();
                    }
                    foundInQueue = true;
                }
            }
        } else {
            System.out.println("\t\t\tThe puzzle was not solved so you may continue!!!!");

            System.out.print("\n\t\t[Round " + getRoundNumber() + "]-> Category: " + CategorySelected);

            System.out.println("\n\t\tThe puzzle is: " + LettersGuessed);

        }

    }

    public void clearTheRound() {
        queue.DestroyQueue();
    }

}
