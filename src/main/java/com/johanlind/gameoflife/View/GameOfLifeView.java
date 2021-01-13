package com.johanlind.gameoflife.View;

import com.johanlind.gameoflife.Engine.GameOfLifeEngine;
import com.johanlind.gameoflife.Model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLifeView {

    private GameOfLifeEngine engine;
    private final int resolutionWidth = 1080;
    private final int resolutionHeight = 1080;

    public GameOfLifeView(GameOfLifeEngine engine) {
        this.engine = engine;
        JPanel gameBoardPanel = getGameBoardPanel();
        JFrame frame = getJFrame();
        frame.add(gameBoardPanel);
        frame.add(getButtonComponent());
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

    private JPanel getGameBoardPanel() {
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

    private JPanel getButtonComponent() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        JButton startButton = new JButton();
        startButton.setSize(100,50);
        startButton.setText("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        return buttonPanel;
    }
}
