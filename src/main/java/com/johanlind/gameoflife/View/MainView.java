package com.johanlind.gameoflife.View;

import com.johanlind.gameoflife.Component.AliveCellPanel;
import com.johanlind.gameoflife.Component.DeadCellPanel;
import com.johanlind.gameoflife.Component.GameBoardPanel;
import com.johanlind.gameoflife.Component.MainFrame;
import com.johanlind.gameoflife.Controller.GameOfLifeController;
import com.johanlind.gameoflife.Model.Cell;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

@Data
public class MainView {
    private GameOfLifeController gameOfLifeController;
    private JPanel gameBoard;
    private JFrame frame;
    private final int resolutionWidth = 750;
    private final int resolutionHeight = 750;
    private final int gameSpeed = 1000;

    public MainView(GameOfLifeController gameOfLifeController) {
        this.gameOfLifeController = gameOfLifeController;
        this.gameBoard = new GameBoardPanel(gameOfLifeController.getBoardWidth(), gameOfLifeController.getBoardHeight());
        this.frame = new MainFrame(resolutionWidth, resolutionHeight);
        randomizeCells();
        frame.add(gameBoard);
    }

    public void randomizeCells() {
        gameOfLifeController.randomizeGameBoardValues();
    }

    public void runSimulation() {
        ((Runnable) () -> {
            while (true) {
                try {
                    nextGeneration(gameBoard);
                    TimeUnit.MILLISECONDS.sleep(gameSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

    private void nextGeneration(JPanel gameBoard) throws InterruptedException {
        gameOfLifeController.generateNextGeneration();
        refreshGameBoard(gameBoard);
    }

    private void refreshGameBoard(JPanel gameBoard) {
        gameBoard.removeAll();
        drawCells(gameBoard);
        gameBoard.updateUI();
    }

    private void drawCells(JPanel gameBoard) {
        for (Cell[] cellRow : gameOfLifeController.getGameBoard().getCells()) {
            for (Cell cell : cellRow) {
                if(cell.isAlive()) {
                    gameBoard.add(new AliveCellPanel());
                } else {
                    gameBoard.add(new DeadCellPanel());
                }
            }
        }
    }
}
