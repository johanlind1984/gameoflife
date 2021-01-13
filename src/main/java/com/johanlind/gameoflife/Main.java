package com.johanlind.gameoflife;

import com.johanlind.gameoflife.Controller.MainViewController;
import com.johanlind.gameoflife.Engine.GameOfLifeEngine;


public class Main {

    public static final int height = 50;
    public static final int width = 50;

    public static void main(String[] args) throws InterruptedException {
        GameOfLifeEngine gameOfLifeEngine = new GameOfLifeEngine(height, width);
        gameOfLifeEngine.randomizeGameBoardValues();
        MainViewController mainViewController = new MainViewController(gameOfLifeEngine);
        mainViewController.randomizeCells();
        mainViewController.runSimulation();
    }
}
