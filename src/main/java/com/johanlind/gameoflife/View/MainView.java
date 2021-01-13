package com.johanlind.gameoflife.View;

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

    public MainView(GameOfLifeController gameOfLifeController) {
        this.gameOfLifeController = gameOfLifeController;
        this.gameBoard = getGameBoard();
        this.frame = getJFrame();
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).run();
    }

    private void nextGeneration(JPanel gameBoard) throws InterruptedException {
        gameOfLifeController.generateNextGeneration();
        refreshGameBoard(gameBoard);
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    private void refreshGameBoard(JPanel gameBoard) {
        gameBoard.removeAll();
        drawCells(gameBoard);
        gameBoard.updateUI();
    }

    private void drawCells(JPanel panel) {
        for (Cell[] cellRow : gameOfLifeController.getGameBoard().getCells()) {
            for (Cell cell : cellRow) {
                if(cell.isAlive()) {
                    panel.add(getAliveCell());
                } else {
                    panel.add(getDeadCell());
                }
            }
        }
    }

    // Bryt ut denna till view
    private JFrame getJFrame() {
        JFrame frame = new JFrame("Game of Life");
        frame.setPreferredSize(new Dimension(resolutionWidth,resolutionHeight));
        frame.setLayout(new GridLayout(1,1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        return frame;
    }

    private JPanel getGameBoard() {
        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(gameOfLifeController.getBoardWidth(), gameOfLifeController.getBoardHeight()));
        drawCells(gameBoard);
        return gameBoard;
    }

    private JPanel getDeadCell() {
        JPanel deadCell = new JPanel();
        deadCell.setBackground(Color.WHITE);
        return deadCell;
    }

    private JPanel getAliveCell() {
        JPanel aliveCell = new JPanel();
        aliveCell.setBackground(Color.RED);
        return aliveCell;
    }
}
