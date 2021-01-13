package com.johanlind.gameoflife;

import com.johanlind.gameoflife.Engine.GameOfLifeEngine;
import com.johanlind.gameoflife.Model.Cell;
import com.johanlind.gameoflife.Model.GameBoard;
import org.junit.Assert;
import org.junit.Test;

public class GameOfLifeEngineTest {

    @Test
    public void isCellOutOfBounds_shouldReturnTrueIfCellIsOutOfBounds_test() {
        GameOfLifeEngine engine = new GameOfLifeEngine(5,5);

        // Checking negative positions
        Assert.assertTrue(engine.isCellOutOfBounds(-1,2));
        Assert.assertTrue(engine.isCellOutOfBounds(1,-1));
        Assert.assertTrue(engine.isCellOutOfBounds(-1,-1));

        // Checking positions over the boards size
        Assert.assertTrue(engine.isCellOutOfBounds(5,1));
        Assert.assertTrue(engine.isCellOutOfBounds(1,5));
        Assert.assertTrue(engine.isCellOutOfBounds(5,5));
    }

    @Test
    public void isCellOutOfBounds_shouldReturnFalseIfCellIsWithinBounds_test() {
        GameOfLifeEngine engine = new GameOfLifeEngine(5,5);

        // Checking corner positions
        Assert.assertFalse(engine.isCellOutOfBounds(0,0));
        Assert.assertFalse(engine.isCellOutOfBounds(4,0));
        Assert.assertFalse(engine.isCellOutOfBounds(0,4));
        Assert.assertFalse(engine.isCellOutOfBounds(4,4));

        // Checking positions top, down, left, right
        Assert.assertFalse(engine.isCellOutOfBounds(0,2));
        Assert.assertFalse(engine.isCellOutOfBounds(4,2));
        Assert.assertFalse(engine.isCellOutOfBounds(2,0));
        Assert.assertFalse(engine.isCellOutOfBounds(2,4));

        // Checking middle
        Assert.assertFalse(engine.isCellOutOfBounds(3,3));

    }

    @Test
    public void getCellsAdjacentCellCount_shouldReturnCorrectCount_test(){
        GameOfLifeEngine engine = new GameOfLifeEngine(3,3);
        engine.getGameBoard().getCells()[0][0].setAlive(true);
        engine.getGameBoard().getCells()[1][1].setAlive(true);
        engine.getGameBoard().getCells()[0][1].setAlive(true);

        Assert.assertEquals(3, engine.getCellsAdjacentCellCount(1,0));
        Assert.assertEquals(1, engine.getCellsAdjacentCellCount(2,2));
        Assert.assertEquals(2, engine.getCellsAdjacentCellCount(0,0));
    }

    @Test
    public void IsPositionSelf_shouldReturnTrueIfCheckpositionIsTheSame_test() {
        GameOfLifeEngine engine = new GameOfLifeEngine(5,5);
        Assert.assertTrue(engine.isPositionSelf(4,1, 4, 1));
        Assert.assertTrue(engine.isPositionSelf(0,0,0,0));
        Assert.assertFalse(engine.isPositionSelf(1,1,0,0));
        Assert.assertFalse(engine.isPositionSelf(3,3,1,3));
        Assert.assertFalse(engine.isPositionSelf(4,3,4,1));
    }

    @Test
    public void generateNextGeneration_shouldGenerateANewGeneration_test() {
        GameOfLifeEngine engine = new GameOfLifeEngine(3,3);
        engine.getGameBoard().getCells()[0][0].setAlive(true);
        engine.getGameBoard().getCells()[1][1].setAlive(true);
        engine.getGameBoard().getCells()[0][1].setAlive(true);
        engine.generateNextGeneration();
    }

    @Test
    public void generateNextGenerationBlock_shouldGenerateANewGenerationBlock_test() {
        GameOfLifeEngine engine = new GameOfLifeEngine(4,4);
        engine.getGameBoard().getCells()[1][1].setAlive(true);
        engine.getGameBoard().getCells()[1][2].setAlive(true);
        engine.getGameBoard().getCells()[2][1].setAlive(true);
        engine.getGameBoard().getCells()[2][2].setAlive(true);

        Cell[][] cellGen1 = engine.getGameBoard().getCells();
        engine.generateNextGeneration();

        Assert.assertArrayEquals(cellGen1, engine.getGameBoard().getCells());
    }

    @Test
    public void generateNextGenerationBlinker_shouldGenerateANewGenerationBlinker_test() {
        GameOfLifeEngine engine = new GameOfLifeEngine(5,5);
        engine.getGameBoard().getCells()[1][2].setAlive(true);
        engine.getGameBoard().getCells()[2][2].setAlive(true);
        engine.getGameBoard().getCells()[3][2].setAlive(true);

        Cell[][] expected = new GameBoard(5,5).getCells();
        expected[2][1].setAlive(true);
        expected[2][2].setAlive(true);
        expected[2][3].setAlive(true);

        engine.generateNextGeneration();

        Assert.assertArrayEquals(expected, engine.getGameBoard().getCells());
    }

    @Test
    public void generateNextGenerationTub_shouldGenerateANewGenerationTub_test() {
        GameOfLifeEngine engine = new GameOfLifeEngine(5,5);
        engine.getGameBoard().getCells()[1][2].setAlive(true);
        engine.getGameBoard().getCells()[2][1].setAlive(true);
        engine.getGameBoard().getCells()[2][3].setAlive(true);
        engine.getGameBoard().getCells()[3][2].setAlive(true);

        Cell[][] cellGen1 = engine.getGameBoard().getCells();
        engine.generateNextGeneration();

        Assert.assertArrayEquals(cellGen1, engine.getGameBoard().getCells());
    }


}
