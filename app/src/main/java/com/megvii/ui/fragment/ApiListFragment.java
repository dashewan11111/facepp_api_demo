package com.megvii.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;

import com.megvii.ui.R;
import com.megvii.ui.adapter.ApiListAdapter;
import com.megvii.ui.bean.ApiListItem;
import com.megvii.ui.datasource.ApiDataSource2;
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
        String apiGroup = getArguments().getString("api_group");
        adapter = new ApiListAdapter(getActivity(), ApiDataSource2.ApiGroups.getApisByGroupName(apiGroup));
        recyclerView.setAdapter(adapter);
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
