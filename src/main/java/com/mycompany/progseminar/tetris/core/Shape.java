package com.mycompany.progseminar.tetris.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shape {

    private List<Offset> normalizedOffsets;

    public Shape(List<Offset> denormalizedOffsets) {
        this.normalizedOffsets = Collections.unmodifiableList(normalize(denormalizedOffsets));
    }

    static List<Offset> normalize(List<Offset> denormalized) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Offset o : denormalized) {
            minX = Math.min(minX, o.dX());
            minY = Math.min(minY, o.dY());
        }
        List<Offset> normalized = new ArrayList<>(denormalized.size());
        for (Offset o : denormalized) {
            normalized.add(new Offset(o.dX() - minX, o.dY() - minY));
        }
        Collections.sort(normalized);
        return normalized;
    }

    /**
     * <0,0> is lower left
     */
    public List<Offset> normalizedOffsets() {
        return this.normalizedOffsets;
    }

}
