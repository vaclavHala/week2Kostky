/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.core;

import java.util.ArrayList;
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

    private Iterator<List<List<Point>>> incomming;
    private List<List<Point>> current;
    boolean[][] board;

    public SimpleTetris(int width, int height, List<List<List<Point>>> incomming) {
        this.board = new boolean[height][width];
        this.incomming = new ArrayList<>(incomming).iterator();
        this.current = this.incomming.next();
        
    }

    @Override
    public int boardWidth() {
        return this.board[0].length;
    }

    @Override
    public int boardHeight() {
        return this.board.length;
    }

    @Override
    public boolean isFree(int x, int y) {
        if (x < 0 || y < 0 || x >= boardWidth()) {
            return false;
        }
        if(y >= boardHeight()){
            return true;
        }
        return !board[y][x];
    }

    @Override
    public List<List<Point>> currentPiece() {
        return Collections.unmodifiableList(this.current);
    }

    @Override
    public void drop(int x, int rotationId) {
        List<Point> at = this.current.get(rotationId).stream()
                .map((Point b) -> new Point(b.x()+x, b.y() + boardHeight()))
                .collect(toList());

        while (canShiftDown(at)) {
            at = shiftedDown(at);
        }
        settle(at);
        current = incomming.next();
        render(at);
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
            this.board[p.y()][p.x()] = true;
        }
    }

    private void clearLine(int line) {

    }

    @Override
    public boolean isOver() {
        for(int i=0; i<boardWidth(); i++){
            if(!isFree(i, boardHeight()-1)){
                return true;
            }
        }
        return false;
    }
    
    private void render(List<Point> droppedShape) {
        char[][] buffer = new char[boardHeight()][boardWidth()];
        for (int col = 0; col <boardHeight() ; col++) {
            for (int row = 0; row < boardWidth(); row++) {
                buffer[col][row] = this.board[col][row]
                        ? SYMBOL_TAKEN
                        : SYMBOL_FREE;
            }
        }
        for (Point p : droppedShape) {
            buffer[p.y()][p.x()] = SYMBOL_ACTIVE;
        }
        for (int col = boardHeight()-1; col >=0 ; col--) {
            for (int row = 0; row < boardWidth(); row++) {
                System.out.print(buffer[col][row]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
