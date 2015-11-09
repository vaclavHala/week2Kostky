/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author xhala3
 */
public class SimpleTetris implements Tetris {

    private static final char SYMBOL_TAKEN;
    private static final char SYMBOL_FREE;
    private static final char SYMBOL_ACTIVE;

    private List<List<List<Point>>> incomming;
    private List<List<Point>> current;
    boolean[][] board;

    public SimpleTetris(int width, int height, List<List<List<Point>>> incomming) {
        this.board = new boolean[height][width];
        this.incomming = new ArrayList<>(incomming);
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
        return board[y][x];
    }

    @Override
    public List<List<Point>> currentPiece() {
        return Collections.unmodifiableList(this.current);
    }

    @Override
    public void drop(int x, int rotationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void render() {
        char[][] buffer = new char[boardHeight()][boardWidth()];
        for (int col = 0; col < boardHeight(); col++) {
            for (int row = 0; row < boardWidth(); row++) {
                buffer[col][row] = this.board[col][row]
                        ? SYMBOL_TAKEN
                        : SYMBOL_FREE;
            }
        }
    }

}
