package com.example.peter.hotpepper.adapter;

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

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.activity.ShopDetailActivity;
import com.example.peter.hotpepper.dto.ShopDto;
import com.squareup.picasso.Picasso;

import java.util.Map;

import static com.example.peter.hotpepper.util.Constants.DELETE_BUTTON;
import static com.example.peter.hotpepper.util.Constants.REGISTER_BUTTON;

/**
 * 店舗詳細画面のリスト用のAdapter
 */
public class ShopDetailAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final Map<String, String> shopMap;
    private final String[] keys;

    /**
     * コンストラクタ
     */
    public ShopDetailAdapter(@NonNull Context context, @NonNull ShopDto results) {
        super();
        shopMap = results.getShopDetailInfo();
        keys = shopMap.keySet().toArray(new String[getCount()]);
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
        ImageViewHolder imageHolder;

        if (position == 0) {
            view = inflater.inflate(R.layout.detail_head, parent, false);
            imageHolder = new ImageViewHolder(view);
            imageHolder.detailImage.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, parent.getRootView().getWidth()));
            initButton(imageHolder.bookmarkBtn);
            view.setTag(imageHolder);
            Picasso.with(view.getContext()).load(getItem(position)).into(imageHolder.detailImage);
        } else {
            if (view == null) {
                view = inflater.inflate(R.layout.detail_content, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);

            } else {
                if (!(view.getTag() instanceof ViewHolder)) {
                    view = inflater.inflate(R.layout.detail_content, parent, false);
                    holder = new ViewHolder(view);
                    view.setTag(holder);
                } else {
                    holder = (ViewHolder) view.getTag();
                }
            }
            holder.detailTitle.setText(keys[position]);
            holder.detailInfo.setText(getItem(position));
        }
        return view;
    }

    /**
     * 先頭行以外のViewHolder
     */
    private class ViewHolder {
        final TextView detailTitle;
        final TextView detailInfo;

        /**
         * コンストラクタ
         */
        private ViewHolder(View view) {
            detailTitle = view.findViewById(R.id.detail_title);
            detailInfo = view.findViewById(R.id.detail_info);
        }
    }

    /**
     * 先頭行用のViewHolder
     */
    private class ImageViewHolder {
        final ImageView detailImage;
        final Button bookmarkBtn;

        /**
         * コンストラクタ
         */
        private ImageViewHolder(View view) {
            detailImage = view.findViewById(R.id.detail_image);
            bookmarkBtn = view.findViewById(R.id.detail_button);
        }
    }

    /**
     * Buttonのテキストの設定
     */
    private void initButton(Button button) {
        if (ShopDetailActivity.flag) {
            button.setText(DELETE_BUTTON);
        } else {
            button.setText(REGISTER_BUTTON);
        }
    }
}
