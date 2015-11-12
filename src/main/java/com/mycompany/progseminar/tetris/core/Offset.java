package com.mycompany.progseminar.tetris.core;

/**
 * sorted by Y first, if same then X, lower values of Y come first
 */
public class Offset implements Comparable<Offset> {

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

    public Offset(int dX, int dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public Offset rotate90Clockwise() {
        return new Offset(this.dY, -1 * this.dX);
    }

    @Override
    public String toString() {
        return "<" + dX + "," + dY + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Offset)) {
            return false;
        }
        Offset other = (Offset) obj;
        return this.dX == other.dX && this.dY == other.dY;
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
