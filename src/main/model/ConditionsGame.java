package main.model;

import org.apache.log4j.Logger;

public class ConditionsGame {

    private int amountOfDistribution; // количество раздач
    private int amountOfBullets; // количество пуль

    public ConditionsGame() {
    }

    public ConditionsGame(int amountOfDistribution, int amountOfBullets) {
        this.amountOfDistribution = amountOfDistribution;
        this.amountOfBullets = amountOfBullets;
    }

    public int getAmountOfDistribution() {
        return amountOfDistribution;
    }

    public void setAmountOfDistribution(int amountOfDistribution) {
        this.amountOfDistribution = amountOfDistribution;
    }

    public int getAmountOfBullets() {
        return amountOfBullets;
    }

    public void setAmountOfBullets(int amountOfBullets) {
        this.amountOfBullets = amountOfBullets;
    }
}
