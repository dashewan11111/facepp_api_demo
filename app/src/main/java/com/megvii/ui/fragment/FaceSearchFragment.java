package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.megvii.ui.R;
import com.megvii.ui.presenter.FaceSearchPresenter;
import com.megvii.ui.view.ScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

/**
 * @author by licheng on 2018/7/19.
 */

public class FaceSearchFragment extends BaseActionFragment<FaceSearchPresenter> {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ScrollViewPager viewPager;

    private XFragmentAdapter adapter;

    private String[] titles = new String[]{"创建FaceSet", "添加FaceToken", "搜索"};
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {

        fragmentList.add(new FaceSetCreateFragment());
        fragmentList.add(new FaceSetAddFaceFragment());
        fragmentList.add(new FaceSetCreateFragment());

        if (null == adapter) {
            adapter = new XFragmentAdapter(getChildFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setScrollAble(true);
        tabLayout.setupWithViewPager(viewPager);
        LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            View tabView = tabStrip.getChildAt(i);
            if (tabView != null) {
                tabView.setClickable(false);
            }
        }
    }

    @Override
    protected void doAction(Bitmap... bitmap) {

    }

    public void switchTab(final int position) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(position, true);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public FaceSearchPresenter newP() {
        return new FaceSearchPresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }

}
