package com.megvii.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.buz.utils.JSONFormat;
import com.megvii.facepp.api.bean.FaceAnalyzeResponse;
import com.megvii.ui.R;
import com.megvii.ui.adapter.ParametersAdapter;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.FaceAnalyzePresenter;
import com.megvii.ui.utils.ImageChooseHelper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;

import static android.app.Activity.RESULT_OK;

/**
 * @author by licheng on 2018/7/26.
 */

public class FaceAnalyzeFragment extends BaseActionFragment<FaceAnalyzePresenter> {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @BindView(R.id.txtResult)
    TextView txtResult;

    ParametersAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        recyclerView.verticalLayoutManager(context);
        recyclerView.verticalDivider(R.color.translucent_white_50, R.dimen.common_1_px);
        adapter = new ParametersAdapter(context, ParametersConstant.FACE_ANALYZE, getArguments());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @OnClick(R.id.btn_face_analyze)
    public void addFace(View view) {
        dialogHelper.showAtProgress();
        getP().doAction(adapter.getParametersList());
    }

    @OnClick(R.id.get_face_token)
    public void getFaceToken(View view) {
        imageChooseHelper.chooseImage();
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final FaceAnalyzeResponse response1 = (FaceAnalyzeResponse) response[0];
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtResult.setText(new JSONFormat().formatJson(response1.toString()));
            }
        });
    }

    @Override
    public void onFailed(String error) {
        super.onFailed(error);
    }

    public void refreshUI(Bitmap bitmap) {
        dialogHelper.showAtProgress();
        getP().detect(BitmapUtil.toByteArray(bitmap));
    }

    public void onDetectSuccess(final String faceTokens) {
        super.onSuccess(faceTokens);
        final Bundle bundle = null == getArguments() ? new Bundle() : getArguments();
        bundle.putString(ParametersConstant.FACE_TOKENS.getKey(), faceTokens);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new ParametersAdapter(context, ParametersConstant.FACE_ANALYZE, bundle);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void onDetectFailed(String error) {
        super.onFailed(error);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageChooseHelper.doOnActivityResult(requestCode, resultCode, data, new ImageChooseHelper.OnFileChooseListener() {
            @Override
            public void onFileChoose(Bitmap bitmap) {
                refreshUI(bitmap);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_face_analyze;
    }

    @Override
    public FaceAnalyzePresenter newP() {
        return new FaceAnalyzePresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
