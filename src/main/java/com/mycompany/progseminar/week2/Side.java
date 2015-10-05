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
    
    private Function<Point, Point> fun;

    private Side(Function<Point, Point> fun) {
        this.fun = fun;
    }
    
    public Point plus(Point point){
        return fun.apply(point);
    }
}
