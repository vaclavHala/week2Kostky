/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.task1;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = Integer.parseInt(scan.nextLine());
        System.out.println(paint(size));
    }

    private static String paint(int size) {
        int whole = size * 4 - 3;
        String[][] grid = new String[whole][whole];
        String fill = "*";
        for (int i = 0; i < whole; i++) {
            fillSquare(grid, i, whole - i, fill);
            fill = fill.equals("*") ? " " : "*";
        }
        StringBuilder img = new StringBuilder();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                img.append(grid[x][y]);
            }
            img.append("\n");
        }
        return img.toString();
    }

    private static void fillSquare(String[][] grid, int from, int to, String fill) {
        int delta = to - from;
        for (int y = from; y < to; y++) {
            for (int x = from; x < to; x++) {
                grid[x][y] = fill;
            }
        }
    }

}
