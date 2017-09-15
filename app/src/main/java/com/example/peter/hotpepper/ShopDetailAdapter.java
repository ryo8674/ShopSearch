package com.example.peter.hotpepper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Map;

/**
 * 独自アダプター
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class ShopDetailAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private Context context;
    private ShopDto shopDto;
    private Map<String, String> shopMap;
    private String[] keys;

    ShopDetailAdapter(@NonNull Context context, @NonNull ShopDto shopDto) {
        this.context = context;
        this.shopDto = shopDto;
        shopMap = shopDto.getShopMap();
        keys = shopMap.keySet().toArray(new String[shopMap.size()]);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return shopMap.size();
    }

    @Override
    public String getItem(int position) {
        return shopMap.get(keys[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;


        if (convertView == null) {
            holder = new ViewHolder();
            if (position == 0) {
                view = inflater.inflate(R.layout.shop_detail_photo, null);
                holder.shopImage = view.findViewById(R.id.detail_photo);
                holder.shopImage.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getRootView().getWidth()));
                holder.shopImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                holder.bookmarkBtn = view.findViewById(R.id.bookmark_btn);
            } else {
                view = inflater.inflate(R.layout.shop_detail_item, null);
                holder.shopInfoTitle = view.findViewById(R.id.detail_title);
                holder.shopInfoContent = view.findViewById(R.id.detail_content);
            }
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (position == 0) {
            Picasso.with(view.getContext()).load(getItem(position)).into(holder.shopImage);
            Picasso.with(view.getContext()).setIndicatorsEnabled(true);
        } else {
            // TODO: textのセット
            holder.shopInfoTitle.setText(keys[position]);
            holder.shopInfoContent.setText(getItem(position));
        }

        return view;
    }

    /**
     * ViewHolder
     */
    private class ViewHolder {
        ImageView shopImage;
        Button bookmarkBtn;
        TextView shopInfoTitle;
        TextView shopInfoContent;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
