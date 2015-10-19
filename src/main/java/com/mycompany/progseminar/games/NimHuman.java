/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.games;

import com.sun.glass.ui.SystemClipboard;
import java.util.Scanner;

/**
 *
 * @author xhala3
 */
public class NimHuman implements NimPlayer {

    private Scanner scan = new Scanner(System.in);

    @Override
    public int play(Nim nim) {
        System.out.println("pick match count: ");
        while (true) {
            try {
                int num = Integer.parseInt(scan.nextLine());
                if(nim.moves().contains(num)){
                    System.out.println(name()+" moves "+num);
                    return num;
                }
            } catch (NumberFormatException e) {
                //ignored
            }
        }
    }

    @Override
    public String name() {
        return "Human";
    }

}
