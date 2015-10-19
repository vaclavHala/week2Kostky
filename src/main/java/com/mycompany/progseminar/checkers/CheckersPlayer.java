/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.checkers;

import com.mycompany.progseminar.games.Nim;

/**
 *
 * @author xhala3
 */
public interface CheckersPlayer {
    
    int play(Checkers checkers);
    
    String name();
}
