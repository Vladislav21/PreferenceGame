package main.model;

public class Card {
    private int color; // масть
    private int value; // значение
    private boolean isTrumpCard; // проверка на козырь
    //
    private static String str = null; // строковая перменная для вывода карты

    public Card() {
    }

    public Card(int color, int value, boolean isTrumpCard) {
        this.color = color;
        this.value = value;
        this.isTrumpCard = isTrumpCard;
    }

    public Card(int color, int value) {
        this.color = color;
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isTrumpCard() {
        return isTrumpCard;
    }

    public void setTrumpCard(boolean trumpCard) {
        isTrumpCard = trumpCard;
    }

    public static String getCard(Card card) {

        if (card.getColor() == 0) {
            switch (card.getValue()) {
                case -1:
                    str = "SPADES" + " 6";
                    break;
                case 0:
                    str = "SPADES" + " 7";
                    break;
                case 1:
                    str = "SPADES" + " 8";
                    break;
                case 2:
                    str = "SPADES" + " 9";
                    break;
                case 3:
                    str = "SPADES" + " 10";
                    break;
                case 4:
                    str = "SPADES" + " JACK";
                    break;
                case 5:
                    str = "SPADES" + " QUEEN";
                    break;
                case 6:
                    str = "SPADES" + " KING";
                    break;
                case 7:
                    str = "SPADES" + " ACE";
                    break;
            }
        }
        if (card.getColor() == 1) {
            switch (card.getValue()) {
                case -1:
                    str = "CLUBS" + " 6";
                    break;
                case 0:
                    str = "CLUBS" + " 7";
                    break;
                case 1:
                    str = "CLUBS" + " 8";
                    break;
                case 2:
                    str = "CLUBS" + " 9";
                    break;
                case 3:
                    str = "CLUBS" + " 10";
                    break;
                case 4:
                    str = "CLUBS" + " JACK";
                    break;
                case 5:
                    str = "CLUBS" + " QUEEN";
                    break;
                case 6:
                    str = "CLUBS" + " KING";
                    break;
                case 7:
                    str = "CLUBS" + " ACE";
                    break;
            }
        }
        if (card.getColor() == 2) {
            switch (card.getValue()) {
                case -1:
                    str = "DIAMONDS" + " 6";
                    break;
                case 0:
                    str = "DIAMONDS" + " 7";
                    break;
                case 1:
                    str = "DIAMONDS" + " 8";
                    break;
                case 2:
                    str = "DIAMONDS" + " 9";
                    break;
                case 3:
                    str = "DIAMONDS" + " 10";
                    break;
                case 4:
                    str = "DIAMONDS" + " JACK";
                    break;
                case 5:
                    str = "DIAMONDS" + " QUEEN";
                    break;
                case 6:
                    str = "DIAMONDS" + " KING";
                    break;
                case 7:
                    str = "DIAMONDS" + " ACE";
                    break;
            }
        }
        if (card.getColor() == 3) {
            switch (card.getValue()) {
                case -1:
                    str = "HEARTS" + " 6";
                    break;
                case 0:
                    str = "HEARTS" + " 7";
                    break;
                case 1:
                    str = "HEARTS" + " 8";
                    break;
                case 2:
                    str = "HEARTS" + " 9";
                    break;
                case 3:
                    str = "HEARTS" + " 10";
                    break;
                case 4:
                    str = "HEARTS" + " JACK";
                    break;
                case 5:
                    str = "HEARTS" + " QUEEN";
                    break;
                case 6:
                    str = "HEARTS" + " KING";
                    break;
                case 7:
                    str = "HEARTS" + " ACE";
                    break;
            }
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (color != card.color) return false;
        if (value != card.value) return false;
        return isTrumpCard == card.isTrumpCard;
    }

    @Override
    public int hashCode() {
        int result = color;
        result = 31 * result + value;
        result = 31 * result + (isTrumpCard ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return Card.getCard(new Card(color, value));
    }
}
