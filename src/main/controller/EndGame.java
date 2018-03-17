package main.controller;

import main.model.Bot;

import java.util.Arrays;
import java.util.stream.Stream;

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
        int maxValueOfBullet = Stream.of(bot1.getBullet(), bot2.getBullet(), bot3.getBullet()).max(Integer::compareTo).get();
        int minValueOfHill = Stream.of(maxValueOfBullet - bot1.getBullet() + bot1.getHill(), maxValueOfBullet - bot2.getBullet() + bot2.getHill(), maxValueOfBullet - bot3.getBullet() + bot3.getHill()).min(Integer::compareTo).get();
        int hill1 = maxValueOfBullet - bot1.getBullet() + bot1.getHill() - minValueOfHill;
        int hill2 = maxValueOfBullet - bot2.getBullet() + bot2.getHill() - minValueOfHill;
        int hill3 = maxValueOfBullet - bot3.getBullet() + bot3.getHill() - minValueOfHill;
        double Vista2_1 = hill1 * 2.5 + bot2.getVista1();
        double Vista3_1 = hill1 * 2.5 + bot3.getVista1();
        double Vista1_2 = hill2 * 2.5 + bot1.getVista1();
        double Vista3_2 = hill2 * 2.5 + bot3.getVista2();
        double Vista1_3 = hill3 * 2.5 + bot1.getVista2();
        double Vista2_3 = hill3 * 2.5 + bot2.getVista2();
        double FVista1_2;
        double FVista2_1;
        double FVista1_3;
        double FVista3_1;
        double FVista2_3;
        double FVista3_2;
        if (Vista1_2 > Vista2_1) {
            FVista1_2 = Vista1_2 - Vista2_1;
            FVista2_1 = -FVista1_2;
        } else {
            FVista2_1 = Vista2_1 - Vista1_2;
            FVista1_2 = -FVista2_1;
        }

        if (Vista1_3 > Vista3_1) {
            FVista1_3 = Vista1_3 - Vista3_1;
            FVista3_1 = -FVista1_3;
        } else {
            FVista3_1 = Vista3_1 - Vista1_3;
            FVista1_3 = -FVista3_1;
        }

        if (Vista2_3 > Vista3_2) {
            FVista2_3 = Vista2_3 - Vista3_2;
            FVista3_2 = -FVista2_3;
        } else {
            FVista3_2 = Vista3_2 - Vista2_3;
            FVista2_3 = -FVista3_2;
        }
        bot1.setOwnVista(FVista1_2 + FVista1_3);
        bot2.setOwnVista(FVista2_1 + FVista2_3);
        bot3.setOwnVista(FVista3_1 + FVista3_2);
    }
}
