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

    private GameStrategy contract = new Contract();
    private GameStrategy misery = new Misery();
    private GameStrategy unpacking = new Unpacking();
    private Bot bot1 = new Bot();
    private Bot bot2 = new Bot();
    private Bot bot3 = new Bot();
    private Distributor distributor = new Distributor();
    private ProcessOfTrade pof = new ProcessOfTrade();
    private Deck deck = new Deck();
    private Logger log = Logger.getLogger(Main.class.getName());
    private InitializationGame init = new InitializationGame();
    private ConditionsGame conditionsGame = new ConditionsGame();
    private StartGame start = new StartGame();
    private EndGame endGame = new EndGame();
    private FinallAPI finallAPI = new FinallAPI();

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
                }
                break;
            case 2:
                System.out.println("Всего доброго! Для старта запустите приложение сного");
                break;
        }
        menuAfterGame();
        int selectMethod = scanner.nextInt();
        switch (selectMethod) {
            case 1:
                System.out.println("Введите номер раздачи для которой хотите получить информацию:");
                int selectDistribution = scanner.nextInt();
                finallAPI.getDataForSpecificDistribution(distributionList, selectDistribution - 1);
                break;
        }

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
        System.out.println("3 - Метод получения данных о процессе заявки игрока (какую игру заказал для определенной раздачи) и реакции других игроков (вист/пас, игра в открытую/закрытую)");
        System.out.println("4 - Метод получения данных о процессе розыгрыша (последовательность ходов и принадлежность взяток) определенной раздачи");
        System.out.println("5 - Метод получения данных о результатах розыгрыша определенной раздачи (раздача, торговля, заявка, игра, результаты)");
        System.out.println("6 - Метод получения данных о полном процессе розыгрыша определенной раздачи (раздача, торговля, заявка, игра, результаты)");
        System.out.println("7 - Метод получения данных о текущем состоянии пули, горы и вистах игрока после определенной раздачи");
        System.out.println("8 - Метод поулчения промежуточного результата игрока после определенной раздачи");
        System.out.println("9 - Метод получения статистики по игроку после розыгрыша определенной раздачи");
        System.out.println("10 - Метод получения промежуточного результата всех игроков после определенной раздачи");
    }

    private Distribution doApp(int countDistribution, String nameBot1, String nameBot2, String nameBot3, String nameDistributer, InitializationGame init, StartGame start, Bot bot1, Bot bot2, Bot bot3, Distributor distributor, Logger log, Deck deck, ProcessOfTrade pof, EndGame endGame) throws CloneNotSupportedException {
        int returnCountDistribution = countDistribution + 1;
        Distribution distribution1 = new Distribution();
        List<Card> cards1 = new ArrayList<>();
        List<Card> cards2 = new ArrayList<>();
        List<Card> cards3 = new ArrayList<>();
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

        if (pof.processTrade(pof.selectStrategy(bot1), pof.selectStrategy(bot2), pof.selectStrategy(bot3), bot1, bot2, bot3).equals("Unpacking")) {
            unpacking.run(bot1, bot2, bot3, distributor, log);
        }

        if (bot1.isContractor()) {
            contract.run(bot1, bot2, bot3, distributor, log);
            distribution1.setNameBot(bot1.getName());
            bot1.setContractor(false);
        }
        if (bot2.isContractor()) {
            contract.run(bot2, bot1, bot3, distributor, log);
            distribution1.setNameBot(bot2.getName());
            bot2.setContractor(false);
        }
        if (bot3.isContractor()) {
            contract.run(bot3, bot1, bot2, distributor, log);
            distribution1.setNameBot(bot3.getName());
            bot3.setContractor(false);
        }
        if (bot1.isMiser()) {
            misery.run(bot1, bot2, bot3, distributor, log);
            distribution1.setNameBot(bot1.getName());
            bot1.setMiser(false);
        }
        if (bot2.isMiser()) {
            misery.run(bot2, bot1, bot3, distributor, log);
            distribution1.setNameBot(bot2.getName());
            bot2.setMiser(false);
        }
        if (bot3.isMiser()) {
            misery.run(bot3, bot1, bot2, distributor, log);
            distribution1.setNameBot(bot3.getName());
            bot3.setMiser(false);
        }

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
