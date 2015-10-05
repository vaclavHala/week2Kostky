/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.task2;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Task2 {

    public static void main(String[] args) {
        String text = normalize("kdo honi dva zajice, nechyti zadneho");
        String pass = normalize("petrklic");
        System.out.println(printColumns(
                new ArrayList(splitColumns(text, pass).values())));
    }

    public static String normalize(String text) {
        return text.toUpperCase().replaceAll("[ .,]", "");
    }

    public static Map<Character, String> splitColumns(String text, String pass) {
        int colCount = pass.length();
        int colSize = columnSize(text.length(), pass.length());
        char[][] columnsBuff = new char[colCount][colSize];
        for (int i = 0; i < colCount * colSize; i++) {
            int row = i / colCount;
            int col = i % colCount;
            char symbol = i < text.length() ? text.charAt(i) : 'X';
            columnsBuff[col][row] = symbol;
        }
        Map<Character, String> columns = new TreeMap<>();
        for (int i = 0; i < columnsBuff.length; i++) {
            StringBuilder col = new StringBuilder();
            for (char c : columnsBuff[i]) {
                col.append(c);
            }
            columns.put(pass.charAt(i), col.toString());
        }
        return columns;
    }

    public static String printColumns(List<String> columns) {
        StringBuilder msg = new StringBuilder();
        for (int at = 0; at < columns.get(0).length(); at++) {
            for (String col : columns) {
                msg.append(col.charAt(at));
            }
        }
        return msg.toString();
    }

    public static int columnSize(int textLen, int passLen) {
        return (int) Math.ceil((double) textLen / (double) passLen);
    }

}
