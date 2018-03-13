package main.model;

import java.util.List;


public class Bot implements Cloneable {
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
    private int valueContract;
    private int colorContract;
    private boolean isContractor;
    private boolean isMiser;

    public Bot() {
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

    public int getValueContract() {
        return valueContract;
    }

    public void setValueContract(int valueContract) {
        this.valueContract = valueContract;
    }

    public int getColorContract() {
        return colorContract;
    }

    public void setColorContract(int colorContract) {
        this.colorContract = colorContract;
    }

    public boolean isContractor() {
        return isContractor;
    }

    public void setContractor(boolean contractor) {
        isContractor = contractor;
    }

    public boolean isMiser() {
        return isMiser;
    }

    public void setMiser(boolean miser) {
        isMiser = miser;
    }

}
