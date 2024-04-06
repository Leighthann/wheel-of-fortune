/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */

public class Queue {
    /// DECLARE OBJECTS
    private Node Front;
    private Node Rear;

    //// CONSTRUCTORS
    public Queue() {/// DEFAULT
        Front = null;
        Rear = null;
    }

    public Queue(Node front, Node rear) {/// PRIMARY
        Front = front;
        Rear = rear;
    }

    public Queue(Queue queue) {/// COPY
        Front = queue.Front;
        Rear = queue.Rear;
    }

    /// GETTERS
    public Node getFront() {
        return Front;
    }

    public Node getRear() {
        return Rear;
    }

    /// SETTERS
    public void setFront(Node front) {
        Front = front;
    }

    public void setRear(Node rear) {
        Rear = rear;
    }

    /// INSERT IN QUEUES
    public void Enqueue(Guess guessData) {//// ADDS DATA TO QUEUE
        Node temp = new Node(guessData);
        if (temp != null) {
            if (Front == null) {
                Front = temp;
                Rear = temp;
            } else {
                Rear.setNextNode(temp);
                temp.setPreviousNode(Rear);
                Rear = temp;
            }
        } else {
            System.err.println("ERROR: The list is full so it cannot add another node.");
        }
    }

    public void EnqueueVowels(Guess guessData) {//// ADDS DATA TO QUEUE
        Node temp = new Node(guessData);
        if (temp != null) {
            if (Front == null) {
                Front = temp;
                Rear = temp;
            } else {
                Rear.setNextNode(temp);
                temp.setPreviousNode(Rear);
                Rear = temp;
            }
        } else {
            System.err.println("ERROR: The list is full so it cannot add another node.");
        }
    }

    /// REMOVE DATA FROM QUEUE
    public Guess Dequeue() {
        Guess dataToReturn = new Guess();
        if (Front != null) {
            if (Front == Rear) {
                Rear = null;
            }
            Node temp = Front;
            dataToReturn = Front.getGuessData();
            Front = Front.getNextNode();
            if (Front != null) {
                Front.setPreviousNode(null);
            }
            temp = null;
        }
        return dataToReturn;
    }

    /// RETURN GUESS AT THE FRONT OF THE QUEUE
    public Guess queueFront() {
        if (Front == null) {
            System.err.println("ERROR: The queue is empty so it cannot return value(s).");
            return null;
        } else {
            return Front.getGuessData();
        }
    }

    /// COUNT NODES IN THE QUEUE
    public int CountNodes() {
        int count = 0;
        Queue tempQueue = new Queue();
        while (Front != null) {
            tempQueue.Enqueue(Dequeue());
            count++;
        }
        while (tempQueue.getFront() != null) {
            Enqueue(tempQueue.Dequeue());
        }
        return count;
    }

    //// CHECKS IF GUESS (LETTER) IN THE QUEUES
    public boolean Contains(Guess guess) {

        if (!isEmpty()) {
            Queue temp = new Queue();/* create a temporary Queue */
            Guess current;
            boolean found = false;
            while (!isEmpty() && !found) {
                current = Dequeue();
                if (current.getGuessedLetter().equals(guess.getGuessedLetter())) {
                    found = true;
                }
                temp.Enqueue(current); /* Dequeue from original and Enqueue into temporary Queue */
            }
            while (temp.Front != null) {
                Enqueue(temp.Dequeue());/* Dequeue from temporary and Enqueue into original Queue */
            }
            if (found)
                return true;
        }
        return false;
    }

    //// DISPLAY ALL DATA IN QUEUE
    public void DisplayQueue(String category) {
        Queue tempQueue = new Queue();
        Guess guess = new Guess();

        while (!(Front == null)) {

            guess = Dequeue();

            if (category.equals("DEFAULT")) {
                if (guess.getGuessedLetter().equalsIgnoreCase("A") | guess.getGuessedLetter().equalsIgnoreCase("E")
                        | guess.getGuessedLetter().equalsIgnoreCase("I")
                        | guess.getGuessedLetter().equalsIgnoreCase("O")
                        | guess.getGuessedLetter().equalsIgnoreCase("U")) {
                    guess.display();
                }
            } else {
                if (category.equals("SPECIAL")) {
                    if (!(guess.getGuessedLetter().equalsIgnoreCase("A")
                            | guess.getGuessedLetter().equalsIgnoreCase("E")
                            | guess.getGuessedLetter().equalsIgnoreCase("I")
                            | guess.getGuessedLetter().equalsIgnoreCase("O")
                            | guess.getGuessedLetter().equalsIgnoreCase("U"))) {
                        guess.display();
                    }
                }
            }
            tempQueue.Enqueue(guess);
        }
        while (!tempQueue.isEmpty()) {
            guess = tempQueue.Dequeue();
            Enqueue(guess);
        }
    }

    public void DisplayVowels() {
        Queue tempQueue = new Queue();
        Guess guess;

        while (!(Front == null)) {
            guess = Dequeue();
            guess.display();
            tempQueue.EnqueueVowels(guess);

        }

        while (!tempQueue.isEmpty()) {
            guess = tempQueue.Dequeue();
            EnqueueVowels(guess);
        }
    }

    //// EMPTY QUEUE
    public void DestroyQueue() {
        while (true) {
            Dequeue();
        }
    }

    /// CHECKS IF QUEUES IS EMPTY
    boolean isEmpty() {
        return Front == null;
    }
}
