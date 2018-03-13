package main.controller;

import main.model.Bot;
import main.model.Card;
import main.model.Distributor;
import org.apache.log4j.Logger;

import java.util.List;

public interface GameStrategy {

    List<String> run(Bot bot1, Bot bot2, Bot bot3, Distributor distributor, Logger logger, List<String> sequenceOfSteps);

    void comparisonCards(Card card11, Card card22, Card card33, Bot bot11, Bot bot22, Bot bot33, Logger log);
}
