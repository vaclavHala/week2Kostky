/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.ai;

import com.mycompany.progseminar.tetris.core.*;
import static com.mycompany.progseminar.tetris.core.Point.p;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.groupingBy;

/**
 *
 * @author pato
 */
public class AI {

    private Tetris tetris;
    private AiParametrization parametrization;

    public AI(Tetris t, AiParametrization parametrization) {
        tetris = t;
        this.parametrization = parametrization;
    }

    public void round() {
        findPlaceToDrop();
    }

    public void findPlaceToDrop() {
        List<Shape> pieceShapes = tetris.currentPiece();
        List<Drop> possibilities = new ArrayList<>();

        for (int col = 0; col < tetris.boardWidth(); col++) {
            int rot = 0;
            for (Shape s : pieceShapes) {
                Optional<List<Point>> result = tetris.tryDrop(col, rot);

                if (!result.isPresent()) {
                    rot++;
                    continue;
                }
                //      System.out.println(col+","+result.get());
                possibilities.add(new Drop(col, rot, evaluate(result.get())));
                rot++;
            }
        }

        Collections.sort(possibilities);
        //  System.out.println(possibilities.get(0));
        tetris.drop(possibilities.get(0).getColumn(), possibilities.get(0).getRotation());
    }

    public boolean checkShape(Shape shape, Point freePoint) {
        for (Offset o : shape.normalizedOffsets()) {
            if (!tetris.isFree(o.dX() + freePoint.x(), o.dY() + freePoint.y())) {
                //        System.out.println(shape + " " + freePoint + "false");
                return false;
            }
        }
        //   System.out.println(shape + " " + freePoint + "true");
        return true;
    }

    private int evaluate(List<Point> result) {
        return heightFitness(result)
            + flatnessFitness(result)
            + rowClearFitness(result)
            + bubbleFitness(result)
            + failFitness(result);
    }

    private int heightFitness(List<Point> result) {
        int lowestPoint = tetris.boardHeight() - result.stream().map(p -> p.y()).min(Integer::compare).get();
        return parametrization.height.apply(lowestPoint);
    }

    private int flatnessFitness(List<Point> result) {
        int minMaxDifference = result.stream().map(p -> p.y()).max(Integer::compare).get()
            - result.stream().map(p -> p.y()).min(Integer::compare).get();
        return parametrization.flatness.apply(minMaxDifference);
    }

    private int bubbleFitness(List<Point> result) {
        int totalSize = 0;
        for (Point p : bottomSurfaceOf(result)) {
            int bubleColumnSize = findBubbleUnder(p);
            totalSize += bubleColumnSize;
        }
        return parametrization.bubble.apply(totalSize);
    }

    private int rowClearFitness(List<Point> result) {
        int rowsCleared = (int) result.stream()
            .map(p -> p.y())
            .filter(affectedRow -> isRowCleared(result, affectedRow))
            .count();

        return parametrization.rowClear.apply(rowsCleared);
    }

    private boolean isRowCleared(List<Point> result, int row) {
        for (int col = 0; col < tetris.boardWidth(); col++) {
            if (tetris.isFree(col, row) && !result.contains(new Point(col, row))) {
                return false;
            }
        }
        return true;
    }

    private List<Point> bottomSurfaceOf(List<Point> shape) {
        List<Point> bottom = new ArrayList<>();
        for (List<Point> colGroup : shape.stream()
            .collect(groupingBy(p -> p.x())).values()) {
            bottom.add(colGroup.stream()
                .min((p1, p2) -> p1.y() - p2.y()).get());
        }
        return bottom;
    }

    private int findBubbleUnder(Point p) {
        int bubleSize = 0;
        Point at = p;
        while (tetris.isFree(at.x(), at.y() - 1)) {
            at = p(at.x(), at.y() - 1);
            bubleSize++;
        }
        return bubleSize;
    }

    private int failFitness(List<Point> point) {
        return (int) -(point.stream().filter(p -> p.y() >= tetris.boardHeight() - 1).count() * 1000);
    }
}
