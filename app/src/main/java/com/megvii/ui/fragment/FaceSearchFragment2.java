package com.megvii.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.megvii.ui.R;
import com.megvii.ui.presenter.FaceSearchPresenter;

import butterknife.BindView;

/**
 * @author by licheng on 2018/7/19.
 */

public class FaceSearchFragment2 extends BaseActionFragment<FaceSearchPresenter> {

    @BindView(R.id.txtTitle)
    TextView txtTitle;

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("search", true);
        switchFragment("第一步: 创建 FaceSet", FaceSetCreateFragment.class.getName(), bundle);
    }

    public void switchFragment(final String title, final String fragmentName, final Bundle bundle) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getChildFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out)
                        .replace(R.id.container, Fragment.instantiate(context, fragmentName, bundle))
                        .commitNowAllowingStateLoss();
                txtTitle.setText(title);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search2;
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
