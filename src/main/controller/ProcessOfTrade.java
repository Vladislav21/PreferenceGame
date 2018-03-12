package main.controller;

import main.model.Bot;
import main.model.Card;

public class ProcessOfTrade {
    public String selectStrategy(Bot bot) {
        String nameStrategy = null;
        int countSameColorS = 0;
        int countSameColorC = 0;
        int countSameColorD = 0;
        int countSameColorH = 0;
        int countLessOrEquals10 = 0;
        for (Card card : bot.getCards()) {
            switch (card.getColor()) {
                case 0:
                    countSameColorS++;
                    break;
                case 1:
                    countSameColorC++;
                    break;
                case 2:
                    countSameColorD++;
                    break;
                case 3:
                    countSameColorH++;
                    break;
            }
            if (card.getValue() <= 3) {
                countLessOrEquals10++;
            }
        }
        if ((countSameColorS == 4 || countSameColorC == 4 || countSameColorD == 4 || countSameColorH == 4) & (countLessOrEquals10 < 7)) {
            bot.setValueContract(6);
            if (countSameColorS >= countSameColorC & countSameColorS >= countSameColorD & countSameColorS >= countSameColorH) {
                bot.setColorContract(0);
            }
            if (countSameColorC >= countSameColorS & countSameColorC >= countSameColorD & countSameColorC >= countSameColorH) {
                bot.setColorContract(1);
            }
            if (countSameColorD >= countSameColorS & countSameColorD >= countSameColorC & countSameColorD >= countSameColorH) {
                bot.setColorContract(2);
            }
            if (countSameColorH >= countSameColorS & countSameColorH >= countSameColorC & countSameColorH >= countSameColorD) {
                bot.setColorContract(3);
            }

            return nameStrategy = "Contract";
        }
        if ((countSameColorS == 5 || countSameColorC == 5 || countSameColorD == 5 || countSameColorH == 5) & (countLessOrEquals10 < 7)) {
            bot.setValueContract(7);
            if (countSameColorS >= countSameColorC & countSameColorS >= countSameColorD & countSameColorS >= countSameColorH) {
                bot.setColorContract(0);
            }
            if (countSameColorC >= countSameColorS & countSameColorC >= countSameColorD & countSameColorC >= countSameColorH) {
                bot.setColorContract(1);
            }
            if (countSameColorD >= countSameColorS & countSameColorD >= countSameColorC & countSameColorD >= countSameColorH) {
                bot.setColorContract(2);
            }
            if (countSameColorH >= countSameColorS & countSameColorH >= countSameColorC & countSameColorH >= countSameColorD) {
                bot.setColorContract(3);
            }
            return nameStrategy = "Contract";
        }
        if ((countSameColorS >= 6 || countSameColorC >= 6 || countSameColorD >= 6 || countSameColorH >= 6) & (countLessOrEquals10 < 7)) {
            bot.setValueContract(8);
            if (countSameColorS >= countSameColorC & countSameColorS >= countSameColorD & countSameColorS >= countSameColorH) {
                bot.setColorContract(0);
            }
            if (countSameColorC >= countSameColorS & countSameColorC >= countSameColorD & countSameColorC >= countSameColorH) {
                bot.setColorContract(1);
            }
            if (countSameColorD >= countSameColorS & countSameColorD >= countSameColorC & countSameColorD >= countSameColorH) {
                bot.setColorContract(2);
            }
            if (countSameColorH >= countSameColorS & countSameColorH >= countSameColorC & countSameColorH >= countSameColorD) {
                bot.setColorContract(3);
            }
            return nameStrategy = "Contract";
        }
        if (countLessOrEquals10 >= 7) {
            return nameStrategy = "Misery";
        } else {
            return nameStrategy = "Pass";
        }
    }

