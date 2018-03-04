package main.model;

import main.controller.GameStrategy;

import java.util.List;

public class Bot {
    private int ID; // уникальный номер бота
    private String name; // имя бота
    private int hill; // гора
    private int bullet; // пуля
    private int bribe; // взятка
    private double ownVista; // собственная виста
    // висты относительно других ботов
    private double vista1;
    private double vista2;
    // Карты бота после раздачи
    private List<Card> cards;
    private GameStrategy strategy;


    public Bot() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHill() {
        return hill;
    }

    public void setHill(int hill) {
        this.hill = hill;
    }

    public int getBullet() {
        return bullet;
    }

    public void setBullet(int bullet) {
        this.bullet = bullet;
    }

    public int getBribe() {
        return bribe;
    }

    public void setBribe(int bribe) {
        this.bribe = bribe;
    }

    public double getOwnVista() {
        return ownVista;
    }

    public void setOwnVista(double ownVista) {
        this.ownVista = ownVista;
    }

    public double getVista1() {
        return vista1;
    }

    public void setVista1(double vista1) {
        this.vista1 = vista1;
    }

    public double getVista2() {
        return vista2;
    }

    public void setVista2(double vista2) {
        this.vista2 = vista2;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setStrategy(GameStrategy strategy) {
        this.strategy = strategy;
    }

    public GameStrategy getStrategy() {
        return strategy;
    }
    public void executeStrategy(Bot bot1, Bot bot2, Bot bot3){
        strategy.run(bot1,bot2,bot3);
    }
}
