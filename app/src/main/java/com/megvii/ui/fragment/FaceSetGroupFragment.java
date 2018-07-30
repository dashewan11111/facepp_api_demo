package com.megvii.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/27.
 */

public class FaceSetGroupFragment extends BaseActionFragment {

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        switchFragment(FaceSetListFragment.class.getName(), getArguments());
    }

    public void switchFragment(final String fragmentName, final Bundle bundle) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment.instantiate(context, fragmentName, bundle))
                        .commitNowAllowingStateLoss();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_faceset_group;
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
