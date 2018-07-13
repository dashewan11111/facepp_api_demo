package com.megvii.ui.fragment;

import android.os.Bundle;

import com.megvii.ui.R;
import com.megvii.ui.adapter.ApiListAdapter;
import com.megvii.ui.bean.ApiListItem;
import com.megvii.ui.presenter.ApiListPresenter;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XLazyFragment;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * @author by licheng on 2018/7/6.
 */

public class ApiListFragment extends XLazyFragment<ApiListPresenter> {

    @BindView(R.id.apiList)
    XRecyclerView recyclerView;

    ApiListAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        recyclerView.verticalLayoutManager(context);
        adapter = new ApiListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        //getP().loadData();
    }

    public void onDataLoaded(final List<ApiListItem> data) {
//        getActivity().runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                adapter.refresh(data);
//            }
//        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_api_list;
    }

    @Override
    public ApiListPresenter newP() {
        return new ApiListPresenter();
    }
}
