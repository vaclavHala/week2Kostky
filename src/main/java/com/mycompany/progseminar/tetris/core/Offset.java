package com.mycompany.progseminar.tetris.core;

public class Offset

    Comparable<Point> {

    private int dX;
    private int dY;

    public static Offset off(int dX, int dY) {
        return new Offset(dX, dY);
    }

    public int dX() {
        return dX;
    }

    public int dY() {
        return dY;
    }

    public Point(int x, int y) {
        this.dX = x;
        this.dY = y;
    }

    public Point rotate90Clockwise() {
        return new Point(this.dY, -1 * this.dX);
    }

    @Override
    public String toString() {
        return "<" + dX + "," + dY + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return this.dX == other.x && this.dY == other.y;
    }

    @Override
    public int compareTo(Offset o) {
        if (this.dY == o.dY) {
            return this.dX - o.dX;
        } else {
            return this.dY - o.dY;
        }
    }

}
