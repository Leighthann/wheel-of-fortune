/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */
public class Node {
    ///INITIALIZES VARIABLES AND OBJECTS
    private Player PlayerData;
    private Card CardData;
    private PuzzleData RoundData;
    private Guess GuessData;
    private Node NextNode;
    private Node PreviousNode;
    ///CONSTRUCTORS
    public Node() {///DEFAULT
        PlayerData = new Player();
        CardData = new Card();
        RoundData = new PuzzleData();
        GuessData = new Guess();
        NextNode = null;
        PreviousNode = null;
    }
    ///PRIMARY CONSTRUCTOR
    public Node(Player playerData, Card cardData, PuzzleData roundData, Guess guessData, Node nextNode, Node previousNode) {
        PlayerData = playerData;
        CardData = cardData;
        RoundData = roundData;
        GuessData = guessData;
        NextNode = nextNode;
        PreviousNode = previousNode;
    }
    ///COPY CONSTRUCTOR
    public Node(Player playerData, Card cardData, PuzzleData roundData, Guess guessData) {
        PlayerData = playerData;
        CardData = cardData;
        RoundData = roundData;
        GuessData = guessData;
        NextNode = null;
        PreviousNode = null;
    }
    ////PRIMARY CONSTRUCTOR 2
    public Node(int playerNumber, String playerName, int round1Total, int round2Total, int round3Total, int grandTotal) {
        PlayerData = new Player(playerNumber, playerName, round1Total, round2Total, round3Total,grandTotal);
        NextNode = null;
        PreviousNode = null;
    }
    /////INITIALIZES CARD DATA
    public Node(String type, int value) {
        CardData = new Card(type, value);
        NextNode = null;
        PreviousNode = null;
    }
    /////INITIALIZES PUZZLE DATA
    public Node(String category, String puzzle) {
        RoundData = new PuzzleData(category, puzzle);
        NextNode = null;
        PreviousNode = null;
    }
    ///SETS PLAYER DATA
    public Node(Player playerdata) {
        PlayerData = playerdata;
    }

    public Node(PuzzleData puzzle) {
        RoundData = puzzle;
    }

    public Node(Player player, Card carddata, PuzzleData puzzledata, Node nextnode, Node previousnode) {
        PlayerData = player;
        CardData = carddata;
        RoundData = puzzledata;
        NextNode = nextnode;
        PreviousNode = previousnode;

    }

    public Node(String guessedLetter) {
        GuessData = new Guess(guessedLetter);
        NextNode = null;
        PreviousNode = null;
    }

    public Node(Guess guessData) {///SETS GUESS
        GuessData = guessData;
        NextNode = null;
        PreviousNode = null;
    }

    public Node(Card cardData) {////SET CARD DATA
        CardData = cardData;
        NextNode = null;
        PreviousNode = null;
    }

    public Node(Node node) {///COPY CONSTRUCTOR
        PlayerData = node.PlayerData;
        CardData = node.CardData;
        RoundData = node.RoundData;
        GuessData = node.GuessData;
        NextNode = node.NextNode;
        PreviousNode = node.PreviousNode;
    }
    ////SETTERS
    public void setPlayerData(Player playerData) {
        PlayerData = playerData;
    }

    public void setCardData(Card cardData) {
        CardData = cardData;
    }

    public void setRoundData(PuzzleData roundData) {
        RoundData = roundData;
    }

    public void setGuessData(Guess guessData) {
        GuessData = guessData;
    }

    public void setNextNode(Node nextNode) {
        NextNode = nextNode;
    }

    public void setPreviousNode(Node previousNode) {
        PreviousNode = previousNode;
    }
    ///GETTERS
    public Player getPlayerData() {
        return PlayerData;
    }

    public Card getCardData() {
        return CardData;
    }

    public PuzzleData getRoundData() {
        return RoundData;
    }

    public Guess getGuessData() {
        return GuessData;
    }

    public Node getNextNode() {
        return NextNode;
    }

    public Node getPreviousNode() {
        return PreviousNode;
    }
    ///tostring METHOD
    public String toString() {
        return "[" + CardData + "| *]->";
    }
    ///DISPLAYS NODE
    public void display() {
        System.out.println(toString());
    }

    
    
    
}