/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.core;

import static com.mycompany.progseminar.tetris.core.Offset.off;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.toList;

public enum Pieces {

    L,
    J,
    I,
    O,
    S,
    Z,
    T;

    private static final Shape BASE_L = new Shape(asList(off(0, 0), off(0, -1), off(0, 1), off(1, 1)));
    private static final Shape BASE_J = new Shape(asList(off(0, 0), off(0, -1), off(0, 1), off(-1, 1)));
    private static final Shape BASE_I = new Shape(asList(off(0, 0), off(0, -1), off(0, 1), off(0, 2)));
    private static final Shape BASE_O = new Shape(asList(off(0, 0), off(0, 1), off(1, 0), off(1, 1)));
    private static final Shape BASE_S = new Shape(asList(off(0, 0), off(1, 0), off(0, 1), off(-1, 1)));
    private static final Shape BASE_Z = new Shape(asList(off(0, 0), off(0, 1), off(1, 1), off(-1, 0)));
    private static final Shape BASE_T = new Shape(asList(off(0, 0), off(-1, 0), off(1, 0), off(0, 1)));

    private List<Shape> shapes;

    static {
        T.shapes = Collections.unmodifiableList(asList(
            BASE_T,
            rotated(BASE_T),
            rotated(rotated(BASE_T)),
            rotated(rotated(rotated(BASE_T)))));
        L.shapes = Collections.unmodifiableList(asList(
            BASE_L,
            rotated(BASE_L),
            rotated(rotated(BASE_L)),
            rotated(rotated(rotated(BASE_L)))));
        J.shapes = Collections.unmodifiableList(asList(
            BASE_J,
            rotated(BASE_J),
            rotated(rotated(BASE_J)),
            rotated(rotated(rotated(BASE_J)))));
        I.shapes = Collections.unmodifiableList(asList(
            BASE_I,
            rotated(BASE_I)));
        O.shapes = Collections.unmodifiableList(asList(
            BASE_O));
        S.shapes = Collections.unmodifiableList(asList(
            BASE_S,
            rotated(BASE_S)));
        Z.shapes = Collections.unmodifiableList(asList(
            BASE_Z,
            rotated(BASE_Z)));

    }

    public static List<Shape> getShapesOf(char symbol) {
        return Pieces.valueOf(String.valueOf(symbol)).shapes;
    }

    static Shape rotated(Shape orig) {
        return new Shape(orig.normalizedOffsets().stream()
            .map(Offset::rotate90Clockwise)
            .collect(toList()));
    }
}
