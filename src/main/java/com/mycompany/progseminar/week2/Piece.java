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
import static java.util.stream.Collectors.toList;

/**
 *
 * @author xhala3
 */
public class Piece {

    public static Piece ORIGIN = new Piece(asList(new Point(0, 0)));

    private List<Point> points;

    Piece(List<Point> points) {
        this.points = Collections.unmodifiableList(normalize(new ArrayList<>(points)));
        if(this.points.size() != points.size()){
         throw new ExceptionInInitializerError("one or more duplicate points");
        }
    }

    static List<Point> normalize(List<Point> original) {
        int minX = original.stream()
            .min((a, b) -> Integer.compare(a.getX(), b.getX()))
            .get().getX();
        int minY = original.stream()
            .min((a, b) -> Integer.compare(a.getY(), b.getY()))
            .get().getY();
        return original.stream()
            .map(p -> new Point(p.getX() - minX, p.getY() - minY))
            .sorted()
            .distinct()
            .collect(toList());
    }

    public String render() {
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

    public Piece rotate() {
        return new Piece(this.points.stream()
            .map(Point::rotate90Clockwise)
            .collect(toList()));
    }

    public Piece mirror() {
        return new Piece(this.points.stream()
            .map(Point::mirrorX)
            .collect(toList()));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Piece)) {
            return false;
        }
        Piece other = (Piece) obj;
        return asList(
            other,
            other.rotate(),
            other.rotate().rotate(),
            other.rotate().rotate().rotate(),
            other.mirror(),
            other.mirror().rotate(),
            other.mirror().rotate().rotate(),
            other.mirror().rotate().rotate().rotate()
        ).stream()
            .anyMatch(this::equalsFixedRotation);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return this.points.toString();
    }

    private boolean equalsFixedRotation(Piece piece) {
        return piece.points.equals(this.points);
    }

    public List<Point> points() {
        return this.points;
    }

    public Piece appendAt(Point point, Side side) {
        List<Point> newPoints = new ArrayList<>(points);
        newPoints.add(side.plus(point));
        return new Piece(newPoints);
    }

}
