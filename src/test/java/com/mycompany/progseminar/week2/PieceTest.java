/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.week2;

import static com.mycompany.progseminar.week2.Piece.ORIGIN;
import static java.util.Arrays.asList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xhala3
 */
public class PieceTest {
    
    @Test
    public void testComparePoints(){
        assertTrue(new Point(0,0).compareTo(new Point(0, 0)) == 0);
        assertTrue(new Point(1,0).compareTo(new Point(0, 0)) > 0);
        assertTrue(new Point(1,1).compareTo(new Point(2, 0)) > 0);
    }
    
    @Test
    public void testAdd(){
        System.out.println(ORIGIN.points());
        
        assertEquals(new Piece(asList(new Point(0, 0), new Point(0, 1))),
                ORIGIN.appendAt(ORIGIN.points().get(0), Side.UP)
                );
    }
    
    
}
