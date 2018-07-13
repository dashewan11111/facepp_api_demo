package com.megvii.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.megvii.ui.R;
import com.megvii.ui.activity.ResultActivity;
import com.megvii.ui.datasource.ApiDataSource;
import com.megvii.ui.bean.ApiListItem;
import com.megvii.ui.fragment.FaceDetectFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * @author by licheng on 2018/7/6.
 */

public class ApiListAdapter extends SimpleRecAdapter<ApiListItem, ApiListAdapter.ViewHolder> {

    private Context context;

    public ApiListAdapter(final Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_api_list;
    }

    @Override
    public int getItemCount() {
        return ApiDataSource.values().length;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ApiDataSource item = ApiDataSource.getApiByIndex(position);
        if (null == item) {
            return;
        }
        holder.txtName.setText(item.getTitle());
        holder.txtDesc.setText(item.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.launch((Activity) context, FaceDetectFragment.class.getName(), item.getTitle());
            }
        });
        holder.imgIcon.setImageResource(item.getResId());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.api_name)
        TextView txtName;

        @BindView(R.id.api_desc)
        TextView txtDesc;

        @BindView(R.id.icon)
        ImageView imgIcon;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
