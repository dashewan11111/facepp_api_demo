package com.megvii.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;

import com.megvii.buz.utils.XUtils;
import com.megvii.ui.R;
import com.megvii.ui.bean.IParameters;
import com.megvii.ui.view.BaseParametersView;
import com.megvii.ui.view.ParametersInputView;
import com.megvii.ui.view.ParametersMultiChoiceView;
import com.megvii.ui.view.ParametersSingleChoiceView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by licheng on 2018/7/24.
 */

public class ParametersAdapter extends RecyclerView.Adapter<ParametersAdapter.ViewHolder> {

    private List<IParameters> parametersList = new ArrayList<>();

    private Context context;

    private Bundle bundle;

    public ParametersAdapter(Context context, List<IParameters> parametersList, Bundle bundle) {
        this.context = context;
        this.bundle = bundle;
        this.parametersList = (ArrayList<IParameters>) XUtils.deepCopy(parametersList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseParametersView itemView;
        switch (viewType) {
            case R.layout.view_parameters_input:
                itemView = new ParametersInputView(context);
                break;
            case R.layout.view_parameters_single_choice:
                itemView = new ParametersSingleChoiceView(context);
                break;
            case R.layout.view_parameters_multi_choice:
                itemView = new ParametersMultiChoiceView(context);
                break;
            default:
                itemView = new ParametersInputView(context);
                break;
        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BaseParametersView itemView = (BaseParametersView) holder.itemView;
        itemView.refresh(parametersList.get(position), bundle);
    }

    @Override
    public int getItemViewType(int position) {
        return parametersList.get(position).getLayoutId();
    }

    @Override
    public int getItemCount() {
        return parametersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(BaseParametersView itemView) {
            super(itemView);
        }
    }

    public Map<String, String> getParametersList() {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < parametersList.size(); i++) {
            IParameters parameters = parametersList.get(i);
            if (!TextUtils.isEmpty(parameters.getValue())) {
                result.put(parameters.getKey(), parameters.getValue());
            }
        }
        return result;
    }
}
