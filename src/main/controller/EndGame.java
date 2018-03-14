package main.controller;

import main.model.Bot;

import java.util.Arrays;

/**
 * Методы для обновления взяток и подсчета конечных вистов
 */
public class EndGame {
    public void refreshBribes(Bot bot1, Bot bot2, Bot bot3) {
        bot1.setBribe(0);
        bot2.setBribe(0);
        bot3.setBribe(0);
    }

    public void countVistaForBots(Bot bot1, Bot bot2, Bot bot3) {
        int[] arrayHills = {bot1.getHill(), bot2.getHill(), bot3.getHill()};
        int minHill = Arrays.stream(arrayHills).min().getAsInt();
        if (bot1.getHill() == minHill) {
            countVista(bot1, bot2, bot3);
        }
        if (bot2.getHill() == minHill) {
            countVista(bot2, bot1, bot3);
        }
        if (bot3.getHill() == minHill) {
            countVista(bot3, bot1, bot2);
        }

    }

    private void countVista(Bot botWithMinHill, Bot bot1, Bot bot2) {
        bot1.setHill(bot1.getHill() - botWithMinHill.getHill());
        bot2.setHill(bot2.getHill() - botWithMinHill.getHill());
        int VistaOnBot1 = (bot1.getHill() * 10) / 3;
        int VistaOnBot2 = (bot2.getHill() * 10) / 3;
        botWithMinHill.setVista1(VistaOnBot1);
        bot2.setVista1(VistaOnBot1);
        botWithMinHill.setVista2(VistaOnBot2);
        bot1.setVista1(VistaOnBot2);
        if (VistaOnBot1 > VistaOnBot2) {
            int difference = VistaOnBot1 - VistaOnBot2;
            bot2.setOwnVista(difference);
            bot1.setOwnVista(-difference);
            botWithMinHill.setOwnVista(botWithMinHill.getVista1() + botWithMinHill.getVista2());
        }
        if (VistaOnBot2 > VistaOnBot1) {
            int difference = VistaOnBot2 - VistaOnBot1;
            bot1.setOwnVista(difference);
            bot2.setOwnVista(-difference);
            botWithMinHill.setOwnVista(botWithMinHill.getVista1() + botWithMinHill.getVista2());
        }
    }
}
