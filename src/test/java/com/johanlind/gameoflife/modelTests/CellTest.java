package com.johanlind.gameoflife.modelTests;

import com.johanlind.gameoflife.Model.Cell;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class CellTest {

    @Test
    public void cellModelTest() {
        ModelsTestUtils.validateAccessors(Cell.class);
        ToStringVerifier.forClass(Cell.class);
        EqualsVerifier.forClass(Cell.class);
    }
}
