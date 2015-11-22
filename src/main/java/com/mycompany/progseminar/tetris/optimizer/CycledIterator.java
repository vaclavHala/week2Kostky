package com.mycompany.progseminar.tetris.optimizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CycledIterator<T> implements Iterator<T> {

    private List<T> source;
    private Iterator<T> base;

    public CycledIterator(Collection<T> source) {
        this.source = new ArrayList<>(source);
        this.base = this.source.iterator();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        if (!base.hasNext()) {
            base = source.iterator();
        }
        return base.next();
    }
}
