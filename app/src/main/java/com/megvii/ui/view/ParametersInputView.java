package com.megvii.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.megvii.ui.R;
import com.megvii.ui.bean.IParameters;

/**
 * @author by licheng on 2018/7/24.
 */

public class ParametersInputView extends BaseParametersView {

    EditText editText;

    TextView txtDesc;

    private IParameters parameters;

    public ParametersInputView(Context context) {
        super(context);
    }

    public ParametersInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParametersInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_parameters_input, this);
        editText = rootView.findViewById(R.id.parameters_input);
        txtDesc = rootView.findViewById(R.id.parameters_desc);
    }

    @Override
    public IParameters getValue() {
        String text = editText.getText().toString();
        this.parameters.setValue(text);
        return this.parameters;
    }

    @Override
    public void refresh(final IParameters parameters, Bundle bundle) {
        this.parameters = parameters;
        editText.setHint(parameters.getKey());
        txtDesc.setText(parameters.getDesc());

        if (null != bundle && null != bundle.get(parameters.getKey())) {
            String value = (String) bundle.get(parameters.getKey());
            if (!TextUtils.isEmpty(value)) {
                editText.setText(value);
                parameters.setValue(value);
            }
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = editText.getText().toString();
                parameters.setValue(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
