/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author pato
 */
public class AI {
    
    private Tetris tetris;
    private int maxRow = 0;
    private List<Point> actualFree = new ArrayList<Point>();
    
    public AI(Tetris t){
        tetris = t;
    }
    
    //create point for each column with minimal heigh of row 
    public void findFreePlaces() {
        actualFree.clear();
        int minY;
        for(int j = 0 ; j < tetris.boardWidth(); j++){
            minY = 21;
            for (int i = tetris.boardHeight()-1; i >= -1 ; i--){
                if (tetris.isFree(j, i)) minY = i;
                else {
                    actualFree.add(new Point(j,minY));
                    break;
                }
            }
        }
        Collections.sort(actualFree, new PointComparator());
System.out.println(actualFree);        
    }
    
    public void round(){
        findFreePlaces();
        findPlaceToDrop();
    }
    
    public void findPlaceToDrop() {
        List<List<Point>> pieces = tetris.currentPiece();
        Point bestP = null;
        int id = 0;
        for(Point p : actualFree){
            id = -1;
            bestP = p;
            for(List<Point> lp : pieces){
                id++;
                if(checkPiece(lp, p)){
                    System.out.println("drop "+bestP);
                    tetris.drop(bestP.getX(), id);
                    return;
                }
            }
        }
    }
    
    public boolean checkPiece(List<Point> lp, Point freePoint) {
        for (int i = 0; i < lp.size(); i++) {
            if (!tetris.isFree(lp.get(i).getX() + freePoint.getX(), lp.get(i).getY() + freePoint.getY())) {
                System.out.println(lp+" "+freePoint+"false");
               return false;
            }
        }
        System.out.println(lp+" "+freePoint+"true");
        return true;
    }
}
