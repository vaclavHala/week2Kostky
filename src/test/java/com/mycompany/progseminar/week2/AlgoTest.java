
package com.mycompany.progseminar.week2;

import org.junit.Test;
import static org.junit.Assert.*;

public class AlgoTest {

    @Test
    public void testCorrectSizes() {
        assertEquals(1, Algo.doIt(1).size());
        assertEquals(1, Algo.doIt(2).size());
        assertEquals(2, Algo.doIt(3).size());
        assertEquals(35, Algo.doIt(6).size());
    }

}