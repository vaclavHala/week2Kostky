package com.mycompany.progseminar.tetris.optimizer;

import com.mycompany.progseminar.tetris.ai.AiParametrization;
import java.util.*;
import static java.util.stream.Collectors.summingInt;

public class Run implements Comparable<Run> {

    public final AiParametrization parametrization;
    public final List<Integer> results;

    public Run(AiParametrization parametrization, List<Integer> results) {
        this.parametrization = parametrization;
        this.results = Collections.unmodifiableList(new ArrayList<>(results));
    }

    @Override
    public int compareTo(Run that) {
        return this.results.stream().collect(summingInt(r -> r))
            - that.results.stream().collect(summingInt(r -> r));
    }

    @Override
    public String toString() {
        return results + " using " + parametrization;
    }

}
