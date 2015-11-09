/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import java.util.Comparator;

/**
 *
 * @author pato
 */
public class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
        if (o1.getY() == o2.getY()){
            return Integer.compare(o1.getX(), o2.getX());
        }
        return Integer.compare(o1.getY(), o2.getY());
    }

     
}

