package main;

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

    // draw a card from a given deck and add it to the players cards.
    public void hit (Deck deck) {
        Card card = deck.dealCard();

        // blackjack ace handling
        System.out.println("Player pulls a " + card.getVal() + " of " + card.getSuit());
        if (card.getVal() == Value.ACE) {
            card.decideValue(aceValue());
        }
        //

        knownCards.add(card);
        playersCards.add(card);

        // evaluates cards
        isBust();
        System.out.println("Players cards are now valued at: " + cardsValue);
        //
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

    // checks if the user is busted, but also scores the user
    public void isBust () {
        int a = 0;
        for (Card card : playersCards) {
            if (card.getVal() == Value.ACE) {
                a += card.getDecidedValue();
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

        // blackjack handling, the computer decides
        // if ace is high or low based on its score.
        if (!isUser) {
            if ((cardsValue + 11) < 22) {
                return 11;
            }
            return 1;
        }
        //

        // ask the user what value they want their ace
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
