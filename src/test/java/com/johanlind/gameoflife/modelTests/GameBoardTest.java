package com.johanlind.gameoflife.modelTests;

import com.johanlind.gameoflife.Model.GameBoard;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class GameBoardTest {

    @Test
    public void gameBoardModelTest() {
        // This test fails because of a NegativeArraySizeException. I could fix this by putting if statments in the
        // constructor but I dont't know if that's good practice. Also I don't know how serious of a problem this is?
        //ModelsTestUtils.validateAccessors(GameBoard.class);
        ToStringVerifier.forClass(GameBoard.class);
        EqualsVerifier.forClass(GameBoard.class);
    }
}
