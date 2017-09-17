package com.example.peter.hotpepper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 独自アダプター
 *
 * @author :ryo.yamada
 * @since :1.0 :2017/08/25
 */
class ShopAdapter extends ArrayAdapter<ShopDto> {

    private final LayoutInflater inflater;

    /**
     * コンストラクタ
     *
     * @param context context
     * @param list    list
     */
    ShopAdapter(@NonNull Context context, @NonNull List<ShopDto> list) {
        super(context, R.layout.shop_item, list);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.shop_item, null);
            holder = new ViewHolder();
            holder.shopImage = view.findViewById(R.id.shop_image);
            holder.shopName = view.findViewById(R.id.shop_name);
            holder.shopGenre = view.findViewById(R.id.shop_genre);
            holder.shopAccess = view.findViewById(R.id.shop_access);
            holder.shopInfo = view.findViewById(R.id.shop_info);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ShopDto shop = getItem(position);
        if (shop != null) {
            holder.shopName.setText(shop.getName());
            Picasso.with(getContext()).load(shop.getPhoto().getMobile().getPictS()).into(holder.shopImage);
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
    }
}
