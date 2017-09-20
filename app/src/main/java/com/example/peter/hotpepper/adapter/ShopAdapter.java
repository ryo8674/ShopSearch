package com.example.peter.hotpepper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.dto.ShopDto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 店舗一覧のリストを表示するAdapter
 */
public class ShopAdapter extends ArrayAdapter<ShopDto> {

    private final LayoutInflater inflater;

    public ShopAdapter(@NonNull Context context, @NonNull List<ShopDto> results) {
        super(context, R.layout.shop_item, results);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.shop_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ShopDto shop = getItem(position);
        if (shop != null) {
            Picasso.with(getContext()).load(shop.getPhoto().getMobile().getPhotoS()).into(holder.shopImage);
            holder.shopName.setText(shop.getName());
            holder.shopGenre.setText(shop.getGenre().getName());
            holder.shopAccess.setText(shop.getMobileAccess());
            holder.shopInfo.setText(shop.getShopInfo());
        }

        return view;
    }

    /**
     * ViewHolder
     */
    private class ViewHolder {
        ImageView shopImage;
        TextView shopName;
        TextView shopGenre;
        TextView shopAccess;
        TextView shopInfo;

        private ViewHolder(View view) {
            shopImage = view.findViewById(R.id.shop_image);
            shopName = view.findViewById(R.id.shop_name);
            shopGenre = view.findViewById(R.id.shop_genre);
            shopAccess = view.findViewById(R.id.shop_access);
            shopInfo = view.findViewById(R.id.shop_info);
        }
    }
}
