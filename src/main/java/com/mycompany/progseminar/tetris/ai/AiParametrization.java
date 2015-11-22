package com.mycompany.progseminar.tetris.ai;

import java.util.function.Function;

public class AiParametrization {

    public final Function<Integer, Integer> height;
    public final Function<Integer, Integer> flatness;
    public final Function<Integer, Integer> rowClear;
    public final Function<Integer, Integer> bubble;

    public AiParametrization(Function<Integer, Integer> height,
                             Function<Integer, Integer> flatness,
                             Function<Integer, Integer> rowClear,
                             Function<Integer, Integer> bubble) {
        this.height = height;
        this.flatness = flatness;
        this.rowClear = rowClear;
        this.bubble = bubble;
    }

    @Override
    public String toString() {
        return "AiParametrization{" + "height=" + height + ", flatness=" + flatness + ", rowClear=" + rowClear + ", bubble=" + bubble + '}';
    }
}
