import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        deck.checkCardsForDuplicates();
        //deck.showCardsInDeck();
        int a = askForPlayerCount();
        Player[] players = new Player[a];
        players[0] = new Player(true, deck.dealCard(), deck.dealCard());
        for (int b = 1; b < a; b++) {
            players[b] = new Player(false, deck.dealCard(), deck.dealCard());
            players[b].isBust();
        }
        int notInCount = 0;
        int playerNum = 1;
        while (notInCount < a) {
            notInCount = 0;
            playerNum = 1;
            System.out.println("round start");
            for (Player player : players) {
                player.isBust();
                System.out.println("Player " + playerNum + ")  " + player.getCardValue());
                playerNum++;
                if (!player.isPlayerIn()) {
                    notInCount++;
                    continue;
                }
                if (player.isUserPlaying()) {
                    hitOrStand(player, deck);
                }
                else {
                    if (Math.random() > .5) {
                        System.out.println("NPC hits!");
                        player.hit(deck);
                        player.isBust();
                    }
                    else {
                        System.out.println("NPC stands!");
                        player.stand();
                    }
                }
            }
        }
        int userWithHighestScore = 0;
        int highestScore = 0;
        for (int d = 0; d < players.length; d++) {
            if (players[d].getCardValue() > 22) {
                continue;
            }
            if (players[d].getCardValue() > highestScore) {
                userWithHighestScore = d;
                highestScore = players[d].getCardValue();
            }
        }
        System.out.println("Player " + userWithHighestScore + " Won!");
    }

    private static void hitOrStand (Player player, Deck deck) {
        Scanner sc = new Scanner(System.in);
        String temp;
        System.out.println("hit or stand?");
        while (true) {
            temp = sc.next();
            if (temp.equals("hit")) {
                player.hit(deck);
                player.isBust();
                break;
            }
            if (temp.equals("stand")) {
                player.stand();
                break;
            }
            System.out.println("Invalid input");
        }
    }

    private static int askForPlayerCount () {
        Scanner sc = new Scanner(System.in);
        int a;
        while (true) {
            System.out.println("Give a number");
            if (sc.hasNextInt()) {
                a = sc.nextInt();
                if (a > 4 ) {
                    System.out.println("Max of four players at this table!");
                    sc.nextLine();
                    continue;
                }
                if (a < 1) {
                    System.out.println("Minimum of one player at this table!");
                    sc.nextLine();
                }
                else {
                    break;
                }
            }
            else {
                System.out.println("not a number");
                sc.nextLine();
            }
        }
        return a;
    }
}