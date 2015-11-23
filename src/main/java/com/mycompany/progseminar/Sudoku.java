/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

/**
 *
 * @author xhala3
 */
public class Sudoku {

    private Cell[][] board;

    private Sudoku(Cell[][] board) {
        this.board = board;
    }

    public static void main(String[] args) {
        run(10);
    }

    private static void run(int n) {
        Sudoku s = read(readNth(n, Sudoku.class.getResourceAsStream("/in")));
        String result = s.solve();
        String expected = readNth(n, Sudoku.class.getResourceAsStream("/result"));
        System.out.println("N " + n);
        System.out.println("res " + result);
        System.out.println("exp " + expected);
        System.out.println(result.equals(expected));
    }

    private static String readNth(int n, InputStream in) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
            int at = 0;
            while (at < n) {
                r.readLine();
                at++;
            }
            return r.readLine();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Sudoku read(String in) {
        Cell[][] board = new Cell[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char c = in.charAt(row * 9 + col);
                if (c == '.') {
                    board[row][col] = new Cell();
                } else {
                    board[row][col] = new Cell(Integer.valueOf("" + c));
                }
            }
        }
        return new Sudoku(board);
    }

    public String solve() {
        forEachCell((row, col) -> initPossibilities(row, col));

        boolean[] change = new boolean[]{true};
        while (change[0]) {
            change[0] = false;
            forEachCell((row, col) -> {
                change[0] |= onlyChoiceInCol(row,col);
                change[0] |= onlyChoiceInRow(row,col);
            });
            forEachCell((row, col) -> initPossibilities(row, col));
            System.out.println(diagnose());
        }
        return result();
    }

    private void initPossibilities(int row, int col) {
        if (board[row][col].isKnown()) {
            return;
        }
        List<Integer> poss = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            poss.add(i);
        }
        poss.removeAll(existingInRow(row));
        poss.removeAll(existingInCol(col));
        poss.removeAll(existingInSquare(row, col));
        if (poss.size() == 1) {
            System.out.println(row + "," + col + "=" + poss.get(0));
        }
        board[row][col].setPossibilities(poss);
    }

    private List<Integer> existingInRow(int row) {
        List<Integer> e = new ArrayList<>();
        for (int col = 0; col < 9; col++) {
            if (board[row][col].isKnown()) {
                e.add(board[row][col].knownValue());
            }
        }
        return e;
    }

    private List<Integer> existingInCol(int col) {
        List<Integer> e = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            if (board[row][col].isKnown()) {
                e.add(board[row][col].knownValue());
            }
        }
        return e;
    }

    private List<Integer> existingInSquare(int row, int col) {
        List<Integer> e = new ArrayList<>();
        int rowStart = row - (row % 3);
        int colStart = col - (col % 3);
        // System.out.println(row+", "+col+" -> "+rowStart+", "+colStart);
        for (int r = rowStart; r < rowStart + 3; r++) {
            for (int c = colStart; c < colStart + 3; c++) {
                if (board[r][c].isKnown()) {
                    e.add(board[r][c].knownValue());
                }
            }
        }
        return e;
    }

    private boolean onlyChoiceInRow(int row, int col) {
        for (int pos : board[row][col].possibilities()) {
            int[] count = new int[]{0};
            forEachCellIn(0, 9, col, col+1, (r, c) -> {
                if (board[r][c].possibilities().contains(pos)) {
                    count[0]++;
                }
            });
            if (count[0] == 1 && !board[row][col].isKnown()) {
                System.out.println("R " + row + "," + col + "=" + pos);
                board[row][col].setResult(pos);
                return true;
            }
        }

        return false;
    }
    
    private boolean onlyChoiceInCol(int row, int col) {
        
        for (int pos : board[row][col].possibilities()) {
            int[] count = new int[]{0};
            forEachCellIn(row, row+1, 0, 9, (r, c) -> {
                if (board[r][c].possibilities().contains(pos)) {
                    count[0]++;
                }
            });
           
            if (count[0] == 1 && !board[row][col].isKnown() ) {
                System.out.println("C " + row + "," + col + "=" + pos);
                board[row][col].setResult(pos);
                return true;
            }
        }

        return false;
    }

  /*  private boolean onlyChoiceInCol(int col) {
        for (int val = 1; val <= 9; val++) {
            final Integer[] foundAtRow = new Integer[]{null};
            forEachCellIn(0, 9, col, col + 1, f(foundAtRow, val));

            if (foundAtRow[0] != null
                    && foundAtRow[0] >= 0
                    && !board[foundAtRow[0]][col].isKnown()) {
                System.out.println("R " + foundAtRow[0] + "," + col + "=" + val);
                board[foundAtRow[0]][col].setResult(val);
                return true;
            }
        }
        return false;
    }

    private BiConsumer<Integer, Integer> f(final Integer[] found, final int v) {
        return (r, c) -> {
            if (board[r][c].possibilities().contains(v)) {
                if (board[r][c].isKnown()) {
                    found[0] = -1;
                    return;
                }
                if (found[0] == null) {
                    found[0] = c;
                } else {
                    found[0] = -1;
                }
            }
        };
    }*/

    private void forEachCell(BiConsumer<Integer, Integer> rowCol) {
        forEachCellIn(0, 9, 0, 9, rowCol);
    }

    private void forEachCellIn(
            int rowFrom,
            int rowTo,
            int colFrom,
            int colTo,
            BiConsumer<Integer, Integer> rowCol) {
        for (int row = rowFrom; row < rowTo; row++) {
            for (int col = colFrom; col < colTo; col++) {
                rowCol.accept(row, col);
            }
        }
    }

    public String result() {
        StringBuilder b = new StringBuilder(9 * 9);
        forEachCell((row, col) -> b.append(board[row][col].knownValue()));
        return b.toString();
    }

    public String diagnose() {
        StringBuilder b = new StringBuilder();
        StringBuilder p = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell cell = board[row][col];
                b.append(cell);
                if (cell.isKnown()) {
                    p.append(padTo("" + cell.knownValue(), 20));
                } else {
                    p.append(padTo(cell.possibilities.toString(), 20));
                }
            }
            b.append("\n");
            p.append("\n");
        }
        return b.append(p).toString();
    }

    private String padTo(String s, int len) {
        return String.format("%-" + len + "s", s);
    }

    private static class Cell {

        private List<Integer> possibilities = new ArrayList<>();

        public Cell() {
        }

        public Cell(int knownValue) {
            possibilities.add(knownValue);
        }

        public List<Integer> possibilities() {
            return Collections.unmodifiableList(possibilities);
        }

        public boolean isKnown() {
            return possibilities.size() == 1;
        }

        public int knownValue() {
            if (!isKnown()) {
                throw new RuntimeException();
            }
            return possibilities.get(0);
        }

        public void setResult(int result) {
            if (isKnown()) {
                throw new RuntimeException();
            }
            this.possibilities.clear();
            this.possibilities.add(result);
        }

        public void setPossibilities(List<Integer> possibilities) {
            this.possibilities = new ArrayList<>(possibilities);
        }

        public void removePossibility(Integer val) {

            this.possibilities.remove(val);
        }

        @Override
        public String toString() {
            if (isKnown()) {
                return "" + knownValue();
            } else {
                return ".";
            }
        }

    }

}
