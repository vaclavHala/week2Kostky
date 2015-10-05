/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progseminar.task6;

import java.awt.Point;
import static java.util.Arrays.asList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import static java.util.stream.Collectors.toList;

public class Task6 {

    public static int[][] maze1() {
        return new int[][]{
            {2, 2, 3, 2, 1},
            {4, 3, 2, 2, 4},
            {4, 3, 3, 3, 4},
            {3, 2, 1, 2, 4},
            {3, 3, 3, 1, 0}
        };
    }

    public static int[][] maze2() {
        return new int[][]{
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        };
    }
    
    public static void main(String[] args) {
        int[][] maze = maze2();
        int size = maze.length;
        Point start = new Point(0, 0);
        Point end = new Point(size - 1, size - 1);
        Set<Point> unvisited = initUnvisited(size);
        Queue<Step> paths = new LinkedList<>();
        paths.add(new Step(null, start));
        while (!paths.isEmpty()) {
            Step cur = paths.poll();
            if (cur.at.equals(end)) {
                System.out.println(cur);
                return;
            }
            paths.addAll(possibleMoves(cur.at,
                    maze[cur.at.x][cur.at.y],
                    size,
                    unvisited).stream().map(p -> new Step(cur, p))
                    .collect(toList()));
        }
    }

    public static Set<Point> initUnvisited(int size) {
        Set<Point> unvisited = new HashSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                unvisited.add(new Point(i, j));
            }
        }
        return unvisited;
    }

    public static List<Point> possibleMoves(Point from, int len, int size, Set<Point> unvisited) {
        return asList(
                new Point(from.x + len, from.y),
                new Point(from.x - len, from.y),
                new Point(from.x, from.y + len),
                new Point(from.x, from.y - len)
        ).stream()
                .filter(p -> p.x >= 0 && p.y >= 0 && p.x < size && p.y < size)
                .filter(p -> unvisited.remove(p))
                .collect(toList());
    }

    private static class Step {

        private Step previous;
        private Point at;

        public Step(Step previous, Point at) {
            this.previous = previous;
            this.at = at;
        }

        @Override
        public String toString() {
            String msg = "";
            if (previous != null) {
                msg = previous.toString();
            }
            msg = msg + "," + at.x + "" + at.y;
            return msg;
        }

    }
}
