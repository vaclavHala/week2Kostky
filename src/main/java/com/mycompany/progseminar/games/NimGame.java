/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.games;

import java.util.List;

/**
 *
 * @author xhala3
 */
public class NimGame {
    
    private Nim nim;
    private NimPlayer first;
    private NimPlayer second;
    private boolean  firstsTurn;
    
    public NimGame(Nim nim, NimPlayer first, NimPlayer second){
        this.nim = nim;
        this.first = first;
        this.second = second;
        
    }
    
    public void play(){
        System.out.println("New game: "+renderNim());
        while(nim.movePossible()){
            System.out.println("turn: "+currentPlayer().name());
            nim.take(currentPlayer().play(nim));
            nextTurn();
            System.out.println("Nim: "+renderNim());
        }
        nextTurn();
        System.out.println("player: "+currentPlayer().name()+" WINS!");
    }
    
    private String renderNim(){
        return "remaining: "+this.nim.left()+"; moves: "+this.nim.moves();
        
    }
    
    private NimPlayer currentPlayer(){
        return this.firstsTurn ? this.first : this.second;
    }
    
    private void nextTurn(){
        this.firstsTurn = !this.firstsTurn;
    }
}
