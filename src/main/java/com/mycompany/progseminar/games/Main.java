/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.games;

import static java.util.Arrays.asList;

/**
 *
 * @author xhala3
 */
public class Main {
    public static void main(String[] args) {
        Nim nim = new Nim(23, asList(30));
        NimAi ai = new NimAi();
        ai.configureFor(nim);
        NimHuman human = new NimHuman();
        NimGame game = new NimGame(nim, ai, human);
        game.play();
    }
}
