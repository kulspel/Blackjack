import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kulspel on 2018-03-02.
 */
public class BlackjackGame {
    private ArrayList<Player> playerList;
    private Dealer dealer;
    private Random random;

    public BlackjackGame(int nrOfPlayers) {
        playerList = new ArrayList<Player>(nrOfPlayers);
        dealer = new Dealer();
        random = new Random();
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public void addPlayer(Player player, int index) {
        playerList.add(index, player);
    }

    public void deal() {
        dealer.deal(random);
        System.out.println("Dealer cards: " + '\n' + dealer.cardString() + '\n');

        int playernumber = 1;
        for (Player p : playerList) {
            p.deal(random);
            System.out.println(playernumber++ + " Player cards: " + '\n' + p.cardString() + '\n');
        }
    }

    public void playerRound() {

        Scanner scanner = new Scanner(System.in);

        int playernumber = 0;

        for (Player p : playerList) {
            System.out.println("----------------------------------------------------------------------------" + '\n');
            System.out.println("Player " + ++playernumber + "'s turn" + '\n');

            boolean stillPlaying = true;

            while (stillPlaying) {
                System.out.println("Player cards: " + '\n' + p.cardString() + '\n');
                System.out.println("Score: " + Arrays.toString(p.getScore()));
                System.out.println("Input your decision 1 to HIT, 2 to STAND");

                //TODO dont allow anything other than int
                int decision = scanner.nextInt();

                switch (decision) {
                    case 1:
                        p.hit(random);
                        boolean endRound = !p.isFat();
                        break;
                    case 2:
                        stillPlaying = false;
                        break;
                    default:
                        System.out.println("Invalid input ");
                        break;
                }

                //System.out.println("Player cards: " + '\n' + p.cardString() + '\n');
            }

        }
    }
}
