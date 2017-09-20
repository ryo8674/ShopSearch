package com.example.peter.hotpepper.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.peter.hotpepper.dto.LargeAreaDto;

import java.util.List;

/**
 * エリア一覧のリストを表示するAdapter
 */
public class AreaAdapter extends ArrayAdapter<LargeAreaDto> {

    private final LayoutInflater inflater;

    public AreaAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<LargeAreaDto> results) {
        super(context, resource, results);

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

        LargeAreaDto largeArea = getItem(position);
        if (largeArea != null) {
            holder.areaName.setText(largeArea.getName());
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