    public String processTrade(String strategyBot1, String strategyBot2, String strategyBot3, Bot bot1, Bot bot2, Bot bot3) {
        String typeOfGame = null;
        if (strategyBot1.equals("Pass") & strategyBot2.equals("Pass") & strategyBot3.equals("Pass")) {
            return typeOfGame = "Unpacking";
        }
        if (strategyBot1.equals("Misery") || strategyBot2.equals("Misery") || strategyBot3.equals("Misery")) {
            if (strategyBot1.equals("Misery")) {
                bot1.setMiser(true);
                return typeOfGame = "Misery";
            }
            if (strategyBot2.equals("Misery")) {
                bot2.setMiser(true);
                return typeOfGame = "Misery";
            }
            if (strategyBot3.equals("Misery")) {
                bot3.setMiser(true);
                return typeOfGame = "Misery";
            }
        }
        if (strategyBot1.equals("Contract") & !strategyBot2.equals("Contract") & !strategyBot3.equals("Contract")) {
            bot1.setContractor(true);
            return typeOfGame = "Contract";
        }
        if (strategyBot2.equals("Contract") & !strategyBot1.equals("Contract") & !strategyBot3.equals("Contract")) {
            bot2.setContractor(true);
            return typeOfGame = "Contract";
        }
        if (strategyBot3.equals("Contract") & !strategyBot1.equals("Contract") & !strategyBot2.equals("Contract")) {
            bot3.setContractor(true);
            return typeOfGame = "Contract";
        }
        if (strategyBot1.equals("Contract") & strategyBot2.equals("Contract") & strategyBot3.equals("Contract")) {
            if (bot1.getValueContract() >= bot2.getValueContract() & bot1.getValueContract() >= bot3.getValueContract()) {
                bot1.setContractor(true);
                return typeOfGame = "Contract";
            }
            if (bot2.getValueContract() >= bot1.getValueContract() & bot2.getValueContract() >= bot3.getValueContract()) {
                bot2.setContractor(true);
                return typeOfGame = "Contract";
            }
            if (bot3.getValueContract() >= bot1.getValueContract() & bot3.getValueContract() >= bot2.getValueContract()) {
                bot3.setContractor(true);
                return typeOfGame = "Contract";
            }
            if (bot1.getValueContract() == bot2.getValueContract() & bot1.getValueContract() == bot3.getValueContract() & bot2.getValueContract() == bot3.getValueContract()) {
                if (bot1.getColorContract() >= bot2.getColorContract() & bot1.getColorContract() >= bot3.getColorContract()) {
                    bot1.setContractor(true);
                    return typeOfGame = "Contract";
                }
                if (bot2.getColorContract() >= bot1.getColorContract() & bot2.getColorContract() >= bot3.getColorContract()) {
                    bot2.setContractor(true);
                    return typeOfGame = "Contract";
                }
                if (bot3.getColorContract() >= bot1.getColorContract() & bot3.getColorContract() >= bot2.getColorContract()) {
                    bot3.setContractor(true);
                    return typeOfGame = "Contract";
                }
            }
        }
        if (strategyBot1.equals("Contract") & strategyBot2.equals("Contract") & !strategyBot3.equals("Contract")) {
            return typeOfGame = comprasionOfContracts(bot1, bot2);
        }
        if (strategyBot2.equals("Contract") & strategyBot3.equals("Contract") & !strategyBot1.equals("Contract")) {
            return typeOfGame = comprasionOfContracts(bot2, bot3);
        }
        if (strategyBot1.equals("Contract") & strategyBot3.equals("Contract") & !strategyBot2.equals("Contract")) {
            return typeOfGame = comprasionOfContracts(bot1, bot3);
        }
        return typeOfGame = "Not determined";
    }

    private String comprasionOfContracts(Bot bot11, Bot bot22) {
        String typeOfGame1 = null;
        if (bot11.getValueContract() > bot22.getValueContract()) {
            bot11.setContractor(true);
            typeOfGame1 = "Contract";
        }
        if (bot22.getValueContract() > bot11.getValueContract()) {
            bot22.setContractor(true);
            typeOfGame1 = "Contract";
        }
        if (bot11.getValueContract() == bot22.getValueContract()) {
            if (bot11.getColorContract() >= bot22.getColorContract()) {
                bot11.setContractor(true);
                typeOfGame1 = "Contract";
            }
            if (bot22.getColorContract() >= bot11.getColorContract()) {
                bot22.setContractor(true);
                typeOfGame1 = "Contract";
            }
        }
        return typeOfGame1;
    }
}
