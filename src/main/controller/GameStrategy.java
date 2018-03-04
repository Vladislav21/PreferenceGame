package main.controller;

import main.model.Bot;

public interface GameStrategy {
    void run(Bot bot1, Bot bot2, Bot bot3);
}
