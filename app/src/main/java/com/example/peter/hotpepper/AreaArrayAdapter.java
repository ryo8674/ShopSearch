package com.example.peter.hotpepper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 独自アダプター
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class AreaArrayAdapter extends ArrayAdapter<LargeArea> {

    private final LayoutInflater inflater;

    /**
     * コンストラクタ
     *  @param context  context
     * @param list     list
     */
    AreaArrayAdapter(@NonNull Context context, @NonNull List<LargeArea> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(android.R.layout.simple_list_item_1, null);
            holder = new ViewHolder();
            holder.areaName = view.findViewById(android.R.id.text1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        LargeArea largeArea = getItem(position);
        if (largeArea != null) {
            ServiceArea serviceArea = largeArea.getServiceArea();
            holder.areaName.setText(serviceArea.getServiceAreaName());
        }

        return view;
    }

    /**
     * ViewHolder
     */
    private class ViewHolder {
        TextView areaName;
    }
}
