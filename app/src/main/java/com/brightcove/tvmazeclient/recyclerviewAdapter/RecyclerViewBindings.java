package com.brightcove.tvmazeclient.recyclerviewAdapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.brightcove.tvmazeclient.recyclerviewAdapter.ClickHandler;
import com.brightcove.tvmazeclient.recyclerviewAdapter.ItemBinder;
import com.brightcove.tvmazeclient.recyclerviewAdapter.RecyclerViewAdapter;

import java.util.List;

/**
 * Created by Ali on 14-Apr-18.
 */

public class RecyclerViewBindings {
    private static final int DATA_KEY = 101;
    private static final int CLICK_HANDLER_KEY = 102;
    private static final int COMPARATOR_KEY = 103;

    @BindingAdapter({"itemBinder"})
    public static <T> void setItemBinder(RecyclerView recyclerView, ItemBinder itemBinder){
        RecyclerViewAdapter<T> adapter = new RecyclerViewAdapter<>(itemBinder,(List<T>) recyclerView.getTag(DATA_KEY), (StringComparator<T>) recyclerView.getTag(COMPARATOR_KEY));

        ClickHandler<T> clickHandler = (ClickHandler<T>) recyclerView.getTag(CLICK_HANDLER_KEY);

        if(clickHandler!=null)
            adapter.setClickHandler(clickHandler);

        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("clickHandler")
    public static <T> void setClickHandler(RecyclerView recyclerView, ClickHandler<T> clickHandler){
        RecyclerViewAdapter<T> adapter = (RecyclerViewAdapter<T>) recyclerView.getAdapter();
        if(adapter==null)
            recyclerView.setTag(CLICK_HANDLER_KEY,clickHandler);
        else
            adapter.setClickHandler(clickHandler);
    }

    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items){
        RecyclerViewAdapter<T> adapter = (RecyclerViewAdapter<T>) recyclerView.getAdapter();
        if(adapter==null)
            recyclerView.setTag(DATA_KEY,items);
        else
            adapter.setItems(items);
    }

    @BindingAdapter("comparator")
    public static <T> void setComparator(RecyclerView recyclerView, StringComparator<T> comparator){
        RecyclerViewAdapter<T> adapter = (RecyclerViewAdapter<T>) recyclerView.getAdapter();
        if(adapter==null)
            recyclerView.setTag(COMPARATOR_KEY,comparator);
        else
            adapter.setComparator(comparator);
    }

}
