/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import static com.mycompany.progseminar.tetris.Point.p;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xhala3
 */
public class PointComparatorTest {
    
    @Test
    public void test(){
        PointComparator c = new PointComparator();
        assertTrue(c.compare(p(1,1), p(1,1)) == 0);
        assertTrue(c.compare(p(2,1), p(1,1)) > 0);
        assertTrue(c.compare(p(2,1), p(1,2)) < 0);
    }
    
}
