package com.megvii.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.megvii.ui.R;
import com.megvii.ui.activity.ResultActivity;
import com.megvii.ui.bean.ApiListItem;
import com.megvii.ui.datasource.ApiDataSource2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;

/**
 * @author by licheng on 2018/7/6.
 */

public class ApiListAdapter extends SimpleRecAdapter<ApiListItem, ApiListAdapter.ViewHolder> {

    private Context context;

    private List<ApiDataSource2.ApiItem> apiList;

    public ApiListAdapter(final Context context, List<ApiDataSource2.ApiItem> apiList) {
        super(context);
        this.context = context;
        this.apiList = apiList;
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
        return apiList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ApiDataSource2.ApiItem item = apiList.get(position);
        if (null == item) {
            return;
        }
        holder.txtName.setText(item.getTitle());
        holder.txtDesc.setText(item.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.launch((Activity) context, item.getClassName(), item.getTitle(),
                        item.getSubFragment());
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
