package main.model;

import java.util.*;

public class Distribution {
    // скорее всего надо
    private int distributionNumber; // номер раздачи
    private List<Card> cardsBot1; // карты бота 1
    private List<Card> cardsBot2; // карты бота 2
    private List<Card> cardsBot3; // карты бота 3
    private String typeOfGame; // тип игры (контракт,мизер,расспас)
    private String botNameOfStartTrade; // имя бота начавшего трэйд
    private Map<String, String> reactionsOtherBots; // реакция других ботов на трэйд
    private String nameBotGettingRedemption; // имя бота получившего прикуп
    // под вопросом
    private String resultProcessTrade; // результат процесса розыгрыша(последовательность ходов и принадлежность взяток)
    private String resultProcessGame; //последовательность ходов и принадлежность взяток
    private String resultProcessFullGame; //раздача, торговля, заявка, игра, результаты

    public Distribution() {
    }

    public int getDistributionNumber() {
        return distributionNumber;
    }

    public void setDistributionNumber(int distributionNumber) {
        this.distributionNumber = distributionNumber;
    }

    public List<Card> getCardsBot1() {
        return cardsBot1;
    }

    public void setCardsBot1(List<Card> cardsBot1) {
        this.cardsBot1 = cardsBot1;
    }

    public List<Card> getCardsBot2() {
        return cardsBot2;
    }

    public void setCardsBot2(List<Card> cardsBot2) {
        this.cardsBot2 = cardsBot2;
    }

    public List<Card> getCardsBot3() {
        return cardsBot3;
    }

    public void setCardsBot3(List<Card> cardsBot3) {
        this.cardsBot3 = cardsBot3;
    }

    public String getTypeOfGame() {
        return typeOfGame;
    }

    public void setTypeOfGame(String typeOfGame) {
        this.typeOfGame = typeOfGame;
    }

    public String getBotNameOfStartTrade() {
        return botNameOfStartTrade;
    }

    public void setBotNameOfStartTrade(String botNameOfStartTrade) {
        this.botNameOfStartTrade = botNameOfStartTrade;
    }

    public Map<String, String> getReactionsOtherBots() {
        return reactionsOtherBots;
    }

    public void setReactionsOtherBots(Map<String, String> reactionsOtherBots) {
        this.reactionsOtherBots = reactionsOtherBots;
    }

    public String getNameBotGettingRedemption() {
        return nameBotGettingRedemption;
    }

    public void setNameBotGettingRedemption(String nameBotGettingRedemption) {
        this.nameBotGettingRedemption = nameBotGettingRedemption;
    }
}
