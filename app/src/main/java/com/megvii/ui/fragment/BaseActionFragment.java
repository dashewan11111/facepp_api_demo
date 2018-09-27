package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Toast;

import com.megvii.buz.view.loading.ProgressDialogHelper;
import com.megvii.ui.presenter.BaseActionPresenter;
import com.megvii.ui.utils.ImageChooseHelper;

import cn.droidlover.xdroidmvp.mvp.XFragment;

/**
 * @author by licheng on 2018/7/10.
 */

public abstract class BaseActionFragment<P extends BaseActionPresenter> extends XFragment<P> {

    protected ProgressDialogHelper dialogHelper;

    protected ImageChooseHelper imageChooseHelper;

    @Override
    public void initData(Bundle savedInstanceState) {
        dialogHelper = new ProgressDialogHelper(getActivity());
        imageChooseHelper = new ImageChooseHelper(this);
    }

    abstract int sampleResId();

    protected void doAction(Bitmap... bitmap) {
        dialogHelper.showAtProgress();
    }

    public void onFailed(final String error) {
        dialogHelper.dismiss();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onSuccess(Object... response) {
        dialogHelper.dismiss();
    }

}