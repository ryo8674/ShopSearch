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
class ShopDetailAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final Map<String, String> shopMap;
    private final String[] keys;

    ShopDetailAdapter(@NonNull Context context, @NonNull ShopDto shopDto) {
        super();
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
                view = inflater.inflate(R.layout.shop_detail_header, null);
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


}


// public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//         View view = convertView;
//         ViewHolder holder = null;
//         ImageViewHolder imageHolder = null;

//         if (view == null) {
//             holder = null;
//             imageHolder = null;
//             if (position == 0) {
//                 view = inflater.inflate(R.layout.detail_head, parent, false);
//                 imageHolder = new ImageViewHolder(view);
//                 view.setTag(imageHolder);
//             } else {
//                 view = inflater.inflate(R.layout.detail_content, parent, false);
//                 holder = new ViewHolder(view);
//                 view.setTag(holder);
//             }
//         } else {
//             if (!(view.getTag() instanceof ViewHolder)) {
//                 view = inflater.inflate(R.layout.detail_content, parent, false);
//                 holder = new ViewHolder(view);
//                 view.setTag(holder);
//             } else if (position == 0) {
//                 view = inflater.inflate(R.layout.detail_head, parent, false);
//                 imageHolder = new ImageViewHolder(view);
//                 view.setTag(imageHolder);
//             } else {
//                 holder = (ViewHolder) view.getTag();
//             }
//         }

//         if (position == 0) {
//             Picasso.with(view.getContext()).load(getItem(position)).into(imageHolder.detailImage);
//         } else {
//             holder.detailTitle.setText(keys[position]);
//             holder.detailInfo.setText(getItem(position));
//         }

//         return view;
//     }
