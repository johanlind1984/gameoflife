package com.johanlind.gameoflife.Controller;

import com.johanlind.gameoflife.Engine.GameOfLifeEngine;
import com.johanlind.gameoflife.Model.Cell;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

@Data
public class MainViewController {
    private GameOfLifeEngine engine;
    private JPanel gameBoard;
    private JFrame frame;
    private final int resolutionWidth = 750;
    private final int resolutionHeight = 750;

    public MainViewController(GameOfLifeEngine engine) {
        this.engine = engine;
        this.gameBoard = getGameBoard();
        this.frame = getJFrame();
        frame.add(gameBoard);
    }

    public void randomizeCells() {
        engine.randomizeGameBoardValues();
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
        engine.generateNextGeneration();
        refreshGameBoard(gameBoard);
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    private void refreshGameBoard(JPanel gameBoard) {
        gameBoard.removeAll();
        drawCells(gameBoard);
        gameBoard.updateUI();
    }

    private void drawCells(JPanel panel) {
        for (Cell[] cellRow : engine.getGameBoard().getCells()) {
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
        gameBoard.setLayout(new GridLayout(engine.getBoardWidth(),engine.getBoardHeight()));
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
