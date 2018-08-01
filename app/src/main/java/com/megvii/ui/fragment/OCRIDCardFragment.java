package com.megvii.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.megvii.ui.R;
import com.megvii.ui.view.ScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.XFragmentAdapter;

/**
 * @author by licheng on 2018/7/19.
 */

public class OCRIDCardFragment extends BaseActionFragment {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.view_pager)
    ScrollViewPager viewPager;

    private XFragmentAdapter adapter;

    private String[] titles = new String[]{"          正  面          ", "          反  面          "};
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {

        Bundle bundleFront = new Bundle();
        bundleFront.putBoolean("front", true);

        Bundle bundleBack = new Bundle();
        bundleBack.putBoolean("front", false);

        fragmentList.add(Fragment.instantiate(context, OCRIDCardFragment1.class.getName(), bundleFront));
        fragmentList.add(Fragment.instantiate(context, OCRIDCardFragment1.class.getName(), bundleBack));

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
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public Object newP() {
        return null;
    }

    @Override
    int sampleResId() {
        return 0;
    }

}
