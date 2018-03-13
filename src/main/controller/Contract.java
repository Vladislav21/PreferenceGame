package main.controller;

import main.model.Bot;
import main.model.Card;
import main.model.Distributor;
import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import java.util.*;
import java.util.stream.Collectors;

public class Contract implements GameStrategy {

    public static String getContract(int value1, int color1) {
        String contract = null;
        switch (color1) {
            case 0:
                contract = " " + value1 + " SPADES";
                break;
            case 1:
                contract = " " + value1 + " CLUBS";
                break;
            case 2:
                contract = " " + value1 + " DIAMONDS";
                break;
            case 3:
                contract = " " + value1 + " HEARTS";
                break;
        }
        return contract;
    }

    @Override
    public List<String> run(Bot botContractor, Bot bot1, Bot bot2, Distributor distributor, Logger logger, List<String> sequenceOfSteps) {
        int check = 0;
        Random random = new Random();
        int select = random.nextInt(3);
        logger.info("\nПроцесс прикупа:");
        int trump = botContractor.getColorContract(); // козырь
        logger.info("\nПрикуп:");
        for (Card card : distributor.getCards()) {
            logger.info(Card.getCard(card));
        }
        for (Card card : distributor.getCards()) {
            logger.info("\nИграющий контракт " + botContractor.getName() + " получил:" + Card.getCard(card) + " от раздающего " + distributor.getName());
            botContractor.getCards().add(card);
        } // Раздающий дал карты контрактеру
        for (int i = 0; i < botContractor.getCards().size(); i++) {
            if (botContractor.getCards().get(i).getColor() != trump & botContractor.getCards().get(i).getValue() < 4) {
                check++;
                if (check < 3) {
                    logger.info("\n" + botContractor.getName() + " решил снести:" + Card.getCard(botContractor.getCards().get(i)));
                    botContractor.getCards().remove(botContractor.getCards().get(i));
                }
            }
        }
        if (botContractor.getCards().size() != 10) {
            for (int i = 0; i < botContractor.getCards().size(); i++) {
                if (botContractor.getCards().get(i).getColor() != trump & botContractor.getCards().get(i).getValue() < 6) {
                    check++;
                    if (check < 3) {
                        logger.info("\n" + botContractor.getName() + " решил снести:" + Card.getCard(botContractor.getCards().get(i)));
                        botContractor.getCards().remove(botContractor.getCards().get(i));
                    }
                }
            }
        }

        // Контрактер убрал две невыгодные карты
        logger.info("\nИгрок " + botContractor.getName() + " выбирает контракт:" + Contract.getContract(botContractor.getValueContract(), botContractor.getColorContract()));
        if (getReactionOnContract(botContractor, bot1).equals("Pass") & getReactionOnContract(botContractor, bot2).equals("Pass")) {
            setBulletBotContractForTwoPass(botContractor);
            setHillForBotLoser(botContractor, bot1);
            setHillForBotLoser(botContractor, bot2);
            logger.info("\n" + bot1.getName() + " пасанул");
            logger.info("\n" + bot2.getName() + " пасанул");
            logger.info("\n" + botContractor.getName() + " выиграл контракт");
            sequenceOfSteps.add("Оба игрока пасанули, ходов не было");
            return sequenceOfSteps;
        }
        if (getReactionOnContract(botContractor, bot1).equals("Vista") & getReactionOnContract(botContractor, bot2).equals("Vista")) {
            logger.info("\n" + bot1.getName() + " и " + bot2.getName() + " вистанули на данный контракт, начинается процесс розыгрыша:");
            sequenceOfSteps.addAll(drawOfCard(select, trump, logger, botContractor, bot1, bot2));
            return sequenceOfSteps;
            /*writePointsForBots(botContractor, bot1, bot2);*/

        }
        if (getReactionOnContract(botContractor, bot1).equals("Vista") & getReactionOnContract(botContractor, bot2).equals("Pass")) {
            int selectGame = random.nextInt(2);

            if (selectGame == 0) {
                logger.info("\nВистующий бот " + bot1.getName() + " решил играть в закрытую");
                sequenceOfSteps.addAll(drawOfCard(select, trump, logger, botContractor, bot1, bot2));
                return sequenceOfSteps;
                /*writePointsForBots(botContractor, bot1, bot2);*/
            }
            if (selectGame == 1) {
                logger.info("\nВистующий бот " + bot1.getName() + " решил играть в открытую");
                sequenceOfSteps.addAll(drawOfCard(select, trump, logger, botContractor, bot1, bot2));
                return sequenceOfSteps;
                /*writePointsForBots(botContractor, bot1, bot2);*/
            }
        }
        if (getReactionOnContract(botContractor, bot1).equals("Pass") & getReactionOnContract(botContractor, bot2).equals("Vista")) {
            int selectGame = random.nextInt(2);
            if (selectGame == 0) {
                logger.info("\nВистующий бот " + bot2.getName() + " решил играть в закрытую");
                sequenceOfSteps.addAll(drawOfCard(select, trump, logger, botContractor, bot1, bot2));
                return sequenceOfSteps;
                /*writePointsForBots(botContractor, bot1, bot2);*/
            }
            if (selectGame == 1) {
                logger.info("\nВистующий бот " + bot2.getName() + " решил играть в открытую");
                sequenceOfSteps.addAll(drawOfCard(select, trump, logger, botContractor, bot1, bot2));
                return sequenceOfSteps;
               /* writePointsForBots(botContractor, bot1, bot2);*/
            }
        }
        return sequenceOfSteps;
    }

