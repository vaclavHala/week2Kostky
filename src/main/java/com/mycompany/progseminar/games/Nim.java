/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author xhala3
 */
public class Nim {

    private int left;
    private List<Integer> moves;

    public Nim(int count, List<Integer> moves) {
        this.left = count;
        this.moves = new ArrayList<>(moves);
        Collections.sort(this.moves);
        this.moves = Collections.unmodifiableList(this.moves);
    }
    
    public void take(int count){
        if(moves.contains(count) && count <= left){
            left -= count;
            return;
        }
        throw new IllegalStateException(count+" is not valid take count!");
    }
    
    public boolean movePossible(){
        for(int move: moves){
            if (move <= left){
                return true;
            }
        }
        return false;
    }
    
    public int left(){
    return this.left;
    }

    public List<Integer> moves(){
        return this.moves;
    }
    
}
