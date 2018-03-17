package main.controller;

import main.model.Bot;
import main.model.Card;
import main.model.Distributor;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Misery implements GameStrategy {

    @Override
    public List<String> run(Bot botMiser, Bot bot1, Bot bot2, Distributor distributor, Logger logger, List<String> sequenceOfSteps) {
        int check = 0;
        Random random = new Random();
        int select = random.nextInt(3);
        logger.info("\nПроцесс прикупа:");
        logger.info("\nПрикуп:");
        for (Card card : distributor.getCards()) {
            logger.info(Card.getCard(card));
        }
        for (Card card : distributor.getCards()) {
            logger.info("\nИграющий мизер " + botMiser.getName() + " получил:" + Card.getCard(card) + " от раздающего " + distributor.getName());
            botMiser.getCards().add(card);
        } // Раздающий дал карты контрактеру
        for (int i = 0; i < botMiser.getCards().size(); i++) {
            if (botMiser.getCards().get(i).getValue() > 3) {
                check++;
                if (check < 3) {
                    logger.info("\n" + botMiser.getName() + " решил снести:" + Card.getCard(botMiser.getCards().get(i)));
                    botMiser.getCards().remove(botMiser.getCards().get(i));
                }
            }
        }
        if (botMiser.getCards().size() != 10) for (int i = 0; i < botMiser.getCards().size(); i++) {
            if (botMiser.getCards().get(i).getValue() > 1) {
                check++;
                if (check < 3) {
                    logger.info("\n" + botMiser.getName() + " решил снести:" + Card.getCard(botMiser.getCards().get(i)));
                    botMiser.getCards().remove(botMiser.getCards().get(i));
                }
            }
        }
        // Мизер убрал две невыгодные карты
        sequenceOfSteps.addAll(drawOfCard(select, logger, botMiser, bot1, bot2));
        return sequenceOfSteps;
    }

    private void comparisonCards(Card card11, Card card22, Card card33, Bot bot11, Bot bot22, Bot bot33, Logger log) {
        if (card11.getColor() == card22.getColor() & card11.getColor() == card33.getColor()) {
            if (card11.getValue() >= card22.getValue() & card11.getValue() >= card33.getValue()) {
                int bribe = bot11.getBribe() + 1;
                bot11.setBribe(bribe);
                log.info("\n" + bot11.getName() + " получил взятку");
                setWinner(bot11, bot22, bot33);
                return;
            }
            if (card22.getValue() >= card11.getValue() & card22.getValue() >= card33.getValue()) {
                int bribe = bot22.getBribe() + 1;
                bot22.setBribe(bribe);
                log.info("\n" + bot22.getName() + " получил взятку");
                setWinner(bot22, bot11, bot33);
                return;
            }
            if (card33.getValue() >= card11.getValue() & card33.getValue() >= card22.getValue()) {
                int bribe = bot33.getBribe() + 1;
                bot33.setBribe(bribe);
                log.info("\n" + bot33.getName() + " получил взятку");
                setWinner(bot33, bot11, bot22);
                return;
            }
        }
        if (card11.getColor() == card22.getColor() & card11.getColor() != card33.getColor()) {
            if (card11.getValue() >= card22.getValue()) {
                int bribe = bot11.getBribe() + 1;
                bot11.setBribe(bribe);
                log.info("\n" + bot11.getName() + " получил взятку");
                setWinner(bot11, bot22, bot33);
                return;
            } else {
                int bribe = bot22.getBribe() + 1;
                bot22.setBribe(bribe);
                log.info("\n" + bot22.getName() + " получил взятку");
                setWinner(bot22, bot11, bot33);
                return;
            }
        }
        if (card11.getColor() == card33.getColor() & card11.getColor() != card22.getColor()) {
            if (card11.getValue() >= card33.getValue()) {
                int bribe = bot11.getBribe() + 1;
                bot11.setBribe(bribe);
                log.info("\n" + bot11.getName() + " получил взятку");
                setWinner(bot11, bot22, bot33);
                return;
            } else {
                int bribe = bot33.getBribe() + 1;
                bot33.setBribe(bribe);
                log.info("\n" + bot33.getName() + " получил взятку");
                setWinner(bot33, bot11, bot22);
                return;
            }
        }
        if (card11.getColor() != card22.getColor() & card11.getColor() != card33.getColor()) {
            int bribe = bot11.getBribe() + 1;
            bot11.setBribe(bribe);
            log.info("\n" + bot11.getName() + " получил взятку");
            setWinner(bot11, bot22, bot33);
        }
    }

    private Card getMinCard(List<Card> cards) {
        Card returnCard = new Card();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getValue() == cards.stream().mapToInt(Card::getValue).min().getAsInt()) {
                returnCard = cards.get(i);
                cards.remove(i);
                break;
            }
        }
        return returnCard;
    }

    private Card getBenefitCard(List<Card> cards, Card card) {
        Card returnCard = new Card();
        List<Card> cardsTrump = cards.stream().filter(card1 -> card1.getColor() == card.getColor()).collect(Collectors.toList());
        if (!cardsTrump.isEmpty()) {
            for (int i = 0; i < cardsTrump.size(); i++) {
                if (cardsTrump.get(i).getValue() == cardsTrump.stream().mapToInt(Card::getValue).min().getAsInt()) {
                    returnCard = cardsTrump.get(i);
                    cards.remove(cardsTrump.get(i));
                    break;
                }
                if (cardsTrump.get(i).getValue() <= cardsTrump.stream().mapToInt(Card::getValue).max().getAsInt()) {
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

    private List<String> drawOfCard(int select, Logger log, Bot botMiser, Bot bot1, Bot bot2) {
        List<String> sequenceOfSteps = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            label:
            {
                int numberStep = i + 1;
                log.info("\nХод номер:" + numberStep);
                if (select == 0) {
                    if (numberStep == 1) {
                        doIt(log, botMiser, bot1, bot2, numberStep, sequenceOfSteps);
                    }
                    if (numberStep > 1) {
                        if (botMiser.isWinner()) {
                            doIt(log, botMiser, bot1, bot2, numberStep, sequenceOfSteps);
                            break label;
                        }
                        if (bot1.isWinner()) {
                            doIt(log, bot1, botMiser, bot2, numberStep, sequenceOfSteps);
                            break label;
                        }
                        if (bot2.isWinner()) {
                            doIt(log, bot2, botMiser, bot1, numberStep, sequenceOfSteps);
                            break label;
                        }
                    }
                }
                if (select == 1) {
                    if (numberStep == 1) {
                        doIt(log, bot1, botMiser, bot2, numberStep, sequenceOfSteps);
                    }
                    if (numberStep > 1) {
                        if (botMiser.isWinner()) {
                            doIt(log, botMiser, bot1, bot2, numberStep, sequenceOfSteps);
                            break label;
                        }
                        if (bot1.isWinner()) {
                            doIt(log, bot1, botMiser, bot2, numberStep, sequenceOfSteps);
                            break label;
                        }
                        if (bot2.isWinner()) {
                            doIt(log, bot2, botMiser, bot1, numberStep, sequenceOfSteps);
                            break label;
                        }
                    }
                }
                if (select == 2) {
                    if (numberStep == 1) {
                        doIt(log, bot2, botMiser, bot1, numberStep, sequenceOfSteps);
                    }
                    if (numberStep > 1) {
                        if (botMiser.isWinner()) {
                            doIt(log, botMiser, bot1, bot2, numberStep, sequenceOfSteps);
                            break label;
                        }
                        if (bot1.isWinner()) {
                            doIt(log, bot1, botMiser, bot2, numberStep, sequenceOfSteps);
                            break label;
                        }
                        if (bot2.isWinner()) {
                            doIt(log, bot2, botMiser, bot1, numberStep, sequenceOfSteps);
                        }
                    }

                }
            }
        }
        writePointsForBots(botMiser);
        return sequenceOfSteps;
    }

    private void doIt(Logger log, Bot bot11, Bot bot22, Bot bot33, int numberStep, List<String> sequenceOfSteps) {
        Card checkCard;
        Card card1;
        Card card2;
        String step = null;
        checkCard = getMinCard(bot11.getCards());
        log.info("\n" + bot11.getName() + " кладет:" + Card.getCard(checkCard));
        card1 = getBenefitCard(bot22.getCards(), checkCard);
        log.info("\n" + bot22.getName() + " кладет:" + Card.getCard(card1));
        card2 = getBenefitCard(bot33.getCards(), checkCard);
        log.info("\n" + bot33.getName() + " кладет:" + Card.getCard(card2));
        comparisonCards(checkCard, card1, card2, bot11, bot22, bot33, log);
        step = writeSteps(bot11, bot22, bot33, numberStep);
        sequenceOfSteps.add(step);
    }

    private String writeSteps(Bot bot1, Bot bot2, Bot bot3, int numberStep) {
        return "\nХод номер:" + numberStep
                + "\nХодит " + bot1.getName()
                + "\nХодит " + bot2.getName()
                + "\nХодит " + bot3.getName() + "\n";
    }

    private void writePointsForBots(Bot botMiser) {
        if (botMiser.getBribe() == 0) {
            int bullet = botMiser.getBullet() + 10;
            botMiser.setBullet(bullet);
        }
        if (botMiser.getBribe() != 0) {
            int hill = botMiser.getHill() + (botMiser.getBribe() * 10);
            botMiser.setHill(hill);
        }

    }

    private void setWinner(Bot botOne, Bot botTwo, Bot botThree) {
        botOne.setWinner(true);
        botTwo.setWinner(false);
        botThree.setWinner(false);
    }

}
