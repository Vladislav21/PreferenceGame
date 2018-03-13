package main.model;

import java.util.*;

public class Distribution {
    // скорее всего надо
    private int distributuionNumber;
    private List<Card> cards1 = new ArrayList<>();
    private List<Card> cards2 = new ArrayList<>();
    private List<Card> cards3 = new ArrayList<>();
    private String botName1; // имена ботов
    private String botName2;
    private String botName3;
    private List<Card> cardsDistributor = new ArrayList<>(); // карты прикупа
    private String nameBot; // имя бота который начинает торговлю / чей ход
    private String strategyBot1;
    private String strategyBot2;
    private String strategyBot3;
    private String selectGame;
    private int bribe1; // взятки ботов
    private int bribe2;
    private int bribe3;
    private List<String> sequenceOfSteps;
    private int botBullet1;
    private int botBullet2;
    private int botBullet3;
    private int botHill1;
    private int botHill2;
    private int botHill3;

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

    public String getStrategyBot1() {
        return strategyBot1;
    }

    public void setStrategyBot1(String strategyBot1) {
        this.strategyBot1 = strategyBot1;
    }

    public String getStrategyBot2() {
        return strategyBot2;
    }

    public void setStrategyBot2(String strategyBot2) {
        this.strategyBot2 = strategyBot2;
    }

    public String getStrategyBot3() {
        return strategyBot3;
    }

    public void setStrategyBot3(String strategyBot3) {
        this.strategyBot3 = strategyBot3;
    }

    public String getSelectGame() {
        return selectGame;
    }

    public void setSelectGame(String selectGame) {
        this.selectGame = selectGame;
    }

    public int getBribe1() {
        return bribe1;
    }

    public void setBribe1(int bribe1) {
        this.bribe1 = bribe1;
    }

    public int getBribe2() {
        return bribe2;
    }

    public void setBribe2(int bribe2) {
        this.bribe2 = bribe2;
    }

    public int getBribe3() {
        return bribe3;
    }

    public void setBribe3(int bribe3) {
        this.bribe3 = bribe3;
    }

    public List<String> getSequenceOfSteps() {
        return sequenceOfSteps;
    }

    public void setSequenceOfSteps(List<String> sequenceOfSteps) {
        this.sequenceOfSteps = sequenceOfSteps;
    }

    public int getBotBullet1() {
        return botBullet1;
    }

    public void setBotBullet1(int botBullet1) {
        this.botBullet1 = botBullet1;
    }

    public int getBotBullet2() {
        return botBullet2;
    }

    public void setBotBullet2(int botBullet2) {
        this.botBullet2 = botBullet2;
    }

    public int getBotBullet3() {
        return botBullet3;
    }

    public void setBotBullet3(int botBullet3) {
        this.botBullet3 = botBullet3;
    }

    public int getBotHill1() {
        return botHill1;
    }

    public void setBotHill1(int botHill1) {
        this.botHill1 = botHill1;
    }

    public int getBotHill2() {
        return botHill2;
    }

    public void setBotHill2(int botHill2) {
        this.botHill2 = botHill2;
    }

    public int getBotHill3() {
        return botHill3;
    }

    public void setBotHill3(int botHill3) {
        this.botHill3 = botHill3;
    }
}
