package main.controller;

import main.model.Bot;
import main.model.Distributor;

public class InitializationGame {

    public void setNameBots(Bot bot1, Bot bot2, Bot bot3, Distributor distributer, String name1, String name2, String name3, String name4) {
        bot1.setName(name1);
        bot2.setName(name2);
        bot3.setName(name3);
        distributer.setName(name4);
    }
}
