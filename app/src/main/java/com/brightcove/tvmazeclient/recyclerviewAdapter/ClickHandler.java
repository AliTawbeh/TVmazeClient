package com.brightcove.tvmazeclient.recyclerviewAdapter;

/**
 * Created by Ali on 14-Apr-18.
 */
/**
 * Interface to handle when an item {@Link T} is clicked.
 * @param <T> model of the item that must be sent in the click event
 */
public interface ClickHandler<T> {
    void onClick(T objectID);
}
