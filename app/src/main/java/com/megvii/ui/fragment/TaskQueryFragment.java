package com.megvii.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.megvii.buz.utils.JSONFormat;
import com.megvii.ui.R;
import com.megvii.ui.adapter.ParametersAdapter;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.TaskQueryPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * @author by licheng on 2018/9/28.
 */

public class TaskQueryFragment extends BaseActionFragment<TaskQueryPresenter> {

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
        recyclerView.addFooterView(txtResult);
        adapter = new ParametersAdapter(context, ParametersConstant.FACE_TASK_QUERY, null);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @OnClick(R.id.btn_task_query)
    public void getTaskStatus(View view) {
        dialogHelper.showAtProgress();
        getP().doAction(adapter.getParametersList());
    }

    @Override
    public void onSuccess(final Object... response) {
        super.onSuccess(response);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtResult.setText(new JSONFormat().formatJson(response[0].toString()));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_task_query;
    }

    @Override
    public TaskQueryPresenter newP() {
        return new TaskQueryPresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
