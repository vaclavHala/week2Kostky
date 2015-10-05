/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.week2;

import static com.mycompany.progseminar.week2.Piece.ORIGIN;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author xhala3
 */
public class Algo {

    public static void main(String[] args) {
        doIt(7).stream()
            .forEach(p -> System.out.println(p.render()));
        
    }
    
    public static List<Piece> doIt(int n) {
        List<Piece> previous = asList(ORIGIN);
        List<Piece> current = previous;
        for(int i=1; i<n; i++){
            current = iterate(previous);
            previous = current;
        }
        return current;
    }

     static List<Piece> iterate(List<Piece> previous) {
        List<Piece> pieces = new ArrayList<>();
        for (Piece piece : previous) {
            pieces.addAll(appendedToOne(piece));
        }
        return pieces.stream().distinct().collect(toList());
    }

     static List<Piece> appendedToOne(Piece piece) {
        List<Piece> newPieces = new ArrayList<>();
        for (Point startingPoint : piece.points()) {
            for (Side side : Side.values()) {
                Point newPoint= side.plus(startingPoint);
                if(piece.points().contains(newPoint)){
                    continue;
                }
                List<Point> newPoints = new ArrayList<>();
                newPoints.addAll(piece.points());
                newPoints.add(newPoint);
                newPieces.add(new Piece(newPoints));
            }
        }
        return newPieces.stream().distinct().collect(toList());
    }

}
