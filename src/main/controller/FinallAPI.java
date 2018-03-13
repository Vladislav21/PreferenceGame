package main.controller;

import main.model.Card;
import main.model.Distribution;
import main.model.Distributor;

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

        FileWriter fw = new FileWriter(FILE_NAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        String data = "\nНомер раздачи: " + check
                + "\nКарты " + nameBot1 + ":" + cards1
                + "\nКарты " + nameBot2 + ":" + cards2
                + "\nКарты " + nameBot3 + ":" + cards3
                + "\nПрикуп:" + cards4
                + "\nТорговлю выиграл:" + nameWinner + "\n";
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

    public void getDataForSpecificDistributionOfTrade(List<Distribution> list, int distributionNumber) throws IOException {
        Distribution distribution = list.get(distributionNumber);
        String nameBot1 = distribution.getBotName1();
        String nameBot2 = distribution.getBotName2();
        String nameBot3 = distribution.getBotName3();
        String strategyBot1 = distribution.getStrategyBot1();
        String strategyBot2 = distribution.getStrategyBot2();
        String strategyBot3 = distribution.getStrategyBot3();
        String selectGame = distribution.getSelectGame();
        List<Card> cards = distribution.getCardsDistributor();
        int check = (distributionNumber) + 1;

        System.out.println("Данные для " + check + " раздачи о процессе торговли:");
        System.out.println("Заявка для " + nameBot1 + ":" + strategyBot1);
        System.out.println("Заявка для " + nameBot2 + ":" + strategyBot2);
        System.out.println("Заявка для " + nameBot3 + ":" + strategyBot3);
        System.out.println("Выбранная игра:" + selectGame);
        System.out.println("Прикуп:" + cards);

        FileWriter fw = new FileWriter(FILE_NAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        String data = "\nНомер раздачи: " + check
                + "\nЗаявка для " + nameBot1 + ":" + strategyBot1
                + "\nЗаявка для " + nameBot2 + ":" + strategyBot2
                + "\nЗаявка для " + nameBot3 + ":" + strategyBot3
                + "\nВыбранная игра:" + selectGame
                + "\nПрикуп:" + cards + "\n";
        bufferedWriter.write(data);
        bufferedWriter.close();

    }

    public void getCardProcess(List<Distribution> list, int distributionNumber) throws IOException {
        Distribution distribution = list.get(distributionNumber);
        String nameBot1 = distribution.getBotName1();
        String nameBot2 = distribution.getBotName2();
        String nameBot3 = distribution.getBotName3();
        List<String> sequenceOfSteps = distribution.getSequenceOfSteps();
        int bribe1 = distribution.getBribe1();
        int bribe2 = distribution.getBribe2();
        int bribe3 = distribution.getBribe3();
        int check = (distributionNumber) + 1;

        System.out.println("Данные для " + check + " раздачи о процессе розыгрыша:");
        System.out.println("Взятка для " + nameBot1 + ":" + bribe1);
        System.out.println("Взятка для " + nameBot2 + ":" + bribe2);
        System.out.println("Взятка для " + nameBot3 + ":" + bribe3);
        System.out.println("Последовательность ходов:" + sequenceOfSteps);

        FileWriter fw = new FileWriter(FILE_NAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        String data = "\nДанные для " + check + " раздачи о процессе розыгрыша:"
                + "\nВзятка для " + nameBot1 + ":" + bribe1
                + "\nВзятка для " + nameBot2 + ":" + bribe2
                + "\nВзятка для " + nameBot3 + ":" + bribe3
                + "\nПоследовательность ходов:" + sequenceOfSteps + "\n";
        bufferedWriter.write(data);
        bufferedWriter.close();


    }

    public void getResultCardProcess(List<Distribution> list, int distributionNumber) throws IOException {
        Distribution distribution = list.get(distributionNumber);
        String nameBot1 = distribution.getBotName1();
        String nameBot2 = distribution.getBotName2();
        String nameBot3 = distribution.getBotName3();
        int bribe1 = distribution.getBribe1();
        int bribe2 = distribution.getBribe2();
        int bribe3 = distribution.getBribe3();
        int botBullet1 = distribution.getBotBullet1();
        int botBullet2 = distribution.getBotBullet2();
        int botBullet3 = distribution.getBotBullet3();
        int botHill1 = distribution.getBotHill1();
        int botHill2 = distribution.getBotHill2();
        int botHill3 = distribution.getBotHill3();
        int check = (distributionNumber) + 1;
        System.out.println("Данные для " + check + " раздачи о результате розыгрыша:");
        System.out.println("Взятка для " + nameBot1 + ":" + bribe1);
        System.out.println("Взятка для " + nameBot2 + ":" + bribe2);
        System.out.println("Взятка для " + nameBot3 + ":" + bribe3);
        System.out.println("Пуля и гора соответственно для " + nameBot1 + ":" + botBullet1 + " и " + botHill1);
        System.out.println("Пуля и гора соответственно для " + nameBot2 + ":" + botBullet2 + " и " + botHill2);
        System.out.println("Пуля и гора соответственно для " + nameBot3 + ":" + botBullet3 + " и " + botHill3);

        FileWriter fw = new FileWriter(FILE_NAME, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        String data = "\nДанные для " + check + " раздачи о результате розыгрыша:"
                + "\nВзятка для " + nameBot1 + ":" + bribe1
                + "\nВзятка для " + nameBot2 + ":" + bribe2
                + "\nВзятка для " + nameBot3 + ":" + bribe3
                + "\nПуля и гора соответственно для " + nameBot1 + ":" + botBullet1 + " и " + botHill1
                + "\nПуля и гора соответственно для " + nameBot2 + ":" + botBullet2 + " и " + botHill2
                + "\nПуля и гора соответственно для " + nameBot3 + ":" + botBullet3 + " и " + botHill3 + "\n";
        bufferedWriter.write(data);
        bufferedWriter.close();
    }

}
