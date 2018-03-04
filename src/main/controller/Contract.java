package main.controller;

import main.model.Bot;

public class Contract implements GameStrategy {
    private static int value;
    private static int color;

    public static int getValue() {
        return value;
    }

    public static void setValue(int value) {
        Contract.value = value;
    }

    public static int getColor() {
        return color;
    }

    public static void setColor(int color) {
        Contract.color = color;
    }

    @Override
    public void run(Bot bot1, Bot bot2, Bot bot3) {

    }
}