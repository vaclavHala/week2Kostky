/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.core;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author xhala3
 */
public class SimpleTetris implements Tetris {

    private static final char SYMBOL_TAKEN = 'X';
    private static final char SYMBOL_FREE = '.';
    private static final char SYMBOL_ACTIVE = '@';

    private Iterator<List<Shape>> incomming;
    private List<Shape> current;
    private List<Point> latestDropped;
    boolean[][] cellTaken;

    public SimpleTetris(int width, int height, Iterator<List<Shape>> incomming) {
        this.cellTaken = new boolean[height][width];
        this.incomming = incomming;
        this.current = this.incomming.next();
        this.latestDropped = Collections.emptyList();
    }

    @Override
    public int boardWidth() {
        return this.cellTaken[0].length;
    }

    @Override
    public int boardHeight() {
        return this.cellTaken.length;
    }

    @Override
    public boolean isFree(int x, int y) {
        if (x < 0 || y < 0 || x >= boardWidth()) {
            return false;
        }
        if (y >= boardHeight()) {
            return true;
        }
        return !cellTaken[y][x];
    }

    @Override
    public List<Shape> currentPiece() {
        return Collections.unmodifiableList(this.current);
    }

    @Override
    public void drop(int dropColumn, int rotationId) {
        List<Point> materializedShape = this.current.get(rotationId)
            .normalizedOffsets().stream()
            .map((Offset o) -> new Point(o.dX() + dropColumn, o.dY() + boardHeight()))
            .collect(toList());

        while (canShiftDown(materializedShape)) {
            materializedShape = shiftedDown(materializedShape);
        }
        settle(materializedShape);
        clearFullRows();
        latestDropped = materializedShape;
        current = incomming.next();
    }

    private List<Point> shiftedDown(List<Point> shape) {
        return shape.stream()
            .map((Point b) -> new Point(b.x(), b.y() - 1))
            .collect(toList());
    }

    private boolean canShiftDown(List<Point> shape) {
        return shape.stream()
            .allMatch(p -> isFree(p.x(), p.y() - 1));
    }

    private void settle(List<Point> shape) {
        for (Point p : shape) {
            this.cellTaken[p.y()][p.x()] = true;
        }
    }

    private void clearFullRows() {
        int row = 0;
        while (row < boardHeight()) {
            if (isFullRow(row)) {
                clearRow(row);
            } else {
                row++;
            }
        }
    }

    private boolean isFullRow(int row) {
        for (int col = 0; col < boardWidth(); col++) {
            if (isFree(col, row)) {
                return false;
            }
        }
        return true;
    }

    private void clearRow(int row) {
        int atRow = row;
        while (atRow < boardHeight()) {
            for (int col = 0; col < boardWidth(); col++) {
                cellTaken[atRow][col] = !isFree(col, atRow + 1);
            }
            atRow++;
        }
    }

    @Override
    public boolean isOver() {
        for (int i = 0; i < boardWidth(); i++) {
            if (!isFree(i, boardHeight() - 1)) {
                return true;
            }
        }
        return false;
    }

    public void render() {
        char[][] buffer = new char[boardHeight()][boardWidth()];
        for (int col = 0; col < boardHeight(); col++) {
            for (int row = 0; row < boardWidth(); row++) {
                buffer[col][row] = this.cellTaken[col][row]
                                   ? SYMBOL_TAKEN
                                   : SYMBOL_FREE;
            }
        }
        for (Point p : this.latestDropped) {
            buffer[p.y()][p.x()] = SYMBOL_ACTIVE;
        }
        for (int col = boardHeight() - 1; col >= 0; col--) {
            for (int row = 0; row < boardWidth(); row++) {
                System.out.print(buffer[col][row]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
