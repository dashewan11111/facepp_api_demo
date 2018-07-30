package com.megvii.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.megvii.ui.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * @author by licheng on 2018/7/27.
 */

public class FaceTokenListAdapter extends SimpleRecAdapter<String, FaceTokenListAdapter.FaceSetViewHolder> {

    private List<String> faceSetList = new ArrayList<>();

    private FaceTokenListAdapter.OnItemClickListener itemClickListener;

    public FaceTokenListAdapter(Context context, List<String> faceSetList) {
        super(context);
        this.faceSetList = faceSetList;
    }

    @Override
    public FaceTokenListAdapter.FaceSetViewHolder newViewHolder(View itemView) {
        return new FaceTokenListAdapter.FaceSetViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_face_token;
    }

    @Override
    public void onBindViewHolder(FaceTokenListAdapter.FaceSetViewHolder holder, int position) {
        final String token = faceSetList.get(position);
        holder.txtFaceSetToken.setText(token);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != itemClickListener) {
                    itemClickListener.onItemClick(token);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(String faceToken);
    }

    public void setItemClickListener(FaceTokenListAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return faceSetList.size();
    }

    class FaceSetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.face_token)
        TextView txtFaceSetToken;

        @BindView(R.id.face_user_id)
        TextView txtUserId;

        @BindView(R.id.btn_delete)
        Button btnDelete;

        public FaceSetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
