package com.megvii.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.megvii.buz.utils.XUtils;
import com.megvii.ui.R;
import com.megvii.ui.adapter.FaceTokenListAdapter;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.FaceSetRemoveFacePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * @author by licheng on 2018/7/25.
 */

public class FaceSetRemoveFaceFragment extends BaseActionFragment<FaceSetRemoveFacePresenter> implements FaceTokenListAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    @BindView(R.id.parameters_input)
    EditText txtFaceTokens;

    @BindView(R.id.parameters_desc)
    TextView txtFaceTokensDesc;

    FaceTokenListAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        txtFaceTokens.setHint(ParametersConstant.FACE_TOKENS.getKey());
        txtFaceTokensDesc.setText(ParametersConstant.FACE_TOKENS.getDesc());

        recyclerView.verticalLayoutManager(context);
        recyclerView.verticalDivider(R.color.translucent_white_50, R.dimen.common_1_px);
        adapter = new FaceTokenListAdapter(context, new ArrayList<String>());
        recyclerView.setAdapter(adapter);

        getFaceTokens();
    }

    @OnClick(R.id.btn_remove_facetoken)
    public void removeFaceToken(View view) {
        dialogHelper.showAtProgress();
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ParametersConstant.FACESET_TOKEN.getKey(), getArguments().getString(ParametersConstant.FACESET_TOKEN.getKey()));
        parameters.put(ParametersConstant.FACE_TOKENS.getKey(), txtFaceTokens.getText().toString());
        getP().doAction(parameters);
    }

    @Override
    public void onItemClick(String faceToken) {
        String current = txtFaceTokens.getText().toString();
        if (TextUtils.isEmpty(current)) {
            txtFaceTokens.setText(faceToken);
        } else {
            String[] faceTokens = current.split(",");
            for (String item : faceTokens) {
                if (item.equals(faceToken)) {
                    return;
                }
            }
            txtFaceTokens.setText(current + "," + faceToken);
        }
    }

    private void getFaceTokens() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ParametersConstant.FACESET_TOKEN.getKey(), getArguments().getString(ParametersConstant.FACESET_TOKEN.getKey()));
        getP().getFaceSetDetails(parameters);
    }

    public void onGetFaceTokensSuccess(final List<String> faceTokenList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new FaceTokenListAdapter(context, faceTokenList);
                adapter.setItemClickListener(FaceSetRemoveFaceFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void onGetFaceTokensFailed(String error) {
        super.onFailed(error);
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "删除成功", Toast.LENGTH_LONG).show();
                txtFaceTokens.setText("");
            }
        });
        getFaceTokens();
    }

    @Override
    public void onFailed(String error) {
        super.onFailed(error);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_remove_face;
    }

    @Override
    public FaceSetRemoveFacePresenter newP() {
        return new FaceSetRemoveFacePresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
