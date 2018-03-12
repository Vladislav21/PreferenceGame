package main.model;

import java.util.*;

public class Distribution {
    // скорее всего надо
    private int distributuionNumber;
    private Bot bot1 = new Bot();
    private Bot bot2 = new Bot();
    private Bot bot3 = new Bot();
    private List<Card> cardsDistributor = new ArrayList<>(); // карты прикупа
    private String nameBot; // имя бота который начинает торговлю / чей ход

    public Bot getBot1() {
        return bot1;
    }

    public void setBot1(Bot bot1) {
        this.bot1 = bot1;
    }

    public Bot getBot2() {
        return bot2;
    }

    public void setBot2(Bot bot2) {
        this.bot2 = bot2;
    }

    public Bot getBot3() {
        return bot3;
    }

    public void setBot3(Bot bot3) {
        this.bot3 = bot3;
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
