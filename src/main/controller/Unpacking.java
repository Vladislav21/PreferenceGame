package main.controller;

import main.model.Bot;
import main.model.Card;
import main.model.Distributor;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Unpacking implements GameStrategy {

    @Override
    public List<String> run(Bot bot1, Bot bot2, Bot bot3, Distributor distributor, Logger logger, List<String> sequenceOfSteps) {
        Random random = new Random();
        int select = random.nextInt(3);
        sequenceOfSteps.addAll(drawOfCard(select, logger, bot1, bot2, bot3, distributor));
        return sequenceOfSteps;
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

    private List<String> drawOfCard(int select, Logger log, Bot botMiser, Bot bot1, Bot bot2, Distributor distributor) {
        List<String> sequenceOfSteps = new ArrayList<>();
        String step = null;
        for (int i = 0; i < 10; i++) {
            int numberStep = i + 1;
            log.info("\nХод номер:" + numberStep);
            Card checkCard;
            Card card1;
            Card card2;
            if (i < 2) {
                log.info("\nРаздающий " + distributor.getName() + " показывает " + i + " карту:" + Card.getCard(distributor.getCards().get(i)));
                if (select == 0) {
                    checkCard = getBenefitCard(botMiser.getCards(), distributor.getCards().get(i));
                    log.info("\n" + botMiser.getName() + " кладет:" + Card.getCard(checkCard));
                    card1 = getBenefitCard(bot1.getCards(), checkCard);
                    log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(card1));
                    card2 = getBenefitCard(bot2.getCards(), checkCard);
                    log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(card2));
                    comparisonCards(checkCard, card1, card2, botMiser, bot1, bot2, log);
                    step = writeSteps(botMiser, bot1, bot2,numberStep);
                }
                if (select == 1) {
                    checkCard = getBenefitCard(bot1.getCards(), distributor.getCards().get(i));
                    log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(checkCard));
                    card1 = getBenefitCard(botMiser.getCards(), checkCard);
                    log.info("\n" + botMiser.getName() + " кладет:" + Card.getCard(card1));
                    card2 = getBenefitCard(bot2.getCards(), checkCard);
                    log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(card2));
                    comparisonCards(checkCard, card1, card2, bot1, botMiser, bot2, log);
                    step = writeSteps(bot1, botMiser, bot2,numberStep);
                }
                if (select == 2) {
                    checkCard = getBenefitCard(bot2.getCards(), distributor.getCards().get(i));
                    log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(checkCard));
                    card1 = getBenefitCard(botMiser.getCards(), checkCard);
                    log.info("\n" + botMiser.getName() + " кладет:" + Card.getCard(card1));
                    card2 = getBenefitCard(bot1.getCards(), checkCard);
                    log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(card2));
                    comparisonCards(checkCard, card1, card2, bot2, botMiser, bot1, log);
                    step = writeSteps(bot2, botMiser, bot1,numberStep);
                }
            }
            if (i >= 2) {
                if (select == 0) {
                    checkCard = getMinCard(botMiser.getCards());
                    log.info("\n" + botMiser.getName() + " кладет:" + Card.getCard(checkCard));
                    card1 = getBenefitCard(bot1.getCards(), checkCard);
                    log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(card1));
                    card2 = getBenefitCard(bot2.getCards(), checkCard);
                    log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(card2));
                    comparisonCards(checkCard, card1, card2, botMiser, bot1, bot2, log);
                    step = writeSteps(botMiser, bot1, bot2,numberStep);
                }
                if (select == 1) {
                    checkCard = getMinCard(bot1.getCards());
                    log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(checkCard));
                    card1 = getBenefitCard(botMiser.getCards(), checkCard);
                    log.info("\n" + botMiser.getName() + " кладет:" + Card.getCard(card1));
                    card2 = getBenefitCard(bot2.getCards(), checkCard);
                    log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(card2));
                    comparisonCards(checkCard, card1, card2, bot1, botMiser, bot2, log);
                    step = writeSteps(bot1, botMiser, bot2,numberStep);
                }
                if (select == 2) {
                    checkCard = getMinCard(bot2.getCards());
                    log.info("\n" + bot2.getName() + " кладет:" + Card.getCard(checkCard));
                    card1 = getBenefitCard(botMiser.getCards(), checkCard);
                    log.info("\n" + botMiser.getName() + " кладет:" + Card.getCard(card1));
                    card2 = getBenefitCard(bot1.getCards(), checkCard);
                    log.info("\n" + bot1.getName() + " кладет:" + Card.getCard(card2));
                    comparisonCards(checkCard, card1, card2, bot2, botMiser, bot1, log);
                    step = writeSteps(bot2, botMiser, bot1,numberStep);
                }
            }
        }
        sequenceOfSteps.add(step);
        writePointsForBots(botMiser);
        writePointsForBots(bot1);
        writePointsForBots(bot2);

        return sequenceOfSteps;
    }

    private void writePointsForBots(Bot botMiser) {
        if (botMiser.getBribe() == 0) {
            int bullet = botMiser.getBullet() + 1;
            botMiser.setBullet(bullet);
        }
        if (botMiser.getBribe() != 0) {
            int hill = botMiser.getHill() + botMiser.getBribe();
            botMiser.setHill(hill);
        }

    }

    private String writeSteps(Bot bot1, Bot bot2, Bot bot3, int numberStep) {
        return "\nХод номер:" + numberStep
                + "\nХодит " + bot1.getName()
                + "\nХодит " + bot2.getName()
                + "\nХодит " + bot3.getName() + "\n";
    }
}
