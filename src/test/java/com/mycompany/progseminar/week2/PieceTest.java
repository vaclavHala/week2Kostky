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
    public void testComparePoints() {
        assertTrue(new Point(0, 0).compareTo(new Point(0, 0)) == 0);
        assertTrue(new Point(1, 0).compareTo(new Point(0, 0)) > 0);
        assertTrue(new Point(1, 1).compareTo(new Point(2, 0)) > 0);
    }

    @Test
    public void testAdd() {
        assertEquals(
                new Piece(asList(new Point(0, 0), new Point(0, 1))),
                ORIGIN.appendAt(ORIGIN.points().get(0), Side.UP)
        );
    }

    @Test
    public void testRotation() {
        assertEquals(
                new Point(1, 0),
                new Point(0, 1).rotate90Clockwise());
        
        assertEquals(
                new Point(3, 2),
                new Point(-2, 3).rotate90Clockwise());
        
    }
    /*
    @Test
    public void testNormalization(){
        System.out.println("-------------------START---------------");
        PieceCreator creator = new PieceCreator();
        System.out.println(Piece.normalize(creator.create(
                "  * \n"+
                " ***\n"+
                " *  ")).render());
        System.out.println("-------------------END---------------");
                
    } */

}
