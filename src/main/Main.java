package main;

import main.controller.Contract;
import main.controller.InitializationGame;
import main.controller.ProcessOfTrade;
import main.controller.StartGame;
import main.model.*;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Bot bot1 = new Bot();
        Bot bot2 = new Bot();
        Bot bot3 = new Bot();
        Distributor distributor = new Distributor();
        Distribution distribution = new Distribution();
        ProcessOfTrade pof = new ProcessOfTrade();
        Deck deck = new Deck();
        Logger log = Logger.getLogger(Main.class.getName());

        InitializationGame init = new InitializationGame();
        StartGame start = new StartGame();
        init.setNameBots(bot1, bot2, bot3, distributor, "Хач", "Хуяч", "Поебалу", "Шлюха");
        start.setStartGame(distribution,1,deck,bot1,bot2,bot3,distributor);
        log.info("Начало первой раздачи");
        Thread.sleep(1000);
        log.info("\nКарты "+ bot1.getName()+": \n");
        for (Card card: bot1.getCards()){
            String str = Card.getCard(card);
            log.info(str);
            Thread.sleep(500);
        }
        log.info("\nКарты "+ bot2.getName()+": \n");
        for (Card card: bot2.getCards()){
            String str = Card.getCard(card);
            log.info(str);
            Thread.sleep(500);
        }
        log.info("\nКарты "+ bot3.getName()+": \n");
        for (Card card: bot3.getCards()){
            String str = Card.getCard(card);
            log.info(str);
            Thread.sleep(500);
        }
        log.info("\nВыбранные стратегии ботами: \n");


        log.info(pof.selectStrategy(bot1));
        log.info(pof.selectStrategy(bot2));
        log.info(pof.selectStrategy(bot3));
        log.info("----------------------------------------------------------------------------------");

    }
}



