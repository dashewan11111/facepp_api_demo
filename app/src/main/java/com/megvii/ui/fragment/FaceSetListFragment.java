package com.megvii.ui.fragment;

import android.os.Bundle;

import com.megvii.facepp.api.bean.FaceSet;
import com.megvii.ui.R;
import com.megvii.ui.adapter.FaceSetListAdapter;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.FaceSetListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * @author by licheng on 2018/7/27.
 */

public class FaceSetListFragment extends BaseActionFragment<FaceSetListPresenter> implements FaceSetListAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;

    private FaceSetListAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        recyclerView.verticalLayoutManager(context);
        recyclerView.verticalDivider(R.color.translucent_white_50, R.dimen.common_1_px);
        adapter = new FaceSetListAdapter(context, new ArrayList<FaceSet>());
        adapter.setItemClickListener(this);
        recyclerView.setAdapter(adapter);

        dialogHelper.showAtProgress();
        getP().doAction();
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final List<FaceSet> faceSets = (List<FaceSet>) response[0];
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new FaceSetListAdapter(context, faceSets);
                adapter.setItemClickListener(FaceSetListFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onItemClick(FaceSet faceSet) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ParametersConstant.FACESET_TOKEN.getKey(), faceSet.getFaceset_token());
        bundle.putSerializable(ParametersConstant.DISPLAY_NAME.getKey(), faceSet.getDisplay_name());
        bundle.putSerializable(ParametersConstant.NEW_OUTER_ID.getKey(), faceSet.getOuter_id());
        bundle.putSerializable(ParametersConstant.TAGS.getKey(), faceSet.getTags());
        String subFragment = getArguments().getString("faceset_sub_fragment");
        ((FaceSetGroupFragment) getParentFragment()).switchFragment(subFragment, bundle);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_faceset_list;
    }

    @Override
    public FaceSetListPresenter newP() {
        return new FaceSetListPresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