    private String getReactionOnContract(Bot botContractor1, Bot botComparable) {
        String reaction = null;
        int countTrump = 0;
        int countAce = 0;
        for (Card card : botComparable.getCards()) {
            if (card.getColor() == botContractor1.getColorContract()) {
                countTrump++;
            }
            if (card.getValue() == 7) {
                countAce++;
            }
        }
        if (countTrump == botContractor1.getValueContract() - 3 & (countAce == 1 || countAce == 2 || countAce == 3)) {
            return reaction = "Vista";
        }
        return reaction = "Pass";
    }

    private void setBulletBotContractForTwoPass(Bot botWinner) {
        if (botWinner.getValueContract() == 6) {
            int bullet = botWinner.getBullet() + 2;
            botWinner.setBullet(bullet);
        }
        if (botWinner.getValueContract() == 7) {
            int bullet = botWinner.getBullet() + 4;
            botWinner.setBullet(bullet);
        }
        if (botWinner.getValueContract() == 8) {
            int bullet = botWinner.getBullet() + 6;
            botWinner.setBullet(bullet);
        }
    }

    private void setHillForBotLoser(Bot botWinner, Bot botLoser) {
        if (botWinner.getValueContract() == 6) {
            if (botLoser.getBribe() >= 4) {
                int hill = botLoser.getHill();
                botLoser.setHill(hill);
            } else {
                int hill = botLoser.getHill() + ((4 - botLoser.getBribe()) * 2);
                botLoser.setHill(hill);
            }

        }
        if (botWinner.getValueContract() == 7) {
            if (botLoser.getBribe() >= 2) {
                int hill = botLoser.getHill();
                botLoser.setHill(hill);
            } else {
                int hill = botLoser.getHill() + ((2 - botLoser.getBribe()) * 4);
                botLoser.setHill(hill);
            }
        }
        if (botWinner.getValueContract() == 8) {
            if (botLoser.getBribe() >= 1) {
                int hill = botLoser.getHill();
                botLoser.setHill(hill);
            } else {
                int hill = botLoser.getHill() + ((1 - botLoser.getBribe()) * 6);
                botLoser.setHill(hill);
            }
        }
    }

