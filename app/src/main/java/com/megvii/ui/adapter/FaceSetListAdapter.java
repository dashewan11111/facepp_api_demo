package com.megvii.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.megvii.facepp.api.bean.FaceSet;
import com.megvii.ui.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * @author by licheng on 2018/7/27.
 */

public class FaceSetListAdapter extends SimpleRecAdapter<FaceSet, FaceSetListAdapter.FaceSetViewHolder> {

    private List<FaceSet> faceSetList = new ArrayList<>();

    private OnItemClickListener itemClickListener;

    public FaceSetListAdapter(Context context, List<FaceSet> faceSetList) {
        super(context);
        this.faceSetList = faceSetList;
    }

    @Override
    public FaceSetViewHolder newViewHolder(View itemView) {
        return new FaceSetViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_faceset;
    }

    @Override
    public void onBindViewHolder(FaceSetViewHolder holder, int position) {
        final FaceSet faceSet = faceSetList.get(position);
        holder.txtFaceSetToken.setText("faceset_token: " + faceSet.getFaceset_token());
        holder.txtDisplayName.setText("displayName：" + faceSet.getDisplay_name());
        holder.txtOuterId.setText("outer_id：" + faceSet.getOuter_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != itemClickListener) {
                    itemClickListener.onItemClick(faceSet);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(FaceSet faceSet);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return faceSetList.size();
    }

    class FaceSetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.faceset_token)
        TextView txtFaceSetToken;

        @BindView(R.id.display_name)
        TextView txtDisplayName;

        @BindView(R.id.faceset_outer_id)
        TextView txtOuterId;

        public FaceSetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
