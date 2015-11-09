/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.week2;

/**
 *
 * @author xhala3
 */
public class Point2 implements Comparable<Point2>{
    
    private int x;
    private int y;
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point2 mirrorX(){
        return new Point2(-this.x, this.y);
    }

    public Point2 mirrorY(){
        return new Point2(this.x, -this.y);
    }
    
    public Point2 rotate90Clockwise(){
        return new Point2( this.y, -1*this.x);
    }

    @Override
    public String toString() {
        return "<"+x+","+y+">";
    }
    
    @Override
    public boolean equals(Object obj) {
         if(!(obj instanceof Point2)){
            return false;
        }
        Point2 other =(Point2)obj;
        return this.x == other.x && this.y == other.y;
    }
    
    @Override
    public int compareTo(Point2 o) {
        if(this.y == o.y){
            return this.x - o.x;
        } else {
            return this.y - o.y;
        }
    }
    
}
