import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Kulspel on 2018-03-02.
 */
//TODO add Payouts
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

        int playerNumber = 0;

        for (Player p : playerList) {
            System.out.println("----------------------------------------------------------------------------" + '\n');

            System.out.println('\n' + "Dealer cards: " + '\n' + dealer.cardString() + '\n');
            System.out.println("Dealer score: " + Arrays.toString(dealer.getScore()) + '\n');

            System.out.println("Player " + ++playerNumber + "'s turn" + '\n');
            System.out.println("Player cards: " + '\n' + p.cardString());
            System.out.println("Score: " + Arrays.toString(p.getScore()) + '\n');

            boolean stillPlaying = true;

            while (stillPlaying) {
                System.out.println("Input your decision 1 to HIT, 2 to STAND");

                //TODO dont allow anything other than int
                int decision = scanner.nextInt();

                //TODO add split, Double Down and Surrender decision
                switch (decision) {
                    case 1:
                        p.hit(random);
                        System.out.println("You were dealt " + p.getLastCard() + '\n');
                        stillPlaying = !p.isFat();
                        break;
                    case 2:
                        System.out.println("You decided to stay " + '\n');
                        stillPlaying = false;
                        break;
                    default:
                        System.out.println("Invalid input " + '\n');
                        break;
                }



                System.out.println("Dealer cards: " + '\n' + dealer.cardString() + '\n');
                System.out.println("Dealer score: " + Arrays.toString(dealer.getScore()) + '\n');
                //System.out.println("Player cards: " + '\n' + p.cardString() + '\n');

                System.out.println("Player cards: " + '\n' + p.cardString());
                System.out.println("Player score: " + Arrays.toString(p.getScore()));
                if (p.isFat()) System.out.println("You're fat!");
                System.out.println("----------------------------------------------------------------------------" + '\n');
            }
            return;
        }
    }


    //TODO add dealer behaviour, hard or soft 17/18
    public void dealerRound() {
        System.out.println("Dealers' turn" + '\n');
        dealer.reveal();

        System.out.println("Dealer reveals cards: " + '\n' + dealer.cardString() + '\n');
        System.out.println("Dealer score: " + Arrays.toString(dealer.getScore()) + '\n');

        boolean stillPlaying = true;

        while (stillPlaying) {
            //System.out.println("Dealer score: " + Arrays.toString(dealer.getScore()) + '\n');

            //TODO add proper fat behaviour
            for (int score : dealer.getScore()) {
                //System.out.println("Dealer score " + score  + '\n');
                if (score >= 18) {
                    stillPlaying = false;
                    System.out.println("The dealer achieved a score of " + score + " and stands" + '\n');
                    break;
                }
            }

            if(stillPlaying){
                dealer.hit(random);
                System.out.println("The dealer was dealt " + dealer.getLastCard() + '\n');
            }
            if (dealer.isFat()){
                System.out.println("Dealer is fat!");
                stillPlaying = false;
            }
        }

    }

    //TODO add win/lose condition
}
