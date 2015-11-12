/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.core;

import static com.mycompany.progseminar.tetris.core.Point.p;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author xhala3
 */
public enum Pieces {

    L,
    J,
    I,
    O,
    S,
    Z,
    T;

    private static final List<Point> BASE_L = asList(p(0, 0), p(0, -1), p(0, 1), p(1, 1));
    private static final List<Point> BASE_J = asList(p(0, 0), p(0, -1), p(0, 1), p(-1, 1));
    private static final List<Point> BASE_I = asList(p(0, 0), p(0, -1), p(0, 1), p(0, 2));
    private static final List<Point> BASE_O = asList(p(0, 0), p(0, 1), p(1, 0), p(1, 1));
    private static final List<Point> BASE_S = asList(p(0, 0), p(1, 0), p(0, 1), p(-1, 1));
    private static final List<Point> BASE_Z = asList(p(0, 0), p(0, 1), p(1, 1), p(-1, 0));
    private static final List<Point> BASE_T = asList(p(0, 0), p(-1, 0), p(1, 0), p(0, 1));

    private List<List<Point>> shapes;

    static {
        T.shapes = asList(
                normalize(BASE_T),
                normalize(rotated(BASE_T)),
                normalize(rotated(rotated(BASE_T))),
                normalize(rotated(rotated(rotated(BASE_T)))));
        L.shapes = asList(
                normalize(BASE_L),
                normalize(rotated(BASE_L)),
                normalize(rotated(rotated(BASE_L))),
                normalize(rotated(rotated(rotated(BASE_L)))));
        J.shapes = asList(
                normalize(BASE_J),
                normalize(rotated(BASE_J)),
                normalize(rotated(rotated(BASE_J))),
                normalize(rotated(rotated(rotated(BASE_J)))));
        I.shapes = asList(
                normalize(BASE_I),
                normalize(rotated(BASE_I)));
        O.shapes = asList(
                normalize(BASE_O));
        S.shapes = asList(
                normalize(BASE_S),
                normalize(rotated(BASE_S)));
        Z.shapes = asList(
                normalize(BASE_Z),
                normalize(rotated(BASE_Z)));
        
    }

    private List<List<Point>> associatedShapes() {
        return this.shapes;
    }

    public static List<List<Point>> getShapesOf(char symbol) {
        return Pieces.valueOf(String.valueOf(symbol)).associatedShapes();
    }

    static List<Point> normalize(List<Point> original) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Point p : original) {
            minX = Math.min(minX, p.x());
            minY = Math.min(minY, p.y());
        }
        List<Point> normalized = new ArrayList<>(original.size());
        for (Point p : original) {
            normalized.add(new Point(p.x() - minX, p.y() - minY));
        }
        Collections.sort(normalized);
        return normalized;
    }

    static List<Point> rotated(List<Point> points) {
        List<Point> rotated = new ArrayList<>();
        for (Point p : points) {
            rotated.add(p.rotate90Clockwise());
        }
        return rotated;
    }
}
