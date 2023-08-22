import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    ArrayList<Card> playersCards = new ArrayList<>();
    ArrayList<Card> knownCards = new ArrayList<>();
    Card unknownCard;
    boolean isUser;
    boolean isIn;
    int cardsValue;

    Player (Boolean isUser, Card cardOne, Card cardTwo) {
        playersCards.add(cardOne);
        knownCards.add(cardOne);

        playersCards.add(cardTwo);
        this.isUser = isUser;
        if (isUser) {
            knownCards.add(cardTwo);
        }
        else {
            unknownCard = cardTwo;
        }
        isIn = true;
    }

    public void hit (Deck deck) {
        Card card = deck.dealCard();
        knownCards.add(card);
        playersCards.add(card);
    }

    public void stand () {
        isIn = false;
    }

    public boolean isPlayerIn () {
        return isIn;
    }

    public boolean isUserPlaying () {
        return isUser;
    }

    public void isBust () {
        int a = 0;
        for (Card card : playersCards) {
            if (card.getVal() == Value.ACE) {
                a += aceValue();
                continue;
            }
            a += card.getVal().value;
        }
        if (a > 21) {
            isIn = false;
        }
        cardsValue = a;
    }

    public int getCardValue () {
        return cardsValue;
    }

    private int aceValue () {
        Scanner sc = new Scanner(System.in);
        int a;
        String temp;
        System.out.println("Ace high? or low?");
        while (true) {
            temp = sc.next();
            if (temp.equals("high")) {
                a = 11;
                break;
            }
            if (temp.equals("low")) {
                a = 1;
                break;
            }
            System.out.println("Invalid input");
        }
        return a;
    }
}
