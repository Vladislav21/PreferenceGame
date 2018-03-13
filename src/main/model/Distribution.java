package main.model;

import java.util.*;

public class Distribution {
    // скорее всего надо
    private int distributuionNumber;
    private List<Card> cards1 = new ArrayList<>();
    private List<Card> cards2 = new ArrayList<>();
    private List<Card> cards3 = new ArrayList<>();
    private String botName1;
    private String botName2;
    private String botName3;
    private List<Card> cardsDistributor = new ArrayList<>(); // карты прикупа
    private String nameBot; // имя бота который начинает торговлю / чей ход


    public List<Card> getCards1() {
        return cards1;
    }

    public void setCards1(List<Card> cards1) {
        this.cards1 = cards1;
    }

    public List<Card> getCards2() {
        return cards2;
    }

    public void setCards2(List<Card> cards2) {
        this.cards2 = cards2;
    }

    public List<Card> getCards3() {
        return cards3;
    }

    public void setCards3(List<Card> cards3) {
        this.cards3 = cards3;
    }

    public String getBotName1() {
        return botName1;
    }

    public void setBotName1(String botName1) {
        this.botName1 = botName1;
    }

    public String getBotName2() {
        return botName2;
    }

    public void setBotName2(String botName2) {
        this.botName2 = botName2;
    }

    public String getBotName3() {
        return botName3;
    }

    public void setBotName3(String botName3) {
        this.botName3 = botName3;
    }

    public List<Card> getCardsDistributor() {
        return cardsDistributor;
    }

    public void setCardsDistributor(List<Card> cardsDistributor) {
        this.cardsDistributor = cardsDistributor;
    }

    public String getNameBot() {
        return nameBot;
    }

    public void setNameBot(String nameBot) {
        this.nameBot = nameBot;
    }

    public int getDistributuionNumber() {
        return distributuionNumber;
    }

    public void setDistributuionNumber(int distributuionNumber) {
        this.distributuionNumber = distributuionNumber;
    }
}
