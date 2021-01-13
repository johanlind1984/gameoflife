package com.johanlind.gameoflife;

import com.johanlind.gameoflife.Controller.GameOfLifeController;
import com.johanlind.gameoflife.Model.Cell;
import com.johanlind.gameoflife.Model.GameBoard;
import org.junit.Assert;
import org.junit.Test;

public class GameOfLifeControllerTest {

    @Test
    public void isCellOutOfBounds_shouldReturnTrueIfCellIsOutOfBounds_test() {
        GameOfLifeController controller = new GameOfLifeController(5,5);

        // Checking negative positions
        Assert.assertTrue(controller.isCellOutOfBounds(-1,2));
        Assert.assertTrue(controller.isCellOutOfBounds(1,-1));
        Assert.assertTrue(controller.isCellOutOfBounds(-1,-1));

        // Checking positions over the boards size
        Assert.assertTrue(controller.isCellOutOfBounds(5,1));
        Assert.assertTrue(controller.isCellOutOfBounds(1,5));
        Assert.assertTrue(controller.isCellOutOfBounds(5,5));
    }

    @Test
    public void isCellOutOfBounds_shouldReturnFalseIfCellIsWithinBounds_test() {
        GameOfLifeController controller = new GameOfLifeController(5,5);

        // Checking corner positions
        Assert.assertFalse(controller.isCellOutOfBounds(0,0));
        Assert.assertFalse(controller.isCellOutOfBounds(4,0));
        Assert.assertFalse(controller.isCellOutOfBounds(0,4));
        Assert.assertFalse(controller.isCellOutOfBounds(4,4));

        // Checking positions top, down, left, right
        Assert.assertFalse(controller.isCellOutOfBounds(0,2));
        Assert.assertFalse(controller.isCellOutOfBounds(4,2));
        Assert.assertFalse(controller.isCellOutOfBounds(2,0));
        Assert.assertFalse(controller.isCellOutOfBounds(2,4));

        // Checking middle
        Assert.assertFalse(controller.isCellOutOfBounds(3,3));

    }

    @Test
    public void getCellsAdjacentCellCount_shouldReturnCorrectCount_test(){
        GameOfLifeController controller = new GameOfLifeController(3,3);
        controller.getGameBoard().getCells()[0][0].setAlive(true);
        controller.getGameBoard().getCells()[1][1].setAlive(true);
        controller.getGameBoard().getCells()[0][1].setAlive(true);

        Assert.assertEquals(3, controller.getCellsAdjacentCellCount(1,0));
        Assert.assertEquals(1, controller.getCellsAdjacentCellCount(2,2));
        Assert.assertEquals(2, controller.getCellsAdjacentCellCount(0,0));
    }

    @Test
    public void IsPositionSelf_shouldReturnTrueIfCheckpositionIsTheSame_test() {
        GameOfLifeController controller = new GameOfLifeController(5,5);
        Assert.assertTrue(controller.isPositionSelf(4,1, 4, 1));
        Assert.assertTrue(controller.isPositionSelf(0,0,0,0));
        Assert.assertFalse(controller.isPositionSelf(1,1,0,0));
        Assert.assertFalse(controller.isPositionSelf(3,3,1,3));
        Assert.assertFalse(controller.isPositionSelf(4,3,4,1));
    }

    @Test
    public void generateNextGeneration_shouldGenerateANewGeneration_test() {
        GameOfLifeController controller = new GameOfLifeController(3,3);
        controller.getGameBoard().getCells()[0][0].setAlive(true);
        controller.getGameBoard().getCells()[1][1].setAlive(true);
        controller.getGameBoard().getCells()[0][1].setAlive(true);
        controller.generateNextGeneration();
    }

    @Test
    public void generateNextGenerationBlock_shouldGenerateANewGenerationBlock_test() {
        GameOfLifeController controller = new GameOfLifeController(4,4);
        controller.getGameBoard().getCells()[1][1].setAlive(true);
        controller.getGameBoard().getCells()[1][2].setAlive(true);
        controller.getGameBoard().getCells()[2][1].setAlive(true);
        controller.getGameBoard().getCells()[2][2].setAlive(true);

        Cell[][] cellGen1 = controller.getGameBoard().getCells();
        controller.generateNextGeneration();

        Assert.assertArrayEquals(cellGen1, controller.getGameBoard().getCells());
    }

    @Test
    public void generateNextGenerationBlinker_shouldGenerateANewGenerationBlinker_test() {
        GameOfLifeController controller = new GameOfLifeController(5,5);
        controller.getGameBoard().getCells()[1][2].setAlive(true);
        controller.getGameBoard().getCells()[2][2].setAlive(true);
        controller.getGameBoard().getCells()[3][2].setAlive(true);

        Cell[][] expected = new GameBoard(5,5).getCells();
        expected[2][1].setAlive(true);
        expected[2][2].setAlive(true);
        expected[2][3].setAlive(true);

        controller.generateNextGeneration();

        Assert.assertArrayEquals(expected, controller.getGameBoard().getCells());
    }

    @Test
    public void generateNextGenerationTub_shouldGenerateANewGenerationTub_test() {
        GameOfLifeController controller = new GameOfLifeController(5,5);
        controller.getGameBoard().getCells()[1][2].setAlive(true);
        controller.getGameBoard().getCells()[2][1].setAlive(true);
        controller.getGameBoard().getCells()[2][3].setAlive(true);
        controller.getGameBoard().getCells()[3][2].setAlive(true);

        Cell[][] cellGen1 = controller.getGameBoard().getCells();
        controller.generateNextGeneration();

        Assert.assertArrayEquals(cellGen1, controller.getGameBoard().getCells());
    }


}
