package com.johanlind.gameoflife.Model;

import lombok.Data;

@Data
public class GameBoard {
    private int generation;
    private Cell[][] cells;

    public GameBoard(int height, int width) {
        this.generation = 0;
        this.cells = new Cell[height][width];
        setupGameBoard();
    }

    private void setupGameBoard() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(false);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Cell[] cellArray : cells) {
            for (Cell cell : cellArray) {
                board.append(cell.isAlive() + "|");
            }
            board.append("\n");
        }
        return board.toString();
    }
}
