/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.week2;

import static com.mycompany.progseminar.week2.Piece.ORIGIN;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author xhala3
 */
public class Algo {

    public static void main(String[] args) {
        doIt(7).stream()
            .forEach(p -> System.out.println(p.render()));

    }

    public static Collection<Piece> doIt(int n) {
        Collection<Piece> previous = asList(ORIGIN);
        Collection<Piece> current = previous;
        for (int i = 1; i < n; i++) {
            current = iterate(previous);
            previous = current;
        }
        return current;
    }

    static Set<Piece> iterate(Collection<Piece> previous) {
        Set<Piece> pieces = new HashSet<>();
        for (Piece piece : previous) {
            pieces.addAll(appendedToOne(piece));
        }
        return pieces;
    }

    static Set<Piece> appendedToOne(Piece piece) {
        Set<Piece> newPieces = new HashSet<>();
        for (Point2 startingPoint : piece.points()) {
            for (Side side : Side.values()) {
                Point2 newPoint = side.plus(startingPoint);
                if (piece.points().contains(newPoint)) {
                    continue;
                }
                List<Point2> newPoints = new ArrayList<>();
                newPoints.addAll(piece.points());
                newPoints.add(newPoint);
                newPieces.add(new Piece(newPoints));
            }
        }
        return newPieces;
    }

}
