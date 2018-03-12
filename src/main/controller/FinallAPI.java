package main.controller;

import main.model.Card;
import main.model.Distribution;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class FinallAPI {

    private final String FILE_NAME = "finaldata.txt";

    public void getDataForSpecificDistribution(List<Distribution> list, int distributionNumber) throws IOException {
        Distribution distribution = list.get(distributionNumber);
        String nameBot1 = distribution.getBot1().getName();
        String nameBot2 = distribution.getBot2().getName();
        String nameBot3 = distribution.getBot3().getName();
        String nameWinner = null; // имя того кто выиграл торговлю
        List<Card> cards1 = distribution.getBot1().getCards();
        List<Card> cards2 = distribution.getBot2().getCards();
        List<Card> cards3 = distribution.getBot3().getCards();
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

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        String data = "\nНомер раздачи: " + check
                + "\nКарты " + nameBot1 + ":" + cards1
                + "\nКарты " + nameBot2 + ":" + cards2
                + "\nКарты " + nameBot3 + ":" + cards3
                + "\nПрикуп:" + cards4
                + "\n Торговлю выиграл:" + nameWinner;
        oos.writeObject(data);
        oos.close();

    }
}
