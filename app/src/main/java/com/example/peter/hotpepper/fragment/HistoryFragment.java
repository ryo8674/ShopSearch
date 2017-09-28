package com.example.peter.hotpepper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.peter.hotpepper.R;
import com.example.peter.hotpepper.activity.ShopDetailActivity;
import com.example.peter.hotpepper.adapter.ShopAdapter;
import com.example.peter.hotpepper.dto.ShopDto;
import com.example.peter.hotpepper.usecase.ShopListUseCase;

import java.util.List;

import static com.example.peter.hotpepper.util.Constants.SHOP_CODE;

/**
 * 履歴一覧を表示する Fragment
 */
public class HistoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView shopList;
    private List<ShopDto> shopDtoList;

    /**
     * コンストラクタ
     */
    public HistoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shopList = view.findViewById(R.id.shop_list);
        shopList.setOnItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ShopListUseCase useCase = new ShopListUseCase(getContext());
        shopDtoList = useCase.loadPreferences();

        ShopAdapter adapter = new ShopAdapter(getContext(), shopDtoList);
        shopList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(view.getContext(), ShopDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        intent.putExtra(SHOP_CODE, shopDtoList.get(position).getId());

        startActivity(intent);
    }

}
