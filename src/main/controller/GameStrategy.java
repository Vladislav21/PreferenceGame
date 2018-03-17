package main.controller;

import main.model.Bot;
import main.model.Card;
import main.model.Distributor;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Общий интерфейс для стратегий игр, который имеет два метода: запустить и сравнить карты
 */
public interface GameStrategy {

    List<String> run(Bot bot1, Bot bot2, Bot bot3, Distributor distributor, Logger logger, List<String> sequenceOfSteps);

}