    private Card getMaxOrMaxTrumpCard(List<Card> cards, int trump1) {
        Card returnCard = new Card();
        List<Card> cardsTrump = cards.stream().filter(card -> card.getColor() == trump1).collect(Collectors.toList());
        if (!cardsTrump.isEmpty()) {
            for (int i = 0; i < cardsTrump.size(); i++) {
                if (cardsTrump.get(i).getValue() == cardsTrump.stream().mapToInt(Card::getValue).max().getAsInt()) {
                    returnCard = cardsTrump.get(i);
                    cards.remove(cardsTrump.get(i));
                    break;
                }
            }
        }
        if (cardsTrump.isEmpty()) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i).getValue() == cards.stream().mapToInt(Card::getValue).max().getAsInt()) {
                    returnCard = cards.get(i);
                    cards.remove(i);
                    break;
                }
            }
        }
        return returnCard;
    }

    public void comparisonCards(Card card11, Card card22, Card card33, Bot bot11, Bot bot22, Bot bot33, Logger log) {
        if (card11.getColor() == card22.getColor() & card11.getColor() == card33.getColor()) {
            if (card11.getValue() >= card22.getValue() & card11.getValue() >= card33.getValue()) {
                int bribe = bot11.getBribe() + 1;
                bot11.setBribe(bribe);
                log.info("\n" + bot11.getName() + " получил взятку");
            }
            if (card22.getValue() >= card11.getValue() & card22.getValue() >= card33.getValue()) {
                int bribe = bot22.getBribe() + 1;
                bot22.setBribe(bribe);
                log.info("\n" + bot22.getName() + " получил взятку");
            }
            if (card33.getValue() >= card11.getValue() & card33.getValue() >= card22.getValue()) {
                int bribe = bot33.getBribe() + 1;
                bot33.setBribe(bribe);
                log.info("\n" + bot33.getName() + " получил взятку");
            }
        }
        if (card11.getColor() == card22.getColor() & card11.getColor() != card33.getColor()) {
            if (card11.getValue() >= card22.getValue()) {
                int bribe = bot11.getBribe() + 1;
                bot11.setBribe(bribe);
                log.info("\n" + bot11.getName() + " получил взятку");
            } else {
                int bribe = bot22.getBribe() + 1;
                bot22.setBribe(bribe);
                log.info("\n" + bot22.getName() + " получил взятку");
            }
        }
        if (card11.getColor() == card33.getColor() & card11.getColor() != card22.getColor()) {
            if (card11.getValue() >= card33.getValue()) {
                int bribe = bot11.getBribe() + 1;
                bot11.setBribe(bribe);
                log.info("\n" + bot11.getName() + " получил взятку");
            } else {
                int bribe = bot33.getBribe() + 1;
                bot33.setBribe(bribe);
                log.info("\n" + bot33.getName() + " получил взятку");
            }
        }
        if (card11.getColor() != card22.getColor() & card11.getColor() != card33.getColor()) {
            int bribe = bot11.getBribe() + 1;
            bot11.setBribe(bribe);
            log.info("\n" + bot11.getName() + " получил взятку");
        }
    }

    private void writePointsForBots(Bot bot1, Bot bot2, Bot bot3) {
        setPointsBotContract(bot1);
        setHillForBotLoser(bot1, bot2);
        setHillForBotLoser(bot1, bot3);
    }

    private void setPointsBotContract(Bot bot1) {
        if (bot1.getBribe() >= 6 & bot1.getValueContract() == 6) {
            int bullet = bot1.getBullet() + 2;
            bot1.setBullet(bullet);
        }
        if (bot1.getBribe() < 6 & bot1.getValueContract() == 6) {
            int hill = bot1.getHill() + ((6 - bot1.getBribe()) * 2);
            bot1.setHill(hill);
        }
        if (bot1.getBribe() >= 7 & bot1.getValueContract() == 7) {
            int bullet = bot1.getBullet() + 4;
            bot1.setBullet(bullet);
        }
        if (bot1.getBribe() < 7 & bot1.getValueContract() == 7) {
            int hill = bot1.getHill() + ((7 - bot1.getBribe()) * 4);
            bot1.setHill(hill);
        }
        if (bot1.getBribe() >= 8 & bot1.getValueContract() == 8) {
            int bullet = bot1.getBullet() + 6;
            bot1.setBullet(bullet);
        }
        if (bot1.getBribe() < 8 & bot1.getValueContract() == 8) {
            int hill = bot1.getHill() + ((8 - bot1.getBribe()) * 6);
            bot1.setHill(hill);
        }

    }

    private List<String> drawOfCard(int select, int trump, Logger log, Bot botContractor, Bot bot1, Bot bot2) {
        List<String> sequenceOfSteps = new ArrayList<>();
        String step1 = null;
        String step2 = null;
        String step3 = null;
        for (int i = 0; i < 10; i++) {
            log.info("\nХод номер:" + i);
            Card checkCard;
            Card card1;
            Card card2;
            if (select == 0) {
                checkCard = getMaxOrMaxTrumpCard(botContractor.getCards(), trump);
                log.info("\n" + botContractor.getName() + " кладет:" + Card.getCard(checkCard));
                card1 = getMaxOrMaxTrumpCard(bot1.getCards(), checkCard.getColor());
                log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(card1));
                card2 = getMaxOrMaxTrumpCard(bot2.getCards(), checkCard.getColor());
                log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(card2));
                comparisonCards(checkCard, card1, card2, bot1, botContractor, bot2, log);
                step1 = writeSteps(botContractor);
                step2 = writeSteps(bot1);
                step3 = writeSteps(bot2);
            }
            if (select == 1) {
                checkCard = getMaxOrMaxTrumpCard(bot1.getCards(), trump);
                log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(checkCard));
                card1 = getMaxOrMaxTrumpCard(botContractor.getCards(), checkCard.getColor());
                log.info("\n" + botContractor.getName() + " кладет:" + Card.getCard(card1));
                card2 = getMaxOrMaxTrumpCard(bot2.getCards(), checkCard.getColor());
                log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(card2));
                comparisonCards(checkCard, card1, card2, bot1, botContractor, bot2, log);
                step1 = writeSteps(bot1);
                step2 = writeSteps(botContractor);
                step3 = writeSteps(bot2);

            }
            if (select == 2) {
                checkCard = getMaxOrMaxTrumpCard(bot2.getCards(), trump);
                log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(checkCard));
                card1 = getMaxOrMaxTrumpCard(botContractor.getCards(), checkCard.getColor());
                log.info("\n" + botContractor.getName() + " кладет:" + Card.getCard(card1));
                card2 = getMaxOrMaxTrumpCard(bot1.getCards(), checkCard.getColor());
                log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(card2));
                comparisonCards(checkCard, card1, card2, bot2, botContractor, bot1, log);
                step1 = writeSteps(bot2);
                step2 = writeSteps(botContractor);
                step3 = writeSteps(bot1);
            }

        }
        sequenceOfSteps.add(step1);
        sequenceOfSteps.add(step2);
        sequenceOfSteps.add(step3);
        switch (select) {
            case 0:
                writePointsForBots(botContractor, bot1, bot2);
                break;
            case 1:
                writePointsForBots(bot1, botContractor, bot2);
                break;
            case 2:
                writePointsForBots(bot2, botContractor, bot1);
                break;
        }

        return sequenceOfSteps;
    }

    private String writeSteps(Bot bot1) {
        return "Ходит " + bot1.getName() + "\n";
    }

}