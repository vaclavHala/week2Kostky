/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.tetris.ai;

/**
 *
 * @author xhala3
 */
public class Drop implements Comparable<Drop>{
    
    private final int column;
    private final int rotation;
    private final int fitness;

    public Drop(int column, int rotation, int fitness) {
        this.column = column;
        this.rotation = rotation;
        this.fitness = fitness;
    }

    public int getColumn() {
        return column;
    }

    public int getRotation() {
        return rotation;
    }

    public int getFitness() {
        return fitness;
    }

    @Override
    public int compareTo(Drop o) {
        return  o.fitness - this.fitness;
    }

    @Override
    public String toString() {
        return "Drop{" + "column=" + column + ", rotation=" + rotation + ", fitness=" + fitness + '}';
    }
    
    
    
}
