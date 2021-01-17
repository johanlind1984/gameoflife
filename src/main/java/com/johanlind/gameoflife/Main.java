package com.johanlind.gameoflife;

import com.johanlind.gameoflife.Controller.GameOfLifeController;
import com.johanlind.gameoflife.View.MainView;


public class Main {

    public static final int height = 100;
    public static final int width = 100;

    public static void main(String[] args) throws InterruptedException {
        GameOfLifeController gameOfLifeController = new GameOfLifeController(height, width);
        MainView manView = new MainView(gameOfLifeController);
        manView.randomizeCells();
        manView.runSimulation();
    }
}
