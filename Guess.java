/*
 * DEVELOPERS: NATHAN BETTON(2001602), BRIANA TAYLOR(2100212), LEIGH-ANN CROSS (2100463), AND ALEEKA CHERRINGTON(2100951)
 * PROGRAM: WHEEL OF FORTUNE
 *
 *
 * */
public class Guess {

    public String GuessedLetter;

    Guess() {
        GuessedLetter = " ";
    }

    Guess(String guessedLetter) {
        GuessedLetter = guessedLetter;
    }

    Guess(Guess guess) {
        GuessedLetter = guess.GuessedLetter;
    }

    public String getGuessedLetter() {
        return GuessedLetter;
    }

    public void setGuessedLetter(String guessedLetter) {
        GuessedLetter = guessedLetter;
    }

    // public String toString(){
    // return GuessedLetter + "\n";
    // }

    public String toString() {
        return GuessedLetter;
    }

    public void display() {
        System.out.print(toString().replaceAll("", " "));
    }
}