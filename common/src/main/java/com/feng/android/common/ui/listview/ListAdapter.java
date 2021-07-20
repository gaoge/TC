package com.feng.android.common.ui.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feng.android.common.R;

import java.util.List;

/**
 * @author gaoge
 * @version V1.0
 * @date 2021-07-20 15:08
 * @tips
 * 具体的适配器，把集合适配成View
 */
public class ListAdapter implements AdapterTarget {
    private List<String> mItems;
    private Context context;

    public ListAdapter(List<String> mItems, Context context) {
        this.mItems = mItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public View getView(int position, ViewGroup parent) {

        TextView itemView = (TextView) LayoutInflater.from(context).inflate(R.layout.item_listview, null);
        itemView.setText(mItems.get(position));
        return itemView;
    }
}
