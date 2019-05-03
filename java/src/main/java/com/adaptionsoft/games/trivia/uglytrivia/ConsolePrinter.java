package com.adaptionsoft.games.trivia.uglytrivia;

public class ConsolePrinter implements IPrinter {
    public ConsolePrinter() {
    }

    @Override
    public void print(String x) {
        System.out.println(x);
    }
}