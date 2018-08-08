package com.megvii.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.megvii.buz.utils.JSONFormat;
import com.megvii.facepp.api.bean.FaceSetCreatResponse;
import com.megvii.ui.R;
import com.megvii.ui.adapter.ParametersAdapter;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.FaceSetCreatePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;


/**
 * @author by licheng on 2018/7/19.
 */

public class FaceSetCreateFragment extends BaseActionFragment<FaceSetCreatePresenter> {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @BindView(R.id.txtResult)
    TextView txtResult;

    ParametersAdapter adapter;

    @Override
    int sampleResId() {
        return 0;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        recyclerView.verticalLayoutManager(context);
        recyclerView.verticalDivider(R.color.translucent_white_50, R.dimen.common_1_px);
        recyclerView.addFooterView(txtResult);
        adapter = new ParametersAdapter(context, ParametersConstant.FACE_SET_CREATE, null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @OnClick(R.id.create_face_set)
    public void createFaceSet(View view) {
        dialogHelper.showAtProgress();
        getP().doAction(adapter.getParametersList());
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final FaceSetCreatResponse response1 = (FaceSetCreatResponse) response[0];
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtResult.setText(new JSONFormat().formatJson(response1.toString()));
            }
        });
        if (getArguments().getBoolean("search")) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("search", true);
            bundle.putString(ParametersConstant.FACESET_TOKEN.getKey(), response1.getFaceset_token());
            ((FaceSearchFragment2) getParentFragment()).switchFragment("第二步：添加人脸到FaceSet", FaceSetAddFaceFragment.class.getName(), bundle);
        }
    }

    @Override
    public void onFailed(String error) {
        super.onFailed(error);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_faceset_create;
    }

    @Override
    public FaceSetCreatePresenter newP() {
        return new FaceSetCreatePresenter();
    }
}
