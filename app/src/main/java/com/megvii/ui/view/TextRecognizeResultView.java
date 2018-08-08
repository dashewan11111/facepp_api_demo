package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.BankCard;
import com.megvii.facepp.api.bean.TextResult;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/31.
 */

public class TextRecognizeResultView extends LinearLayout implements IResultView<TextResult> {

    private TextView txtResult;
    private TextView txtRaw;

    public TextRecognizeResultView(Context context) {
        super(context);
        init(context);
    }

    public TextRecognizeResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextRecognizeResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_text_recognize, this);
        txtResult = rootView.findViewById(R.id.txtResult);
        txtRaw = rootView.findViewById(R.id.txtRaw);
    }

    @Override
    public void refresh(TextResult result) {
        if (null != result) {
            txtResult.setText(result.getValue());
            txtRaw.setText(result.toString());
        }
    }
}
