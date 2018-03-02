import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kulspel on 2018-03-02.
 */
public class Player {
    private int balance;
    private ArrayList<BlackjackCard> cards;
    //private int roundScore;

    protected Player() {
        balance = Integer.MAX_VALUE;
        cards = new ArrayList<BlackjackCard>(2);
        //roundScore = 0;
    }

    public Player(int deposit) {
        balance = deposit;
        cards = new ArrayList<BlackjackCard>(2);
        //roundScore = 0;
    }

    public void addCard(BlackjackCard card) {
        cards.add(card);
    }

    public void deal(Random random) {

        BlackjackCard playerCard1 = new BlackjackCard(random);
        BlackjackCard playerCard2 = new BlackjackCard(random);

        this.addCard(playerCard1);
        this.addCard(playerCard2);
    }

    public void hit(Random random) {
        BlackjackCard playerCard = new BlackjackCard(random);
        this.addCard(playerCard);
    }

    public String cardString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (BlackjackCard c : cards) {
            stringBuilder.append(c.toString());
            stringBuilder.append(", ");
        }

        return stringBuilder.toString();
    }

    public boolean isFat() {
        boolean isFat=true;
        int[] scores = this.getScore();
        for(int i:scores){
            if(i<=21){
                isFat = false;
                //System.out.println("You're fat!");
            }
        }
        return isFat;
    }

    //TODO add The Ace is always valued at 11 unless that would result in the hand going over 21, in which case it is valued as 1
    //TODO implement score as an attribute and update when hitting
    //TODO Add trimScore func
    public int[] getScore() {
        int[] scores = new int[1];

        for (BlackjackCard c : cards) {
            int[] cardScore = new int[1];

            if(c.isHidden()) cardScore[0] = 0;
            else cardScore  = c.getRank();

            //This if statement is basically if statement is basically if there is an ace
            if (cardScore.length > 1) {
                scores = doubleSize(scores);

                for (int i = 0; i < scores.length / 2; i++) {
                    scores[i] += cardScore[0];
                    scores[scores.length / 2 + i] += cardScore[1];
                }
            } else {
                for (int i = 0; i < scores.length; i++) {
                    scores[i] += cardScore[0];
                }
            }
        }

        return scores;
    }

    private int[] doubleSize(int[] array) {
        int[] tmp = new int[array.length * 2];

        for (int i = 0; i < array.length; i++) {
            tmp[i] = array[i];
            tmp[array.length + i] = array[i];
        }
        return tmp;
    }

    public String getLastCard() {
        return cards.get(cards.size()-1).toString();
    }

    protected void reveal() {
        for(BlackjackCard c: cards){
            c.revealCard();
        }
    }

    protected ArrayList<BlackjackCard> getCardList(){
        return cards;
    }
}
