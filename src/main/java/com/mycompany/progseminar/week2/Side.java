/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.week2;

import java.util.function.Function;

/**
 *
 * @author xhala3
 */
public enum Side {
    
    UP(p -> new Point(p.getX(), p.getY()+1)),
    DOWN(p -> new Point(p.getX(), p.getY()-1)),
    LEFT(p -> new Point(p.getX()-1, p.getY())),
    RIGHT(p -> new Point(p.getX()+1, p.getY()));
    
    private Function<Point2, Point2> fun;

    private Side(Function<Point2, Point2> fun) {
        this.fun = fun;
    }
    
    public Point2 plus(Point2 point){
        return fun.apply(point);
    }
}
