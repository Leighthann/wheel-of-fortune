/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */
public class PuzzleData{

    ////DECLARE VARIABLES
    private String Category;
    private String Puzzle;

    //CONSTRUCTOR
    PuzzleData(){//DEFAULT
        Category = "";
        Puzzle = "";
       
    }
    ///
    PuzzleData(String category, String puzzle){///PRIMARY
        Category = category;
        Puzzle = puzzle;
        
    }
    
    PuzzleData(PuzzleData puzzleData){////COPY
        Category = puzzleData.Category;
        Puzzle = puzzleData.Puzzle;
       
    }
    ////GETTERS
    public String getCategory(){
        return Category;
    }
    
    public String getPuzzle(){
        return Puzzle;
    }
    ////SETTERS
    public void setCategory(String category){
        Category = category;
    }
    
    public void setPuzzle(String puzzle){
        Puzzle = puzzle;
    }
    ///TOSTRING
    public String toString(){
        return Category + "\t" + Puzzle + "\n";
    }
    ///DISPLAY
    public void Display(){
        System.out.print(toString());
    }

   


    
}