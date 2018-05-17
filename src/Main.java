import java.math.BigDecimal;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    Random random = new Random();

        //BlackjackCard blackjackCard= new BlackjackCard(random);
        //System.out.println(blackjackCard.getRank() + '\n');

        int nrOfPlayers = 1;
        BlackjackGame bG = new BlackjackGame(nrOfPlayers);

        for(int i = 1; i<=nrOfPlayers;i++){
            Player p = new Player(new BigDecimal("10"));
            bG.addPlayer(p);
        }

        for(int i = 1; i<=10;i++) {
            bG.bet();
            bG.deal();
            //TODO add insurance protocol
            bG.playerRound();
            bG.dealerRound();
            bG.payout();
        }


        bG.postWinnings();
    }
}
