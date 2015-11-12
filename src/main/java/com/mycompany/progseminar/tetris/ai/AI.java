/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.ai;

import com.mycompany.progseminar.tetris.core.*;
import static com.mycompany.progseminar.tetris.core.Point.p;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 *
 * @author pato
 */
public class AI {

    private Tetris tetris;
    private int maxRow = 0;
    //   private List<Point> actualFree = new ArrayList<Point>();

    public AI(Tetris t) {
        tetris = t;
    }

    //create point for each column with minimal heigh of row
    /* public void findFreePlaces() {
        actualFree.clear();
        
        for(int col=0; col<tetris.boardWidth(); col++){
            for(int row = tetris.boardHeight()-1; row>=0; row--){
                if(!tetris.isFree(col, row-1)){
                    actualFree.add(p(col,row));
                    break;
                }
            }
        }
        Collections.sort(actualFree, new PointComparator());
        System.out.println(actualFree);
    }*/
    public void round() {
        findPlaceToDrop();
    }

    public void findPlaceToDrop() {
        List<Shape> pieceShapes = tetris.currentPiece();
        List<Drop> possibilities = new ArrayList<>();

        for (int col = 0; col < tetris.boardWidth(); col++) {
            int rot = 0;
            for (Shape s : pieceShapes) {
                Optional<List<Point>> result = tetris.tryDrop(col, rot);
                
                if (!result.isPresent()) {
                    rot++;
                    continue;
                }
          //      System.out.println(col+","+result.get());
                possibilities.add(new Drop(col, rot, evaluate(result.get())));
                rot++;
            }
        }

        Collections.sort(possibilities);
      //  System.out.println(possibilities.get(0));
        tetris.drop(possibilities.get(0).getColumn(), possibilities.get(0).getRotation());
    }

    public boolean checkShape(Shape shape, Point freePoint) {
        for (Offset o : shape.normalizedOffsets()) {
            if (!tetris.isFree(o.dX() + freePoint.x(), o.dY() + freePoint.y())) {
        //        System.out.println(shape + " " + freePoint + "false");
                return false;
            }
        }
     //   System.out.println(shape + " " + freePoint + "true");
        return true;
    }

    private int evaluate(List<Point> result) {
        int heightFitness = (tetris.boardHeight() - result.stream().map(p -> p.y()).min(Integer::compare).get()) * 2;
        int flatnessFitness = -(result.stream().map(p -> p.y()).max(Integer::compare).get()
                - result.stream().map(p -> p.y()).min(Integer::compare).get());
        int bubbleFitness = bubbleFitness(result);
        int rowClearFitness = rowClearFitness(result);
        int failFitness = failFitness(result);
     //   System.out.println("h"+heightFitness+" f"+flatnessFitness+" r"+rowClearFitness+" b"+bubbleFitness);
        return heightFitness
                + flatnessFitness
                + rowClearFitness
                + bubbleFitness
                +failFitness;
    }

    private int bubbleFitness(List<Point> result) {
        int fitness = 0;
        for (Point p : bottomSurfaceOf(result)) {
            int bubbleSize = findBubbleUnder(p);
            fitness -= (2* bubbleSize*bubbleSize );
        }
        return fitness;
    }

    private int rowClearFitness(List<Point> result) {
        int cleared = 0;
        Set<Integer> rows = result.stream().map(p -> p.y()).collect(toSet());
        label:
        for(int row: rows){
            for(int col=0; col<tetris.boardWidth(); col++){
              if(tetris.isFree(col, row) && !result.contains(new Point(col, row)))  {
                  continue label;
              }
            }
            cleared++;
        }
        return cleared*tetris.boardWidth();
    }

    private List<Point> bottomSurfaceOf(List<Point> shape) {
        List<Point> bottom = new ArrayList<>();
        for (List<Point> colGroup : shape.stream()
                .collect(groupingBy(p -> p.x())).values()) {
            bottom.add(colGroup.stream()
                    .min((p1, p2) -> p1.y() - p2.y()).get());
        }
        return bottom;
    }

    private int findBubbleUnder(Point p) {
        int bubleSize = 0;
        Point at = p;
        while (tetris.isFree(at.x(), at.y() - 1)) {
            at = p(at.x(), at.y() - 1);
            bubleSize++;
        }
        return bubleSize;
    }
    
    private int failFitness(List<Point> point){
        return (int) -(point.stream().filter(p -> p.y() >= tetris.boardHeight()-1).count() * 1000);
    }
}
