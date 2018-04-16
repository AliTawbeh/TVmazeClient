package com.brightcove.tvmazeclient.recyclerviewAdapter;

/**
 * Created by Ali on 16-Apr-18.
 */

/**
 * This interface is used to somehow check if a string is contained in an Object
 * @param <T>
 */
public interface StringGenericComparator<T> {
    boolean contained(String s, T t);
}
