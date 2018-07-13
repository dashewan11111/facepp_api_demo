package com.megvii.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.megvii.buz.view.loading.ProgressDialogHelper;
import com.megvii.ui.R;
import com.megvii.ui.presenter.BaseApiPresenter;
import com.megvii.ui.view.AttributeListResultView;
import com.megvii.ui.view.BaseResultView;
import com.megvii.ui.view.FaceView;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XFragment;
import io.reactivex.functions.Consumer;

/**
 * @author by licheng on 2018/7/10.
 */

public abstract class BaseResultFragment extends XFragment<BaseApiPresenter> {

    @BindView(R.id.faceView)
    FaceView faceView;

    @BindView(R.id.resultContainer)
    LinearLayout resultContainer;

    @BindView(R.id.face_container)
    LinearLayout faceContainer;

    Bitmap bitmap;

    BaseResultView baseResultView;

    protected ProgressDialogHelper dialogHelper;

    @Override
    public void initData(Bundle savedInstanceState) {
        baseResultView = resultView(context);
        baseResultView.setVisibility(View.INVISIBLE);
        resultContainer.addView(baseResultView);
        dialogHelper = new ProgressDialogHelper(getActivity());
        faceView.setImageResource(getSampleResId(), toolbarHeight(), new Consumer<Bitmap>() {
            @Override
            public void accept(Bitmap bitmap) throws Exception {
                BaseResultFragment.this.bitmap = bitmap;
                doAction();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_result;
    }

    @Override
    public BaseApiPresenter newP() {
        return null;
    }

    public void onActionSuccess(Object... response) {
        dialogHelper.dismiss();
        baseResultView.setVisibility(View.VISIBLE);
        faceContainer.setVisibility(View.VISIBLE);
    }

    public void onActionFailed(final String error) {
        dialogHelper.dismiss();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
            }
        });
    }

    abstract int getSampleResId();

    abstract void doAction();

    BaseResultView resultView(Context context) {
        return new AttributeListResultView(context);
    }

    int toolbarHeight() {
        return 0;
    }

    public void onFileChoose(Bitmap bmp) {
        faceContainer.setVisibility(View.INVISIBLE);
        baseResultView.setVisibility(View.INVISIBLE);
        faceView.setImageResource(bmp, new Consumer<Bitmap>() {
            @Override
            public void accept(Bitmap bitmap) throws Exception {
                BaseResultFragment.this.bitmap = bitmap;
                doAction();
            }
        });
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}