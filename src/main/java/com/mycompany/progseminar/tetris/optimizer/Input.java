package com.mycompany.progseminar.tetris.optimizer;

import com.mycompany.progseminar.tetris.core.Pieces;
import com.mycompany.progseminar.tetris.core.Shape;
import java.util.Iterator;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Input {

    public final String name;
    public final String iteration;

    public Input(String name, String iteration) {
        this.name = name;
        this.iteration = iteration;
    }

    public Iterator<List<Shape>> infinite() {
        return new CycledIterator<>(parseInput(iteration));
    }

    private static List<List<Shape>> parseInput(String in) {
        return in
            .chars().mapToObj(c -> Pieces.getShapesOf((char) c))
            .collect(toList());
    }
}
