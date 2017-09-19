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
        ImageViewHolder imageViewHolder;

        if (position == 0) {
            view = inflater.inflate(R.layout.shop_detail_header, parent, false);
            imageViewHolder = new ImageViewHolder(view);
            imageViewHolder.shopImage.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getRootView().getWidth()));
            initButton(imageViewHolder.bookmarkBtn);
            view.setTag(imageViewHolder);

            Picasso.with(view.getContext()).load(getItem(position)).into(imageViewHolder.shopImage);
            Picasso.with(view.getContext()).setIndicatorsEnabled(true);
        } else {
            if (view == null) {
                view = inflater.inflate(R.layout.shop_detail_item, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);

            } else {
                if (!(view.getTag() instanceof ViewHolder)) {
                    view = inflater.inflate(R.layout.shop_detail_item, parent, false);
                    holder = new ViewHolder(view);
                    view.setTag(holder);
                } else {
                    holder = (ViewHolder) view.getTag();
                }
            }
            holder.shopInfoTitle.setText(keys[position]);
            holder.shopInfoContent.setText(getItem(position));
        }
        return view;
    }


    /**
     * ViewHolder
     */
    private class ViewHolder {
        private final TextView shopInfoTitle;
        private final TextView shopInfoContent;

        /**
         * コンストラクタ
         */
        private ViewHolder(View view) {
            shopInfoTitle = view.findViewById(R.id.detail_title);
            shopInfoContent = view.findViewById(R.id.detail_content);
        }
    }

    /**
     * 先頭行用のViewHolder
     */
    private class ImageViewHolder {
        private final ImageView shopImage;
        private final Button bookmarkBtn;

        /**
         * コンストラクタ
         */
        private ImageViewHolder(View view) {
            shopImage = view.findViewById(R.id.detail_photo);
            bookmarkBtn = view.findViewById(R.id.bookmark_btn);
        }
    }

    private void initButton(Button button) {
        if (ShopDetailActivity.flag) {
            button.setText("ブックマークを取り消す");
        } else {
            button.setText("このお店をブックマーク");
        }
    }
}
