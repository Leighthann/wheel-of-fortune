
/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */
import java.util.Random;

public class CircularLinkedList {
    /// INITIALIZATION
    private Node Head;
    private Node Tail;
    private int Size;
    Card Spin = new Card();

    /// CONSTRUCTORS
    public CircularLinkedList() {/// DEFAULT
        Head = null;
        Tail = null;
        Size = 0;
    }

    public CircularLinkedList(Node head, Node tail, int size) {// PRIMARY
        Head = head;
        Tail = tail;
        Size = size;
    }

    public CircularLinkedList(CircularLinkedList list) {/// COPY
        Head = list.Head;
        Tail = list.Tail;
        Size = list.Size;
    }

    /// GETTERS
    public Node getHead() {
        return Head;
    }

    public Node getTail() {
        return Tail;
    }

    public int getSize() {
        return Size;
    }

    /// SETTERS
    public void setHead(Node head) {
        Head = head;
    }

    public void setTail(Node tail) {
        Tail = tail;
    }

    public void setSize(int size) {
        Size = size;
    }

    ///// POPULATES THE CIRCULARLINKED LIST WITH 25 CARDS
    public void PopulateWheel() {/// CIRCULAR LINKEDLIST

        Card card1 = new Card("Bankrupt", 0);
        Card card2 = new Card("Lose a Turn", 0);
        Card card3 = new Card("Money", 850);
        Card card4 = new Card("Bankrupt", 0);
        Card card5 = new Card("Bankrupt", 0);
        Card card6 = new Card("Lose a Turn", 0);
        Card card7 = new Card("Money", 2500);
        Card card8 = new Card("Money", 600);
        Card card9 = new Card("Money", 700);
        Card card10 = new Card("Money", 600);
        Card card11 = new Card("Money", 650);
        Card card12 = new Card("Money", 500);
        Card card13 = new Card("Money", 700);
        Card card14 = new Card("Money", 600);
        Card card15 = new Card("Money", 550);
        Card card16 = new Card("Money", 500);
        Card card17 = new Card("Money", 600);
        Card card18 = new Card("Money", 650);
        Card card19 = new Card("Money", 700);
        Card card20 = new Card("Money", 800);
        Card card21 = new Card("Money", 500);
        Card card22 = new Card("Money", 650);
        Card card23 = new Card("Money", 500);
        Card card24 = new Card("Money", 900);
        Card card25 = new Card("Money", 700); // Added just because we need 25 Cards in the list

        InsertAtFront(card1);
        InsertAtFront(card2);
        InsertAtFront(card3);
        InsertAtFront(card4);
        InsertAtFront(card5);
        InsertAtFront(card6);
        InsertAtFront(card7);
        InsertAtFront(card8);
        InsertAtFront(card9);
        InsertAtFront(card10);
        InsertAtFront(card11);
        InsertAtFront(card12);
        InsertAtFront(card13);
        InsertAtFront(card14);
        InsertAtFront(card15);
        InsertAtBack(card16);
        InsertAtBack(card17);
        InsertAtBack(card18);
        InsertAtBack(card19);
        InsertAtBack(card20);
        InsertAtBack(card21);
        InsertAtBack(card22);
        InsertAtBack(card23);
        InsertAtBack(card24);
        InsertAtBack(card25);

    }

    //// INSERT AT THE FRONT OF THE LIST
    public void InsertAtFront(Card dataToInsert) {
        Node temp = new Node(dataToInsert);
        if (temp != null) {
            if (Head == null) {
                Head = temp;
                Tail = temp;
                Tail.setNextNode(Head);
            } else {
                temp.setNextNode(Head);
                Tail.setNextNode(temp);
                Head = temp;
            }
        } else {
            System.err.println("Error! The list is full, cannot insert new node.");
        }
        Size++;
    }

    //// INSERT AT THE BACK OF THE LIST
    public void InsertAtBack(Card dataToInsert) {
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

    /// CHECKS IF THE LIST IS EMPTY
    public boolean isEmpty() {
        return Head == null;
    }

    /// DISPLAYS ALL CARDS IN LIST
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

    //// SELECTS NEXT NODE IN LIST
    public Node GoToNextNode() {// select players and cards
        Node current = Head;
        Node Data = new Node();
        if (!isEmpty()) {
            Data = current;
            Head = current.getNextNode();
            // Tail = current;
        } else {
            System.err.println("No player has been registered");
        }

        return Data;
    }

    ///// ADDS DATA TO LIST
    public void PopulateList(Card cardData, Player player, PuzzleData puzzle) {
        Node next = new Node();

        if (cardData != null) {
            next.setCardData(cardData);
        } else if (player != null) {
            next.setPlayerData(player);
        } else
            next.setRoundData(puzzle);

        if (isEmpty()) {
            Head = next;
            Tail = next;
        } else {
            Tail.setNextNode(next);
            next.setPreviousNode(Tail);
            Tail = next;
        }
        Tail.setNextNode(Head);
        Head.setPreviousNode(Tail);
    }

    //// RANDOMIZE CARDS IN THE LIST
    public Card SpinWheel() {
        Node currentCard = null;
        try {
            System.out.println("The wheel is spinning...");
            Thread.sleep(5000);
            currentCard = Head;
            Random random = new Random();
            int currentPosition = 0;
            int spaces = random.nextInt(51) + 50;
            for (int number = 0; number < spaces; number++) {
                currentCard = currentCard.getNextNode();
                currentPosition++;
            }
            // System.out.println("It took " + spaces + " spaces for you to land on: "
            // + currentCard.getCardData().getType() + " for $" +
            // currentCard.getCardData().getValue() + " !!");
        } catch (InterruptedException interruptedException) {
            System.err.println("ERROR: Interrupted Exception Occurred.");
        }
        return currentCard.getCardData();
    }

    //// FINDS A SPECIFIC PLAYER IN THE LIST
    public void Find(Player player) {
        if (isEmpty()) {
            System.out.println("No element present");
        }
    }
}
