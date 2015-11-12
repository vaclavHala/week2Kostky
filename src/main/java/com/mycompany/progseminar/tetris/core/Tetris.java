/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.core;

import java.util.List;

public interface Tetris {
    
    int boardWidth();

    int boardHeight();

    boolean isOver();
    
    boolean isFree(int x, int y);

    List<List<Point>> currentPiece();

    void drop(int x, int rotationId);
    
}
