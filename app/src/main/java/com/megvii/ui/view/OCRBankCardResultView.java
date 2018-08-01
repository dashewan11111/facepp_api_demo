package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.BankCard;
import com.megvii.facepp.api.bean.Vehicle;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/31.
 */

public class OCRBankCardResultView extends LinearLayout implements IResultView<BankCard> {

    private TextView txtBankNum;
    private TextView txtBankName;
    private TextView txtOrg;

    public OCRBankCardResultView(Context context) {
        super(context);
        init(context);
    }

    public OCRBankCardResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OCRBankCardResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_ocr_bank_card, this);
        txtBankNum = rootView.findViewById(R.id.attribute_bank_num);
        txtBankName = rootView.findViewById(R.id.attribute_bank_name);
        txtOrg = rootView.findViewById(R.id.attribute_org);
    }

    @Override
    public void refresh(BankCard bankCard) {
        if (null != bankCard) {

            txtBankNum.setText(bankCard.getNumber());
            txtBankName.setText(bankCard.getBank());
            txtOrg.setText(bankCard.getOrganization().toString());
        }
    }
}
