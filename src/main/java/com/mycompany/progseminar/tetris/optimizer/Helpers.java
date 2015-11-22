package com.mycompany.progseminar.tetris.optimizer;

import java.util.ArrayList;
import java.util.List;

public class Helpers {

    public static <T> List<T> combine(List<T>... collections) {
        List<T> all = new ArrayList<>();
        for (List<T> some : collections) {
            all.addAll(some);
        }
        return all;
    }

    public static List<Double> range(double from, double to, double step) {
        List<Double> ds = new ArrayList<>();
        double at = from;
        while (at < to) {
            ds.add(at);
            at += step;
        }
        return ds;
    }
}
