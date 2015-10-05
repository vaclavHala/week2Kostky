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
public class Point implements Comparable<Point>{
    
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point mirrorX(){
        return new Point(-this.x, this.y);
    }

    public Point mirrorY(){
        return new Point(this.x, -this.y);
    }
    
    public Point rotate90Clockwise(){
        return new Point( this.y, -1*this.x);
    }

    @Override
    public String toString() {
        return "<"+x+","+y+">";
    }
    
    @Override
    public boolean equals(Object obj) {
         if(!(obj instanceof Point)){
            return false;
        }
        Point other =(Point)obj;
        return this.x == other.x && this.y == other.y;
    }
    
    @Override
    public int compareTo(Point o) {
        if(this.y == o.y){
            return this.x - o.x;
        } else {
            return this.y - o.y;
        }
    }
    
}
