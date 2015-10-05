/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.week2;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author xhala3
 */
public class Piece {
 
    public static Piece ORIGIN = new Piece(asList(new Point(0,0)));
    
    private List<Point> points;
    
    Piece(List<Point> points){
        //validate
        this.points =  Collections.unmodifiableList(new ArrayList<>(points));
    }
    
    public String render(){
    return null;
    }
    
    public Piece rotate(){
        return null;
        
    }
    
    public  Piece mirror(){
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Piece)){
            return false;
        }
        Piece other =(Piece)obj;
        return other.points.equals(this.points);
    }

    public List<Point> points(){
        
        return this.points;
    }
    
    public Piece appendAt(Point point, Side side){
        List<Point> newPoints = new ArrayList<>(points);
        newPoints.add(side.plus(point));
        return new Piece(newPoints);
    }
    
}
