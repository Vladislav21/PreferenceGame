package main.model;

import java.util.*;

public class Deck {

    // Номера карт в одной масти:
    private final static int _7 = 0;
    private final static int _8 = 1;
    private final static int _9 = 2;
    private final static int _10 = 3;
    private final static int JACK = 4;
    private final static int QUEEN = 5;
    private final static int KING = 6;
    private final static int ACE = 7;

    // Номера мастей:
    private final static int SPADES = 0; // Пики
    private final static int CLUBS = 1; // Трефы
    private final static int DIAMONDS = 2; // Бубны
    private final static int HEARTS = 3; // Червы

    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        filling(cards);
    }

    public void filling(List<Card> cards) {

        cards.add(new Card(SPADES, _7));
        cards.add(new Card(SPADES, _8));
        cards.add(new Card(SPADES, _9));
        cards.add(new Card(SPADES, _10));
        cards.add(new Card(SPADES, JACK));
        cards.add(new Card(SPADES, QUEEN));
        cards.add(new Card(SPADES, KING));
        cards.add(new Card(SPADES, ACE));

        cards.add(new Card(CLUBS, _7));
        cards.add(new Card(CLUBS, _8));
        cards.add(new Card(CLUBS, _9));
        cards.add(new Card(CLUBS, _10));
        cards.add(new Card(CLUBS, JACK));
        cards.add(new Card(CLUBS, QUEEN));
        cards.add(new Card(CLUBS, KING));
        cards.add(new Card(CLUBS, ACE));

        cards.add(new Card(DIAMONDS, _7));
        cards.add(new Card(DIAMONDS, _8));
        cards.add(new Card(DIAMONDS, _9));
        cards.add(new Card(DIAMONDS, _10));
        cards.add(new Card(DIAMONDS, JACK));
        cards.add(new Card(DIAMONDS, QUEEN));
        cards.add(new Card(DIAMONDS, KING));
        cards.add(new Card(DIAMONDS, ACE));

        cards.add(new Card(HEARTS, _7));
        cards.add(new Card(HEARTS, _8));
        cards.add(new Card(HEARTS, _9));
        cards.add(new Card(HEARTS, _10));
        cards.add(new Card(HEARTS, JACK));
        cards.add(new Card(HEARTS, QUEEN));
        cards.add(new Card(HEARTS, KING));
        cards.add(new Card(HEARTS, ACE));
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
