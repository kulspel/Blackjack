import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    Random random = new Random();

        BlackjackCard blackjackCard= new BlackjackCard(random);
        System.out.println(blackjackCard.getRank());
    }
}
