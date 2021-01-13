package com.johanlind.gameoflife;

import com.johanlind.gameoflife.Model.Cell;
import com.johanlind.gameoflife.Model.GameBoard;
import org.junit.Assert;
import org.junit.Test;


public class GameBoardTest {

    @Test
    public void whenCreatingGameBoard_AllCellsShouldBeFalse() {
        GameBoard gameBoard = new GameBoard(10,10);
        for (Cell[] cellArray : gameBoard.getCells()) {
            for (Cell cell :cellArray) {
                Assert.assertFalse(cell.isAlive());
            }
        }
    }
}
