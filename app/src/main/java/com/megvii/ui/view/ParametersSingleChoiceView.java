package com.megvii.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.megvii.buz.utils.XUtils;
import com.megvii.ui.R;
import com.megvii.ui.bean.IParameters;

/**
 * @author by licheng on 2018/7/24.
 */

public class ParametersSingleChoiceView extends BaseParametersView {

    private TextView txtKey, txtDesc;

    private RadioGroup parameterPool;

    private Context context;

    int width;

    public ParametersSingleChoiceView(Context context) {
        super(context);
    }

    public ParametersSingleChoiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParametersSingleChoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context) {
        this.context = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_parameters_single_choice, this);
        txtKey = rootView.findViewById(R.id.parameter_key);
        txtDesc = rootView.findViewById(R.id.parameter_desc);
        parameterPool = rootView.findViewById(R.id.parameter_pool);
        width = XUtils.getScreenWidthAndHeight(context).x;
    }

    @Override
    public void refresh(final IParameters parameters, Bundle bundle) {

        txtKey.setText(parameters.getKey());
        txtDesc.setText(parameters.getDesc());

        for (int i = 0; i < parameters.getValuePool().length; i++) {
            final IParameters.Bean bean = parameters.getValuePool()[i];
            final RadioButton radioButton = new RadioButton(context);
            radioButton.setId(i);
            radioButton.setLayoutParams(new RadioGroup.LayoutParams(
                    (width - XUtils.dip2px(context, 100)) / parameters.getValuePool().length,
                    RadioGroup.LayoutParams.WRAP_CONTENT));

            radioButton.setTextColor(getResources().getColor(R.color.translucent_black_05));
            radioButton.setText(bean.getDesc());
            radioButton.setChecked(bean.getValue().equals(parameters.getValue()));
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        parameters.setValue(bean.getValue());
                    }
                }
            });
            parameterPool.addView(radioButton);
        }

    }

    @Override
    public IParameters getValue() {
        return null;
    }
}
