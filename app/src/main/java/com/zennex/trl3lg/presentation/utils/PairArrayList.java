package com.zennex.trl3lg.presentation.utils;

import android.support.annotation.NonNull;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 14.09.17.
 */

public class PairArrayList<T, E> extends ArrayList<Pair<T, E>> {


    public PairArrayList() {
        super();
    }

    public PairArrayList(List<Pair<T, E>> pairs) {
        addAll(pairs);
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public Pair<T, E>[] toArray() {
        Pair[] pairs = new Pair[size()];
        for (int i = 0, n = size(); i < n; i++) pairs[i] = get(i);
        return pairs;
    }
}
