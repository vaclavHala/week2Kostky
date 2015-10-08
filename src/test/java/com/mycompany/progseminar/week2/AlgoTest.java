package com.mycompany.progseminar.week2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AlgoTest {

    @Test
    public void testCorrectSizes() {
        //assertEquals(35, Algo.doIt(6).size());
        assertEquals(369, Algo.doIt(8).size());
        // assertEquals(1285, Algo.doIt(9).size());
    }

}
