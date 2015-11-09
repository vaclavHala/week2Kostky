/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.week2;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.progseminar.week2.Point2;
/**
 *
 * @author pato
 */
public class PieceCreator {
    private List<Point2> points = new ArrayList<>();
    
    public Piece create(String piece) {
        int x = 0;
        int y = 0;
        for (int i = 0 ; i < piece.length() ; i++ ) {
            if (piece.charAt(i) == '*') points.add(new Point2(x,y));
            x++;
            if (piece.charAt(i) == '\n') {
                y++;
                x = 0;
            }
        }      
        return new Piece(points);
    }      
}
