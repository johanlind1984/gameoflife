package com.johanlind.gameoflife.Controller;

import com.johanlind.gameoflife.Model.Cell;
import com.johanlind.gameoflife.Model.GameBoard;
import lombok.Data;

import java.util.Random;

@Data
public class GameOfLifeController {
    private int boardHeight;
    private int boardWidth;
    private GameBoard gameBoard;

    public GameOfLifeController(int boardHeight, int boardWidth) {
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.gameBoard = new GameBoard(boardHeight, boardWidth);
    }

    public void randomizeGameBoardValues() {
        for (Cell[] cellArray : gameBoard.getCells()) {
            for (Cell cell: cellArray) {
                cell.setAlive(getRandomBool());
            }
        }
    }

    public void generateNextGeneration() {
        Cell[][] nextGenCells = new GameBoard(boardHeight, boardWidth).getCells();
        gameBoard.setGeneration(gameBoard.getGeneration() + 1);

        for (int i = 0; i < boardHeight - 1; i++) {
            for (int j = 0; j < boardWidth - 1; j++) {
                int surroundingCellsCount = getCellsAdjacentCellCount(i,j);

                switch (surroundingCellsCount) {
                    case 2:
                        if(gameBoard.getCells()[i][j].isAlive()) {
                            nextGenCells[i][j].setAlive(true);
                        } else {
                            nextGenCells[i][j].setAlive(false);
                        }
                        break;
                    case 3:
                        nextGenCells[i][j].setAlive(true);
                        break;
                    default:
                        nextGenCells[i][j].setAlive(false);
                }
            }
        }
        gameBoard.setCells(nextGenCells);
    }

    public int getCellsAdjacentCellCount(int xPosition, int yPosition) {
        int surroundingCellsCount = 0;
        for(int i = xPosition - 1; i <= xPosition + 1; i++) {
            for(int j = yPosition - 1; j <= yPosition + 1; j++) {
                if(!isCellOutOfBounds(i, j) && !isPositionSelf(xPosition, yPosition, i, j)) {
                    if(gameBoard.getCells()[i][j].isAlive()) {
                        surroundingCellsCount += 1;
                   }
                }
            }
        }

        return surroundingCellsCount;
    }

    public boolean isPositionSelf(int xPosition, int yPosition, int xCheckPosition, int yCheckPosition) {
        return (xPosition == xCheckPosition) && (yPosition == yCheckPosition);
    }

    public boolean isCellOutOfBounds(int xPosition, int yPosition) {
        if(xPosition < 0 || xPosition > boardHeight - 1) {
            return true;
        } else if(yPosition < 0 || yPosition > boardWidth - 1) {
            return true;
        }
        return false;
    }

    private boolean getRandomBool() {
        return new Random().nextBoolean();
    }
}
