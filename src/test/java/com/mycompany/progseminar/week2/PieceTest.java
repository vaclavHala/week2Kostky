/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.week2;

import static com.mycompany.progseminar.week2.Piece.ORIGIN;
import static java.util.Arrays.asList;
import java.util.LinkedHashSet;
import java.util.Set;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author xhala3
 */
public class PieceTest {

    @Test
    public void testComparePoints() {
        assertTrue(new Point2(0, 0).compareTo(new Point2(0, 0)) == 0);
        assertTrue(new Point2(1, 0).compareTo(new Point2(0, 0)) > 0);
        assertTrue(new Point2(1, 1).compareTo(new Point2(2, 0)) > 0);
    }

    @Test
    public void testAdd() {
        assertEquals(
            new Piece(asList(new Point2(0, 0), new Point2(0, 1))),
            ORIGIN.appendAt(ORIGIN.points().get(0), Side.UP)
        );
    }

    @Test
    public void testRotation() {
        assertEquals(
            new Point2(1, 0),
            new Point2(0, 1).rotate90Clockwise());

        assertEquals(
            new Point2(3, 2),
            new Point2(-2, 3).rotate90Clockwise());

    }

    @Test
    public void testNormalization() {
        assertEquals(
            new Piece(asList(new Point2(0, 0), new Point2(1, 0), new Point2(0, 1))),
            new Piece(asList(new Point2(1, 2), new Point2(2, 2), new Point2(1, 3)))
        );
    }

    @Test(expected = ExceptionInInitializerError.class)
    public void testDuplicationInCtor() {
        new Piece(asList(new Point2(0, 0), new Point2(0, 0)));
    }

    @Test
    public void testEqualsRotated() {
        assertEquals(
            new Piece(asList(new Point2(0, 0), new Point2(1, 0))),
            new Piece(asList(new Point2(1, 2), new Point2(1, 3)))
        );

        Set<Piece> pieces = new LinkedHashSet<>();
        pieces.add(new Piece(asList(new Point2(0, 0), new Point2(1, 0), new Point2(0, 1))));
        pieces.add(new Piece(asList(new Point2(1, 2), new Point2(2, 2), new Point2(1, 3))));
        System.out.println(pieces.size());

        assertEquals(1, asList(
                     new Piece(asList(new Point2(0, 0), new Point2(1, 0), new Point2(0, 1))),
                     new Piece(asList(new Point2(1, 2), new Point2(2, 2), new Point2(1, 3)))
                 ).stream().distinct().collect(toList()).size());
    }

    @Test
    public void testPieceHashNotDependentOnRotationAndMirroring() {
        int hash = new Piece(asList(new Point2(0, 0), new Point2(0, 1), new Point2(1, 1))).hashCode();
        assertEquals(hash, new Piece(asList(new Point2(0, 0), new Point2(0, 1), new Point2(1, 1))).hashCode());
        assertEquals(hash, new Piece(asList(new Point2(0, 1), new Point2(1, 0), new Point2(1, 1))).hashCode());
    }

}
