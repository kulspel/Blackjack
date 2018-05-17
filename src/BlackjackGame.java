import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Kulspel on 2018-03-02.
 */

/**
 * TODO implement simpleblackjackGame that inherits from blackjackgame, or maybe the other way around, make
 * blackjackgame inherit from simpleblackjack. Implement hardcoreblackjackgame with all functionalities, double down
 * split etc and put that into the inheritance chain
 */
//TODO add Payouts
public class BlackjackGame {
    private ArrayList<Player> playerList;
    private Dealer dealer;
    private Random random;
    private HashMap<Player, BigDecimal> bets;
    private BigDecimal blackJackMod = new BigDecimal(("1.5"));

    public BlackjackGame(int nrOfPlayers) {
        playerList = new ArrayList<Player>(nrOfPlayers);
        dealer = new Dealer();
        random = new Random();
        bets = new HashMap<Player, BigDecimal>(nrOfPlayers);
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
        dealer.resetCards();

        for(Player p : playerList){
            p.resetCards();
        }
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

            System.out.println("Dealer cards: " + '\n' + dealer.cardString());
            System.out.println("Dealer score: " + Arrays.toString(dealer.getScore()) + '\n');

            System.out.println("Player " + ++playerNumber + "'s turn");
            System.out.println("Player cards: " + '\n' + p.cardString());
            System.out.println("Score: " + Arrays.toString(p.getScore()) + '\n');

            boolean stillPlaying = true;

            while (stillPlaying) {
                //TODO Add immediate payout when hitting blackjack
                if (p.getBestScore()==21) {
                    stillPlaying = false;
                    System.out.println("----------------------------");
                    System.out.println("Winner Winner Chicken Dinner");
                    System.out.println("-----You got BLACKJACK-----");
                    System.out.println("----------------------------");
                    continue;
                }

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


                System.out.println("Dealer cards: " + '\n' + dealer.cardString());
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
    //TODO fix dealer behaviour involving aces
    //TODO Fix the entire dealer behaviour use the implementation of bestscore?
    public void dealerRound() {
        System.out.println("Dealers' turn" + '\n');
        dealer.reveal();

        System.out.println("Dealer reveals cards: " + '\n' + dealer.cardString());
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

            if (stillPlaying) {
                dealer.hit(random);
                System.out.println("The dealer was dealt " + dealer.getLastCard() + '\n');
            }
            if (dealer.isFat()) {
                System.out.println("Dealer is fat!");
                stillPlaying = false;
            }
        }

    }

    public void payout() {
        int playerNumber = 0;

        System.out.println("***PAYOUT***PAYOUT***PAYOUT***PAYOUT***PAYOUT***PAYOUT***");
        for (Player p : playerList) {
            System.out.println("----------------------------------------------------------------------------" + '\n');
            System.out.println("Player " + ++playerNumber + " got a score of " + p.getBestScore());
            System.out.println("The dealer got a score of " + dealer.getBestScore() + '\n');

            if (p.isFat()) {
                System.out.println("You were fat and got no money" + '\n');
                continue;
            }else if (p.getBestScore()==21) {
                p.payout(bets.get(p).multiply(blackJackMod).add(bets.get((p))));
                System.out.println("You got blackjack and with your bet of " + bets.get(p) + " you earned " + bets.get(p).multiply(blackJackMod)+ '\n');
                continue;
            } else if(p.getBestScore()>dealer.getBestScore()){
                p.payout(bets.get(p).add(bets.get(p)));
                System.out.println("You got a score of " + p.getBestScore()+ " and the dealer got a score of " +dealer.getBestScore() + " with your bet of " + bets.get(p) + " you earned " + bets.get(p)+ '\n');
                continue;
            }else {
                System.out.println("You got a score of " + p.getBestScore()+ " and the dealer got a score of " +dealer.getBestScore() + " you earned no money "+ '\n');
                continue;
            }
        }
    }

    //TODO add checks for invalid betting
    public void bet() {
        Scanner scanner = new Scanner(System.in);
        int playerNumber = 0;

        System.out.println("***BETTING***BETTING***BETTING***BETTING***BETTING***");
        for (Player p : playerList) {
            System.out.println("----------------------------------------------------------------------------" + '\n');
            System.out.println("Player " + ++playerNumber + "'s turn to bet");
            System.out.println("Your current balance is: " + p.getBalance() + '\n');
            System.out.println("Input your bet:");

            //TODO dont allow anything other than int, negative or higher than current balance
            BigDecimal bet = scanner.nextBigDecimal();

            p.bet(bet);
            bets.put(p,bet);
            System.out.println("Your new balance is: " + p.getBalance()+ '\n');
        }
    }

    public void postWinnings() {
        System.out.println("Game ended");
        int playerNumber = 0;
        for (Player p : playerList) {
            System.out.println("Player " + ++playerNumber + "'s balance is" + p.getBalance());
        }
    }
}

