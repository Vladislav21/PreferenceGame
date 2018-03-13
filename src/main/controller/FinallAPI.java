package main.controller;

import main.model.Card;
import main.model.Distribution;

import java.io.*;
import java.util.List;

public class FinallAPI {

    private final String FILE_NAME = "finaldata.txt";

    public void getDataForSpecificDistribution(List<Distribution> list, int distributionNumber) throws IOException {
        Distribution distribution = list.get(distributionNumber);
        String nameBot1 = distribution.getBotName1();
        String nameBot2 = distribution.getBotName2();
        String nameBot3 = distribution.getBotName3();
        String nameWinner = null; // имя того кто выиграл торговлю
        List<Card> cards1 = distribution.getCards1();
        List<Card> cards2 = distribution.getCards2();
        List<Card> cards3 = distribution.getCards3();
        List<Card> cards4 = distribution.getCardsDistributor();
        int check = (distributionNumber) + 1;

        System.out.println("Данные для " + check + " раздачи:");
        System.out.println("Карты " + nameBot1 + ":" + cards1);
        System.out.println("Карты " + nameBot2 + ":" + cards2);
        System.out.println("Карты " + nameBot3 + ":" + cards3);
        System.out.println("Прикуп:" + cards4);

        System.out.println("Торговлю выиграл:");
        if (distribution.getNameBot() != null) {
            nameWinner = distribution.getNameBot();
            System.out.println(nameWinner);
        } else {
            nameWinner = "Была выбрана распасовка";
            System.out.println(nameWinner);
        }

        FileWriter fw = new FileWriter(FILE_NAME,true);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        String data = "\nНомер раздачи: " + check
                + "\nКарты " + nameBot1 + ":" + cards1
                + "\nКарты " + nameBot2 + ":" + cards2
                + "\nКарты " + nameBot3 + ":" + cards3
                + "\nПрикуп:" + cards4
                + "\nТорговлю выиграл:" + nameWinner;
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

    public void getDataForSpecificDistributionOfTrade(List<Distribution> list, int distributionNumber){

    }

}
