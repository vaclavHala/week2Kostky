/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

/**
 *
 * @author xhala3
 */
public interface Tetris {

    boolean isFree(int x, int y);

    Piece currentPiece();

    void drop(int x);

}
