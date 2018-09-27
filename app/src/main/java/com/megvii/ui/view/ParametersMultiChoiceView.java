package com.megvii.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.buz.utils.XUtils;
import com.megvii.ui.R;
import com.megvii.ui.bean.IParameters;

/**
 * @author by licheng on 2018/7/24.
 */

public class ParametersMultiChoiceView extends BaseParametersView {

    private TextView txtKey, txtDesc;

    private GridLayout parameterPool;

    private Context context;

    int width;

    public ParametersMultiChoiceView(Context context) {
        super(context);
    }

    public ParametersMultiChoiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParametersMultiChoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context) {
        this.context = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_parameters_multi_choice, this);
        txtKey = rootView.findViewById(R.id.parameter_key);
        txtDesc = rootView.findViewById(R.id.parameter_desc);
        parameterPool = rootView.findViewById(R.id.parameter_pool);

        width = XUtils.getScreenWidthAndHeight(context).x;
    }

    @Override
    public void refresh(final IParameters parameters, Bundle bundle) {
        txtKey.setText(parameters.getKey());
        txtDesc.setText(parameters.getDesc());

        for (final IParameters.Bean bean : parameters.getValuePool()) {
            CheckBox checkBox = new CheckBox(context);
            checkBox.setTextColor(getResources().getColor(R.color.translucent_black_05));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((width - XUtils.dip2px(context, 60)) / 2, XUtils.dip2px(context, 50));
            checkBox.setLayoutParams(layoutParams);
            checkBox.setChecked(parameters.getValue().contains(bean.getValue()));
            checkBox.setText(bean.getDesc());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        String value = parameters.getValue();
                        if (TextUtils.isEmpty(value)) {
                            parameters.setValue(bean.getValue());
                        } else {
                            parameters.setValue(value + "," + bean.getValue());
                        }

                    } else {
                        String value = parameters.getValue();
                        String newValue = "";
                        if (!TextUtils.isEmpty(value)) {
                            String[] valueItems = value.split(",");
                            for (String item : valueItems) {
                                if (!TextUtils.equals(item, bean.getValue())) {
                                    if (TextUtils.isEmpty(newValue)) {
                                        newValue = item;
                                    } else {
                                        newValue = newValue + "," + item;
                                    }
                                }
                            }
                            parameters.setValue(newValue);
                        }
                    }
                }
            });

            parameterPool.addView(checkBox);
        }

        invalidate();
    }

    @Override
    public IParameters getValue() {
        return null;
    }
}
