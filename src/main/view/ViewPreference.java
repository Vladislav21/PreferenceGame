package main.view;

import main.Main;
import main.controller.*;
import main.model.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ViewPreference {

    private GameStrategy contract;
    private GameStrategy misery;
    private GameStrategy unpacking;
    private Bot bot1;
    private Bot bot2;
    private Bot bot3;
    private Distributor distributor;
    private ProcessOfTrade pof;
    private Deck deck;
    private Logger log;
    private InitializationGame init;
    private StartGame start;
    private EndGame endGame;
    private FinallAPI finallAPI;

    public ViewPreference() {
        contract = new Contract();
        misery = new Misery();
        unpacking = new Unpacking();
        bot1 = new Bot();
        bot2 = new Bot();
        bot3 = new Bot();
        distributor = new Distributor();
        pof = new ProcessOfTrade();
        deck = new Deck();
        log = Logger.getLogger(Main.class.getName());
        init = new InitializationGame();
        start = new StartGame();
        endGame = new EndGame();
        finallAPI = new FinallAPI();
    }

    public void startPreference() throws IOException, CloneNotSupportedException {
        List<Distribution> distributionList = new ArrayList<>();
        menuBeforeGame();
        Scanner scanner = new Scanner(System.in);
        String nameBot1 = null;
        String nameBot2 = null;
        String nameBot3 = null;
        String nameDistributer = null;
        int countDistribution = 0;
        int select = scanner.nextInt();
        switch (select) {
            case 1:
                System.out.println("Инициализация игры. Запишите имена ботов(3 штуки) и раздающего, также выберите условие концовки игры:");
                System.out.println("Имя первого бота:");
                nameBot1 = scanner.next();
                System.out.println("Имя второго бота:");
                nameBot2 = scanner.next();
                System.out.println("Имя третьего бота:");
                nameBot3 = scanner.next();
                System.out.println("Имя раздающего:");
                nameDistributer = scanner.next();
                System.out.println("Выберите условие концовки игры (1 - по количеству раздач(min-10), 2 - по количеству общих пуль:");
                int selectEnd = scanner.nextInt();
                switch (selectEnd) {
                    case 1:
                        System.out.println("Введите количество раздачи (не менее 10):");
                        int amountOfDistribution = scanner.nextInt();
                        Distribution distribution;
                        while (countDistribution < amountOfDistribution) {
                            distribution = doApp(countDistribution, nameBot1, nameBot2, nameBot3, nameDistributer, init, start, bot1, bot2, bot3, distributor, log, deck, pof, endGame);
                            distributionList.add(distribution);
                            countDistribution = distribution.getDistributuionNumber();
                        }
                        endGame.countVistaForBots(bot1, bot2, bot3);
                        log.info("\n------------------------------- Результат по очкам -------------------------------");
                        log.info("\n" + bot1.getName() + ": " + bot1.getOwnVista());
                        log.info("\n" + bot2.getName() + ": " + bot2.getOwnVista());
                        log.info("\n" + bot3.getName() + ": " + bot3.getOwnVista());
                        log.info("\n----------------------------------------------------------------------------------");

                        break;
                    case 2:
                        System.out.println("Введите количество общих пуль для концовки ( не более 35):");
                        int amountOfBullets = scanner.nextInt();
                        while ((bot1.getBullet() + bot2.getBullet() + bot3.getBullet()) < amountOfBullets) {
                            distribution = doApp(countDistribution, nameBot1, nameBot2, nameBot3, nameDistributer, init, start, bot1, bot2, bot3, distributor, log, deck, pof, endGame);
                            distributionList.add(distribution);
                            countDistribution = distribution.getDistributuionNumber();
                        }
                        endGame.countVistaForBots(bot1, bot2, bot3);
                        log.info("\n------------------------------- Результат по очкам -------------------------------");
                        log.info("\n" + bot1.getName() + ": " + bot1.getOwnVista());
                        log.info("\n" + bot2.getName() + ": " + bot2.getOwnVista());
                        log.info("\n" + bot3.getName() + ": " + bot3.getOwnVista());
                        log.info("\n----------------------------------------------------------------------------------");
                        break;
                    default:
                        System.out.println("Действий с такой цифрой нет. Запустите заного.");
                        break;
                }
                break;
            case 2:
                System.out.println("Всего доброго! Для старта запустите приложение сного");
                break;
            default:
                System.out.println("Действий с такой цифрой нет. Запустите заного.");
                break;
        }
        boolean flag = true;
        while (flag) {
            menuAfterGame();
            int selectMethod = scanner.nextInt();
            switch (selectMethod) {
                case 1:
                    System.out.println("Введите номер раздачи для которой хотите получить информацию:");
                    int selectDistribution = scanner.nextInt();
                    finallAPI.getDataForSpecificDistribution(distributionList, selectDistribution - 1);
                    break;
                case 2:
                    System.out.println("Введите номер раздачи для которой хотите получить информацию:");
                    int selectDistribution1 = scanner.nextInt();
                    finallAPI.getDataForSpecificDistributionOfTrade(distributionList, selectDistribution1 - 1);
                    break;
                case 3:
                    System.out.println("Введите номер раздачи для которой хотите получить информацию:");
                    int selectDistribution2 = scanner.nextInt();
                    finallAPI.getCardProcess(distributionList, selectDistribution2 - 1);
                    break;
                case 4:
                    System.out.println("Введите номер раздачи для которой хотите получить информацию:");
                    int selectDistribution3 = scanner.nextInt();
                    finallAPI.getResultCardProcess(distributionList, selectDistribution3 - 1);
                    break;
                case 5:
                    System.out.println("Пока! Чтобы опять начать работать запустите приложение");
                    flag = false;
                    break;
                default:
                    System.out.println("Действий с такой цифрой нет. Запустите заного.");
                    break;

            }
        }
        scanner.close();
    }

    private void menuBeforeGame() {
        System.out.println("Игра - Преферанс. Выберите действие:");
        System.out.println("1 - Начать играть");
        System.out.println("2 - Выйти");
    }

    private void menuAfterGame() {
        System.out.println("Игра закончена. Выберите действие:");
        System.out.println("1 - Метод получения данных определенной раздачи (кому какие карты были розданы, что в прикупе, кто начинает торговлю/чей ход)");
        System.out.println("2 - Метод поулчения данных о процессе торговли для определенной раздачи (включая прикуп)");
        System.out.println("3 - Метод получения данных о процессе розыгрыша (последовательность ходов и принадлежность взяток) определенной раздачи");
        System.out.println("4 - Метод получения данных о результатах розыгрыша определенной раздачи (раздача, торговля, заявка, игра, результаты)");
        System.out.println("5 - Выход");
    }

    private Distribution doApp(int countDistribution, String nameBot1, String nameBot2, String nameBot3, String nameDistributer, InitializationGame init, StartGame start, Bot bot1, Bot bot2, Bot bot3, Distributor distributor, Logger log, Deck deck, ProcessOfTrade pof, EndGame endGame) throws CloneNotSupportedException {
        int returnCountDistribution = countDistribution + 1;
        Distribution distribution1 = new Distribution();
        List<Card> cards1 = new ArrayList<>();
        List<Card> cards2 = new ArrayList<>();
        List<Card> cards3 = new ArrayList<>();
        List<String> sequenceOfSteps = new ArrayList<>();
        int bribe1 = 0;
        int bribe2 = 0;
        int bribe3 = 0;
        int bullet1 = 0;
        int bullet2 = 0;
        int bullet3 = 0;
        int hill1 = 0;
        int hill2 = 0;
        int hill3 = 0;
        init.setNameBots(bot1, bot2, bot3, distributor, nameBot1, nameBot2, nameBot3, nameDistributer);
        start.setStartGame(deck, bot1, bot2, bot3, distributor);
        log.info("Начало " + returnCountDistribution + " раздачи");
        log.info("\nКарты " + bot1.getName() + ": ");
        for (Card card : bot1.getCards()) {
            String str = Card.getCard(card);
            log.info(str);
        }
        log.info("\nКарты " + bot2.getName() + ": ");
        for (Card card : bot2.getCards()) {
            String str = Card.getCard(card);
            log.info(str);
        }
        log.info("\nКарты " + bot3.getName() + ": ");
        for (Card card : bot3.getCards()) {
            String str = Card.getCard(card);
            log.info(str);
        }
        log.info("\nВыбранные стратегии ботами: ");

        if (pof.selectStrategy(bot1).equals("Contract")) {
            log.info(pof.selectStrategy(bot1) + Contract.getContract(bot1.getValueContract(), bot1.getColorContract()));
        } else {
            log.info(pof.selectStrategy(bot1));
        }
        if (pof.selectStrategy(bot2).equals("Contract")) {
            log.info(pof.selectStrategy(bot2) + Contract.getContract(bot2.getValueContract(), bot2.getColorContract()));
        } else {
            log.info(pof.selectStrategy(bot2));
        }
        if (pof.selectStrategy(bot3).equals("Contract")) {
            log.info(pof.selectStrategy(bot3) + Contract.getContract(bot3.getValueContract(), bot3.getColorContract()));
        } else {
            log.info(pof.selectStrategy(bot3));
        }

        cards1.addAll(bot1.getCards());
        cards2.addAll(bot2.getCards());
        cards3.addAll(bot3.getCards());

        log.info("\nВыбранная игра: ");
        log.info(pof.processTrade(pof.selectStrategy(bot1), pof.selectStrategy(bot2), pof.selectStrategy(bot3), bot1, bot2, bot3));

        distribution1.setBotName1(nameBot1);
        distribution1.setBotName2(nameBot2);
        distribution1.setBotName3(nameBot3);
        distribution1.setCards1(cards1);
        distribution1.setCards2(cards2);
        distribution1.setCards3(cards3);
        distribution1.setCardsDistributor(distributor.getCards());
        distribution1.setDistributuionNumber(returnCountDistribution);
        distribution1.setStrategyBot1(pof.selectStrategy(bot1));
        distribution1.setStrategyBot2(pof.selectStrategy(bot2));
        distribution1.setStrategyBot3(pof.selectStrategy(bot3));
        distribution1.setSelectGame(pof.processTrade(pof.selectStrategy(bot1), pof.selectStrategy(bot2), pof.selectStrategy(bot3), bot1, bot2, bot3));

        if (pof.processTrade(pof.selectStrategy(bot1), pof.selectStrategy(bot2), pof.selectStrategy(bot3), bot1, bot2, bot3).equals("Unpacking")) {
            List<String> run = unpacking.run(bot1, bot2, bot3, distributor, log, sequenceOfSteps);
            distribution1.setSequenceOfSteps(run);
        }

        if (bot1.isContractor()) {
            List<String> run = contract.run(bot1, bot2, bot3, distributor, log, sequenceOfSteps);
            distribution1.setSequenceOfSteps(run);
            distribution1.setNameBot(bot1.getName());
            bot1.setContractor(false);
        }
        if (bot2.isContractor()) {
            List<String> run = contract.run(bot2, bot1, bot3, distributor, log, sequenceOfSteps);
            distribution1.setSequenceOfSteps(run);
            distribution1.setNameBot(bot2.getName());
            bot2.setContractor(false);
        }
        if (bot3.isContractor()) {
            List<String> run = contract.run(bot3, bot1, bot2, distributor, log, sequenceOfSteps);
            distribution1.setSequenceOfSteps(run);
            distribution1.setNameBot(bot3.getName());
            bot3.setContractor(false);
        }
        if (bot1.isMiser()) {
            List<String> run = misery.run(bot1, bot2, bot3, distributor, log, sequenceOfSteps);
            distribution1.setSequenceOfSteps(run);
            distribution1.setNameBot(bot1.getName());
            bot1.setMiser(false);
        }
        if (bot2.isMiser()) {
            List<String> run = misery.run(bot2, bot1, bot3, distributor, log, sequenceOfSteps);
            distribution1.setSequenceOfSteps(run);
            distribution1.setNameBot(bot2.getName());
            bot2.setMiser(false);
        }
        if (bot3.isMiser()) {
            List<String> run = misery.run(bot3, bot1, bot2, distributor, log, sequenceOfSteps);
            distribution1.setSequenceOfSteps(run);
            distribution1.setNameBot(bot3.getName());
            bot3.setMiser(false);
        }
        bribe1 = bot1.getBribe();
        bribe2 = bot2.getBribe();
        bribe3 = bot3.getBribe();
        bullet1 = bot1.getBullet();
        bullet2 = bot2.getBullet();
        bullet3 = bot3.getBullet();
        hill1 = bot1.getHill();
        hill2 = bot2.getHill();
        hill3 = bot3.getHill();
        distribution1.setBribe1(bribe1);
        distribution1.setBribe2(bribe2);
        distribution1.setBribe3(bribe3);
        distribution1.setBotBullet1(bullet1);
        distribution1.setBotBullet2(bullet2);
        distribution1.setBotBullet3(bullet3);
        distribution1.setBotHill1(hill1);
        distribution1.setBotHill2(hill2);
        distribution1.setBotHill3(hill3);

        log.info("\n----------------------------------------------------------------------------------");
        log.info("\n------------------------------- Результат взяток ---------------------------------");
        log.info("\n" + bot1.getName() + ": " + bot1.getBribe());
        log.info("\n" + bot2.getName() + ": " + bot2.getBribe());
        log.info("\n" + bot3.getName() + ": " + bot3.getBribe());
        log.info("\n----------------------------------------------------------------------------------");

        log.info("\n" + bot1.getName() + " соответственно горы и пули:" + bot1.getHill() + " и " + bot1.getBullet());
        log.info("\n" + bot2.getName() + " соответственно горы и пули:" + bot2.getHill() + " и " + bot2.getBullet());
        log.info("\n" + bot3.getName() + " соответственно горы и пули:" + bot3.getHill() + " и " + bot3.getBullet());
        log.info("\nРаздача завершена");
        log.info("\n----------------------------------------------------------------------------------");
        endGame.refreshBribes(bot1, bot2, bot3);
        return distribution1;
    }
}
