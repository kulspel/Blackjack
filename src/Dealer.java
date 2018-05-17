import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kulspel on 2018-03-02.
 */

//TODO implement inheritance from player?
public class Dealer{
    private Player dealer;

    public Dealer(){
       dealer = new Player();
    }

    public void deal(Random random) {
        BlackjackCard dealerCard1= new BlackjackCard(random);
        BlackjackCard dealerCard2= new BlackjackCard(random);
        dealerCard2.setHidden();

        dealer.addCard(dealerCard1);
        dealer.addCard(dealerCard2);
    }

    public String cardString(){
        return dealer.cardString();
    }


    public int[] getScore() {
        return dealer.getScore();
    }

    public void reveal() {
        dealer.reveal();
    }

    public void hit(Random random) {
        dealer.hit(random);
    }

    public String getLastCard() {
       return dealer.getLastCard();
    }

    protected ArrayList<BlackjackCard> getCardList(){
        return dealer.getCardList();
    }

    public boolean isFat() {
        return dealer.isFat();
    }

    public int getBestScore() {
        return dealer.getBestScore();
    }

    public void resetCards() {
        dealer.resetCards();
    }
}
