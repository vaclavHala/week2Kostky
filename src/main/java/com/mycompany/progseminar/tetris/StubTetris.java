/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import java.util.List;

/**
 *
 * @author xhala3
 */
public class StubTetris implements Tetris{

    private List<List<Point>> shape;
    private List<String> board;

    public StubTetris(List<List<Point>> shape, List<String> board) {
        this.shape = shape;
        this.board = board;
    }
    
    @Override
    public int boardWidth() {
        return board.get(0).length();
    }

    @Override
    public int boardHeight() {
        return board.size();
    }

    @Override
    public boolean isFree(int x, int y) {
        return board.get(y).charAt(x) == '.';
    }

    @Override
    public List<List<Point>> currentPiece() {
        return shape;
    }

    @Override
    public void drop(int x, int rotationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
