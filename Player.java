/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */

public class Player {
    ///INITIALIZES VARIABLES AND OBJECTS
    private int PlayerNumber;
    private String PlayerName;
    private int Round1Total = 0;
    private int Round2Total = 0;
    private int Round3Total = 0;
    private int GrandTotal = 0;
    ////CONSTRUCTORS
    Player() {//DEFAULT
        PlayerNumber = 0;
        PlayerName = "";
        Round1Total = 0;
        Round2Total = 0;
        Round3Total = 0;
        GrandTotal = 0;
    }

    Player(String playerName, int playerNumber) {////PRIMARY
        PlayerNumber = playerNumber;
        PlayerName = playerName;
    }
    ///PRIMARY CONSTRUCTOR 2
    Player(int playerNumber, String playerName, int round1Total, int round2Total, int round3Total, int grandTotal) {
        PlayerNumber = playerNumber;
        PlayerName = playerName;
        Round1Total = round1Total;
        Round2Total = round2Total;
        Round3Total = round3Total;
        GrandTotal = grandTotal;
    }

    Player(Player player) {///COPY CONSTRUCTOR
        PlayerNumber = player.PlayerNumber;
        PlayerName = player.PlayerName;
        Round1Total = player.Round1Total;
        Round2Total = player.Round2Total;
        Round3Total = player.Round3Total;
        GrandTotal = player.GrandTotal;
    }
    ///GETTERS
    public int getPlayerNumber() {
        return PlayerNumber;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public int getRound1Total() {
        return Round1Total;
    }

    public int getRound2Total() {
        return Round2Total;
    }

    public int getRound3Total() {
        return Round3Total;
    }

    public int getGrandTotal() {
        return GrandTotal;
    }

    ///SETTERS

    public void setPlayerNumber(int playerNumber) {
        PlayerNumber = playerNumber;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public void setRound1Total(int round1Total) {
        Round1Total = round1Total;
    }
    public void setRound2Total(int round2Total) {
        Round2Total = round2Total;
    }
    public void setRound3Total(int round3Total) {
        Round3Total = round3Total;
    }

    public void setGrandTotal(int grandTotal) {
        GrandTotal = Round1Total + Round2Total + Round3Total;
    }

    
      public void addGrandTotal(int money) {
      GrandTotal += money;
      }
     
    ///TOSTRING
    public String toString() {
        return "\nPlayer's Number: " + PlayerNumber + "\nPlayer's Name: " + PlayerName + "\nPlayer's Round 1 Total: "
                + Round1Total + "\nPlayer's Round 2 Total: " + Round2Total + "\nPlayer's Round 3 Total: " + Round3Total + "\nPlayer's Grand Total: " + GrandTotal + "\n";
    }
    ///DISPLAY PLAYER'S DATA
    public void display() {
        System.out.print(toString());
    }
   

}