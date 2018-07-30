package com.megvii.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.megvii.buz.utils.JSONFormat;
import com.megvii.facepp.api.bean.FaceAnalyzeResponse;
import com.megvii.facepp.api.bean.FaceSetUpdateResponse;
import com.megvii.ui.R;
import com.megvii.ui.adapter.ParametersAdapter;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.FaceSetUpdatePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * @author by licheng on 2018/7/26.
 */

public class FaceSetUpdateFragment extends BaseActionFragment<FaceSetUpdatePresenter> {

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
        adapter = new ParametersAdapter(context, ParametersConstant.FACE_SET_UPDATE, getArguments());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @OnClick(R.id.update_faceset)
    public void updateFaceSet(View view) {
        dialogHelper.showAtProgress();
        getP().doAction(adapter.getParametersList());
    }


    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final FaceSetUpdateResponse response1 = (FaceSetUpdateResponse) response[0];
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


    @Override
    public int getLayoutId() {
        return R.layout.fragment_face_set_update;
    }

    @Override
    public FaceSetUpdatePresenter newP() {
        return new FaceSetUpdatePresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
