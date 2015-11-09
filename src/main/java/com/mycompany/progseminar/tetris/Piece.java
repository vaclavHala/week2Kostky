/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import com.mycompany.progseminar.week2.*;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author xhala3
 */
public class Piece {

    private Point center;
    private List<Point> points;
    private int hash;

    Piece(List<Point> points, Point center) {
        this.points = Collections.unmodifiableList(points);
        this.center= center;
    }
    /*
     public String render(char symbol, char[][] buffer) {
     int maxX = 0;
     int maxY = 0;
     for (Point p : points) {
     if (p.getX() > maxX) {
     maxX = p.getX();
     }
     if (p.getY() > maxY) {
     maxY = p.getY();
     }
     }

     StringBuilder piece = new StringBuilder();
     int indexOfPoint = 0;
     for (int i = 0; i <= maxY; i++) {
     for (int j = 0; j <= maxX; j++) {
     Point p = points.get(indexOfPoint);
     if (p.getX() == j && p.getY() == i) {
     piece.append("#");
     if (indexOfPoint < points.size() - 1) {
     indexOfPoint++;
     }
     } else {
     piece.append(".");
     }
     }
     piece.append('\n');
     }

     return piece.toString();
     }
     */

    public Point centerPosition(){
        return this.center;
    }
    
    public void rotate() {
        for (int i = 0; i < this.points.size(); i++) {
            this.points.set(i, this.points.get(i).rotate90Clockwise());
        }
    }

    public List<Point> points() {
        return this.points;
    }

}
