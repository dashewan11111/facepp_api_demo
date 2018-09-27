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
import com.megvii.facepp.api.bean.FaceSetAddResponse;
import com.megvii.facepp.api.bean.SearchResponse;
import com.megvii.ui.R;
import com.megvii.ui.adapter.ParametersAdapter;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.FaceSetAddFacePresenter;
import com.megvii.ui.presenter.FaceSetFinalPresenter;
import com.megvii.ui.utils.ImageChooseHelper;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;

import static android.app.Activity.RESULT_OK;

/**
 * @author by licheng on 2018/7/25.
 */

public class FaceSetFinalFragment extends BaseActionFragment<FaceSetFinalPresenter> {

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
        adapter = new ParametersAdapter(context, ParametersConstant.FACE_SET_SEARCH, getArguments());
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.choose_file)
    public void chooseFile(View view) {
        imageChooseHelper.chooseImage();
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final SearchResponse response1 = (SearchResponse) response[0];
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
        getP().doAction(adapter.getParametersList(), BitmapUtil.toByteArray(bitmap));
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
        return R.layout.fragment_face_search_final;
    }

    @Override
    public FaceSetFinalPresenter newP() {
        return new FaceSetFinalPresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
