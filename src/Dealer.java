import java.util.Random;

/**
 * Created by Kulspel on 2018-03-02.
 */

//TODO add dealer behaviour
public class Dealer{
    private Player dealer;

    public Dealer(){
       dealer = new Player();
    }

    public void deal(Random random) {
        BlackjackCard dealerCard1= new BlackjackCard(random);
        BlackjackCard dealerCard2= new BlackjackCard(random);
        dealerCard2.switchHidden();

        dealer.addCard(dealerCard1);
        dealer.addCard(dealerCard2);
    }

    public String cardString(){
        return dealer.cardString();
    }


}
