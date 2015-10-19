/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.games;

import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.List;

/**
 *
 * @author xhala3
 */
public class NimAi implements NimPlayer {

    private static final int WIN = -1;
    private int[] plannedMoves;

    public void configureFor(Nim nim) {
        plannedMoves = new int[nim.left() + 1];
        plannedMoves[0] = WIN;
        for (int i = 1; i < plannedMoves.length; i++) {
            plannedMoves[i] = determineFor(i, nim.moves());
        }
    }

    private int determineFor(int remaining, List<Integer> possibleMoves) {
        for (int move : possibleMoves) {
            if (getAt(remaining - move) == WIN) {
                return move;
            }
        }
        return WIN;
    }

    private int getAt(int pos) {
        return pos >= 0 ? plannedMoves[pos] : 1;
    }

    @Override
    public int play(Nim nim) {
        int move = this.plannedMoves[nim.left()];
        if (move >= 0) {
            System.out.println(name() + " moves " + move);
            return move;
        } else {
            move = nim.moves().get(0);
            System.out.println(name() + " moves " + move);
            return move;
        }
    }

    @Override
    public String name() {
        return "AI";
    }
}
