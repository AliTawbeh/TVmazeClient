package com.brightcove.tvmazeclient.recyclerviewAdapter;

/**
 * Created by Ali on 16-Apr-18.
 */

public interface StringComparator<T> {
    boolean compare(String s, T t);
}
