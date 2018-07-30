package com.megvii.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.megvii.buz.utils.JSONFormat;
import com.megvii.buz.utils.XUtils;
import com.megvii.facepp.api.bean.FaceSet;
import com.megvii.facepp.api.bean.FaceSetDetailResponse;
import com.megvii.ui.R;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.FaceSetDetailPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * @author by licheng on 2018/7/27.
 */

public class FaceSetDetailFragment extends BaseActionFragment<FaceSetDetailPresenter> {

    @BindView(R.id.txtResult)
    TextView txtResult;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_faceset_detail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        dialogHelper.showAtProgress();
        String faceSetToken = getArguments().getString(ParametersConstant.FACESET_TOKEN.getKey());
        Map<String, String> paras = new HashMap<>();
        paras.put(ParametersConstant.FACESET_TOKEN.getKey(), faceSetToken);
        getP().doAction(paras);
    }

    @Override
    public void onSuccess(final Object... response) {
        super.onSuccess(response);
        final FaceSetDetailResponse faceSetDetailResponse = (FaceSetDetailResponse) response[0];
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtResult.setText(new JSONFormat().formatJson(faceSetDetailResponse.toString()));
            }
        });
    }

    @Override
    public void onFailed(String error) {
        super.onFailed(error);
    }

    @Override
    public FaceSetDetailPresenter newP() {
        return new FaceSetDetailPresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
