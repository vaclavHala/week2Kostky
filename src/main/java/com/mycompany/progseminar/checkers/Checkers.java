/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.checkers;

/**
 *
 * @author xhala3
 */
public class Checkers {

    private boolean[] field;

    public Checkers(int count) {
        this.field = new boolean[count];
    }

    public boolean[] field() {
        boolean[] copy = new boolean[field.length];
        System.arraycopy(field, 0, copy, 0, field.length);
        return copy;
    }

    public void fill(int index) {
        if (this.field[index]) {
            throw new IllegalStateException("Already filled at " + index);
        }
        this.field[index] = true;
    }
    
    public boolean isWinner(){
        int consecutine = 0;
        for(boolean cell: field){
            if(cell){
                consecutine++;
            }
            if(consecutine == 3){
                return true;
            }
            consecutine = 0;
        }
        return false;
    }

}
