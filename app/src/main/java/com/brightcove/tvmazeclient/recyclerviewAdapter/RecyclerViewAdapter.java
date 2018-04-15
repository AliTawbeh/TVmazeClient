package com.brightcove.tvmazeclient.recyclerviewAdapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Ali on 14-Apr-18.
 */

public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> implements View.OnClickListener{
    //TAG ID used to attach the item <T> to the viewHolder
    private static final int ITEM_TAG = -929;
    //Observable list of Items in the recyclerView
    private ObservableList<T> mItems;
    private ItemBinder mItemBinder;
    //The callback that is called by ObservableList when the list has changed.
    private CustomOnListChangedCallback mCustomOnListChangedCallback;
    private LayoutInflater mInflater;
    private ClickHandler<T> mClickHandler;

    RecyclerViewAdapter(@NonNull ItemBinder itemBinder, @NonNull List<T> items){
        mItemBinder=itemBinder;
        mCustomOnListChangedCallback = new CustomOnListChangedCallback();
        setItems(items);
    }
    @Override
    public RecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mInflater == null)
            mInflater = LayoutInflater.from(parent.getContext());

        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(mInflater,viewType,parent,false);
        return new RecyclerViewViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewViewHolder holder, int position) {
        //if the list of items is null or empty do nothing
        if(mItems ==null || mItems.size()==0)
            return;
        //Get the item at the position
        T item = mItems.get(position);
        if(item==null)
            return;
        //Pass the variable item to the view holder, where the viewHolder's layout populates the views
        if(mItemBinder !=null)
            holder.mViewDataBinding.setVariable(mItemBinder.getBindingVariable(),item);
        //Attach the item to the viewHolder so it can be used for example when we want to delete it in swipe event
        holder.mViewDataBinding.getRoot().setTag(ITEM_TAG,item);
        holder.mViewDataBinding.getRoot().setOnClickListener(this);
        holder.mViewDataBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mItems !=null ? mItems.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        //This can be used to return different layouts depending on the item type in the position,
        //in this app we only have on item type
        return mItemBinder!=null ? mItemBinder.getLayoutRes():super.getItemViewType(position);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if(mItems!=null)
            mItems.removeOnListChangedCallback(mCustomOnListChangedCallback);
    }

    public void setItems(List<T> items) {
        //It is better to have items instance of Observable list, so we do not need to allocate a new observable list
        //if same reference do nothing
        if(items== mItems)
            return;
        //if mItems is not null, then clear then remove the observable list listener and notify that the list is cleared
        if(mItems != null){
            mItems.removeOnListChangedCallback(mCustomOnListChangedCallback);
            notifyItemRangeRemoved(0, mItems.size());
        }

        if(items instanceof ObservableList){
            //if items is observable, then assign it to mItems, notify that insertion happened
            // and add observable list callback
            mItems = (ObservableList<T>) items;
            notifyItemRangeInserted(0, mItems.size());
            mItems.addOnListChangedCallback(mCustomOnListChangedCallback);
        }else if(items !=null){
            mItems = new ObservableArrayList<>();
            mItems.addOnListChangedCallback(mCustomOnListChangedCallback);
            mItems.addAll(items);
        } else {
            mItems = null;
        }
    }


    @Override
    public void onClick(View v) {
        if(mClickHandler!=null)
            mClickHandler.onClick((T) v.getTag(ITEM_TAG));
    }

    public void setClickHandler(ClickHandler<T> clickHandler) { mClickHandler = clickHandler; }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //set a reference for the recyclerView in the ListChangedCallback
        mCustomOnListChangedCallback.setRecyclerView(recyclerView);
    }

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder{
        final ViewDataBinding mViewDataBinding;
        public RecyclerViewViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
        }
    }

    private class CustomOnListChangedCallback<T> extends ObservableList.OnListChangedCallback{
        private RecyclerView mRecyclerView;
        void setRecyclerView(RecyclerView recyclerView){
            mRecyclerView = recyclerView;
        }


        @Override
        public void onChanged(ObservableList observableList) {
            RecyclerViewAdapter.this.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableList observableList, int positionStart, int itemCount) {
            RecyclerViewAdapter.this.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeInserted(ObservableList observableList, int positionStart, int itemCount) {
            RecyclerViewAdapter.this.notifyItemRangeInserted(positionStart, itemCount);
            if(mRecyclerView!=null)
                //scroll to top after insertion
                mRecyclerView.smoothScrollToPosition(positionStart);
        }

        @Override
        public void onItemRangeMoved(ObservableList observableList, int fromPosition, int toPosition, int itemCount) {
            RecyclerViewAdapter.this.notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRangeRemoved(ObservableList observableList, int positionStart, int itemCount) {
            RecyclerViewAdapter.this.notifyItemRangeRemoved(positionStart,itemCount);
        }
    }
}
