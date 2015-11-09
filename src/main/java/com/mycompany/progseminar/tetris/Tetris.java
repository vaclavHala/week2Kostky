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
public interface Tetris {

    int boardWidth();

    int boardHeight();

    boolean isFree(int x, int y);

    List<Point> currentPiece();

    void rotate();
    
    void drop(int x);

}
