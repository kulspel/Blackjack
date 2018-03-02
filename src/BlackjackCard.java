import java.util.Random;

/**
 * Created by Kulspel on 2018-03-02.
 */
public class BlackjackCard {
    private int suit;
    private int rank;

    // Kinds of suits
    public final static int DIAMONDS = 1;
    public final static int CLUBS    = 2;
    public final static int HEARTS   = 3;
    public final static int SPADES   = 4;

    public BlackjackCard(Random random) {
        suit = random.nextInt(4)+1;
        rank = random.nextInt(13)+1;

    }

    //Returns the value of the card in a blackjack system
    public int getRank() {
        return rank;
    }

    public String getSuit() {
        switch (suit) {
            case DIAMONDS:
                return "Diamonds";
            case CLUBS:
                return "Clubs";
            case HEARTS:
                return "Hearts";
            case SPADES:
                return "Spades";
            default:
                return null;
        }
    }
}
