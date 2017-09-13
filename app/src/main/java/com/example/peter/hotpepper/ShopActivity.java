package com.example.peter.hotpepper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_item);

        ImageView imageView = (ImageView) findViewById(R.id.shop_image);
        Picasso.with(this).load("https://imgfp.hotp.jp/IMGH/71/70/P027897170/P027897170_100.jpg").into(imageView);

    }
}
