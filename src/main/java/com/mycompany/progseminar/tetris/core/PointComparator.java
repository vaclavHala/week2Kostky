/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.core;

import java.util.Comparator;

/**
 *
 * @author pato
 */
public class PointComparator implements Comparator<Point> {

    @Override
    public int compare(Point o1, Point o2) {
        if (o1.y() == o2.y()){
            return Integer.compare(o1.x(), o2.x());
        }
        return Integer.compare(o1.y(), o2.y());
    }

     
}

