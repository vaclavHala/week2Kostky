/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.ai;

import com.mycompany.progseminar.tetris.core.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author pato
 */
public class AI {

    private Tetris tetris;
    private int maxRow = 0;
    private List<Point> actualFree = new ArrayList<Point>();

    public AI(Tetris t) {
        tetris = t;
    }

    //create point for each column with minimal heigh of row
    public void findFreePlaces() {
        actualFree.clear();
        int minY;
        for (int j = 0; j < tetris.boardWidth(); j++) {
            minY = 21;
            for (int i = tetris.boardHeight() - 1; i >= -1; i--) {
                if (tetris.isFree(j, i)) {
                    minY = i;
                } else {
                    actualFree.add(new Point(j, minY));
                    break;
                }
            }
        }
        Collections.sort(actualFree, new PointComparator());
        System.out.println(actualFree);
    }

    public void round() {
        findFreePlaces();
        findPlaceToDrop();
    }

    public void findPlaceToDrop() {
        List<Shape> pieceShapes = tetris.currentPiece();
        Point bestP = null;
        int id = 0;
        for (Point p : actualFree) {
            id = -1;
            bestP = p;
            for (Shape s : pieceShapes) {
                id++;
                if (checkShape(s, p)) {
                    System.out.println("drop " + bestP);
                    tetris.drop(bestP.x(), id);
                    return;
                }
            }
        }
    }

    public boolean checkShape(Shape shape, Point freePoint) {
        for (Offset o : shape.normalizedOffsets()) {
            if (!tetris.isFree(o.dX() + freePoint.x(), o.dY() + freePoint.y())) {
                System.out.println(shape + " " + freePoint + "false");
                return false;
            }
        }
        System.out.println(shape + " " + freePoint + "true");
        return true;
    }
}
