/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */

import java.util.Random;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class RandomizePuzzle {
        ////DECLARATION OF VARIABLES AND OBJECTS
        private Node Head;
        private Node Tail;
        private int Size;
        private int numberOfPuzzle;
        private String CategoryFromFile;
        private String PuzzleFromFile;
        private String SelectedCategory, SelectedPuzzle;
    ///CONSTRUCTOR
        public RandomizePuzzle() {////DEFAULT
            Head = Tail = null;
            Size = 0;
        }
    
        public RandomizePuzzle(Node head, Node tail, int size) {////PRIMARY
            Head = head;
            Tail = tail;
            Size = size;
        }

        public RandomizePuzzle(RandomizePuzzle randomizePuzzle){///COPY
            Head = randomizePuzzle.Head;
            Tail = randomizePuzzle.Tail;
            Size = randomizePuzzle.Size;
        }
        ///GETTERS
        public Node getHead() {
            return Head;
        }
    
        public Node getTail() {
            return Tail;
        }
    
        public int getSize() {
            return Size;
        }
        public String getSelectedPuzzle(){
            return SelectedPuzzle;
        }

        public String getSelectedCategatory(){
            return SelectedCategory;
        }
        ///SETTERS
        public void setHead(Node head) {
            Head = head;
        }
    
        public void setTail(Node tail) {
            Tail = tail;
        }
    
        public void setSize(int size) {
            Size = size;
        }

        ////INSERT PUZZLE AT THE BACK OF LIST
        public void InsertAtBack(PuzzleData dataToInsert) {
            Node temp = new Node(dataToInsert);
            if (temp != null) {
                if (Head == null) {
                    Head = temp;
                    Tail = temp;
                    Tail.setNextNode(Head);
                } else {
                    temp.setNextNode(Head);
                    Tail.setNextNode(temp);
                    Tail = temp;
                }
            } else {
                System.err.println("Error! The list is full, cannot insert new node.");
            }
            Size++;
        }
        ////CHECKS IF LIST IS EMPTY
        public boolean isEmpty() {
            return Head == null;
        }

        ///DISPLAYS ALL PUZZLES
        public void Display() {
            if (!isEmpty()) {
                Node traverse = Head;
                do {
                    System.out.print(traverse);
                    traverse = traverse.getNextNode();
                } while (traverse != Head);
            } else {
                System.out.println("NULL");
            }
        }

        ////GOES TO THE NEXT PUZZLE IN THE LIST
        public Node GoToNextNode() {
            Node current = Head;
            Node Data = new Node();
            if (!isEmpty()) {
                Data = current;
                Head = Head.getNextNode();
                Tail = current;
            }
            return Data;
        }
        ///ADDS PUZZLE TO LIST FROM FILE
        public void LoadPuzzle() throws FileNotFoundException {
            
            try {
                Scanner ReadPuzzle = new Scanner(new File("CategoriesAndPuzzles.txt"));
    
                while (ReadPuzzle.hasNext()) {
                    CategoryFromFile = ReadPuzzle.next();
                    PuzzleFromFile = ReadPuzzle.next();
                    PuzzleData puzzleData = new PuzzleData(CategoryFromFile, PuzzleFromFile);
                    InsertAtBack(puzzleData);
                    numberOfPuzzle++;
                }
            } catch (FileNotFoundException fnf) {
                fnf.printStackTrace();
            } catch (Exception e) {
                System.err.println("Exception thrown: " + e.getMessage());
             } 
        }
        ////THIS FUNCTION RETURNS THE LENGTH OF THE WORD IN THE PUZZLE AND SET THE SELECTEDPUZZLE
        public int SelectPuzzleRandom(Node puzzleData) {

           
            int LetterRemainInPuzzle = 0;
            SelectedPuzzle = puzzleData.getRoundData().getPuzzle().replaceAll("_", " ");
            SelectedPuzzle =  SelectedPuzzle.replaceAll(",", " ");
            SelectedCategory = puzzleData.getRoundData().getCategory();
    
            LetterRemainInPuzzle = SelectedPuzzle.replaceAll(" ", "").replaceAll(",", "").replaceAll("'", "")
                    .replaceAll("-", "").replaceAll("_", "").length();
            return LetterRemainInPuzzle;
        }

        ////RANDOMIZES PUZZLES IN THE LIST
        public int RandomizePuzzles(){////Randomizes Puzzle or selects random puzzle (Rename function to Play when finished building)
            Node currentPuzzle = new Node();
            try{

                System.out.println("\nShuffling Puzzle.....");
                //Thread.sleep(5000);
                currentPuzzle = Head;
                Random random = new Random();
                int currentPosition = 0;
                int spaces = random.nextInt(51) + 50;
                
                for (int number = 0; number < spaces; number++){
                    currentPuzzle = currentPuzzle.getNextNode();
                    currentPosition++;
                    if (currentPosition >= Size){
                        currentPosition = 0;
                        currentPuzzle = Head;
                    }
                    
                }
                
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return SelectPuzzleRandom(currentPuzzle);
        }
}


