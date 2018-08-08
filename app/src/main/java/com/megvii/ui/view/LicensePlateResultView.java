package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.buz.utils.XUtils;
import com.megvii.facepp.api.bean.BankCard;
import com.megvii.facepp.api.bean.LicensePlatResponse;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/31.
 */

public class LicensePlateResultView extends LinearLayout implements IResultView<LicensePlatResponse> {

    private TextView txtCarNum;

    public LicensePlateResultView(Context context) {
        super(context);
        init(context);
    }

    public LicensePlateResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LicensePlateResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_license_plate, this);
        txtCarNum = rootView.findViewById(R.id.car_num);
    }

    @Override
    public void refresh(LicensePlatResponse response) {
        if (null != response && null != response.getResults() && response.getResults().size() > 0) {
            txtCarNum.setText(response.getResults().get(0).getLicense_plate_number());
        }
    }
}
