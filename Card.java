/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */
public class Card{

    ////INITIALIZATION
    public String Type;
    public int Value;
    
    Card(){///DEFAULT
        Type = "";
        Value = 0;
    }
    
    Card(String type, int value){///PRIMARY
        Type = type;
        Value = value;
    }
    
    Card(Card card){///COPY
        Type = card.Type;
        Value = card.Value;
    }
    ///GETTERS
    public String getType(){
        return Type;
    }
    
    public int getValue(){
        return Value;
    }
    ///SETTERS
    public void setType(String type){
        Type = type;
    }
    
    public void setValue(int value){
        Value = value;
    }
    ////TOSTRING
    
    public String toString(){
        return "Card Type: " + Type + ", Card Value: $" + Value ;
    }
    ///DISPLAY
    public void display(){
        System.out.print(toString());
    }
}