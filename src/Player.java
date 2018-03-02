import java.util.ArrayList;

/**
 * Created by Kulspel on 2018-03-02.
 */
public class Player {
    private int balance;
    private ArrayList<BlackjackCard> cards;

    protected Player(){
        balance = Integer.MAX_VALUE;
        cards = new ArrayList<BlackjackCard>(2);
    }

    public Player(int deposit) {
        balance = deposit;
        cards = new ArrayList<BlackjackCard>(2);
    }
}
