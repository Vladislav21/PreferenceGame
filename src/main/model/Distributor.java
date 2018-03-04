package main.model;

import java.util.List;

public class Distributor {


    private String name; // имя раздающего, чтобы не обижать его =)
    private List<Card> cards; // прикуп


    public Distributor() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }


}
