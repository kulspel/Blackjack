import java.util.Arrays;
import java.util.Random;

/**
 * Created by Kulspel on 2018-03-02.
 */

//TODO implement inheritance from Card
public class BlackjackCard {
    private int suit;
    private int rank;
    private boolean hidden;

    // Kinds of suits
    public final static int DIAMONDS = 1;
    public final static int CLUBS    = 2;
    public final static int HEARTS   = 3;
    public final static int SPADES   = 4;

    // Kinds of ranks
    public final static int ACE   = 1;
    public final static int DEUCE = 2;
    public final static int THREE = 3;
    public final static int FOUR  = 4;
    public final static int FIVE  = 5;
    public final static int SIX   = 6;
    public final static int SEVEN = 7;
    public final static int EIGHT = 8;
    public final static int NINE  = 9;
    public final static int TEN   = 10;
    public final static int JACK  = 11;
    public final static int QUEEN = 12;
    public final static int KING  = 13;

    public BlackjackCard(Random random) {
        suit = random.nextInt(4)+1;
        rank = random.nextInt(13)+1;
        hidden = false;

    }

    //Get the rank in a blackjack system
    public int[] getRank() {
        //System.out.println("Rank: " + Arrays.toString(blackjackRank[rank]));
        return blackjackRank[rank];
    }

    static final int[][] blackjackRank = {
            {0},{1,11},{2},{3},{4},{5},{6},{7},{8},{9},{10},{10},{10},{10}};

    protected void setHidden(){
        hidden = true;
    }

    protected void revealCard(){
        hidden = false;
    }

    public boolean isHidden(){
        return hidden;
    }

    public String toString(){

        if(this.isHidden()){
            return "Hidden card";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rankToString(rank));
        stringBuilder.append(" of ");
        stringBuilder.append(this.getSuit());

        return  stringBuilder.toString();
    }

    public static String rankToString(int rank) {
        switch (rank) {
            case ACE:
                return "Ace";
            case DEUCE:
                return "Deuce";
            case THREE:
                return "Three";
            case FOUR:
                return "Four";
            case FIVE:
                return "Five";
            case SIX:
                return "Six";
            case SEVEN:
                return "Seven";
            case EIGHT:
                return "Eight";
            case NINE:
                return "Nine";
            case TEN:
                return "Ten";
            case JACK:
                return "Jack";
            case QUEEN:
                return "Queen";
            case KING:
                return "King";
            default:
                //Handle an illegal argument.  There are generally two
                //ways to handle invalid arguments, throwing an exception
                //(see the section on Handling Exceptions) or return null
                return null;
        }
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
