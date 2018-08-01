package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.Card;
import com.megvii.facepp.api.bean.Gesture;
import com.megvii.facepp.api.bean.OcrIdCardResponse;
import com.megvii.ui.R;
import com.megvii.ui.datasource.GestureDataSource;

/**
 * @author by licheng on 2018/7/31.
 */

public class OCRIDCardResultView extends LinearLayout implements IResultView<Card> {

    private TextView txtName;
    private TextView txtGender;
    private TextView txtMinzu;
    private TextView txtBirth;
    private TextView txtAddress;
    private TextView txtNum;
    private TextView txtFront;
    private TextView txtreal;
    private TextView txtJiguan;
    private TextView txtValidate;

    private boolean front;

    public OCRIDCardResultView(Context context, boolean front) {
        super(context);
        this.front = front;
        init(context);
    }

    public OCRIDCardResultView(Context context) {
        super(context);
        init(context);
    }

    public OCRIDCardResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OCRIDCardResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(front ? R.layout.view_ocr_idcard_front : R.layout.view_ocr_idcard_back, this);
        txtName = rootView.findViewById(R.id.attribute_name);
        txtGender = rootView.findViewById(R.id.attribute_gender);
        txtMinzu = rootView.findViewById(R.id.attribute_minzu);
        txtBirth = rootView.findViewById(R.id.attribute_birth);
        txtAddress = rootView.findViewById(R.id.attribute_address);
        txtNum = rootView.findViewById(R.id.attribute_num);
        txtFront = rootView.findViewById(R.id.attribute_front);
        txtreal = rootView.findViewById(R.id.attribute_real);
        txtJiguan = rootView.findViewById(R.id.attribute_jiguan);
        txtValidate = rootView.findViewById(R.id.attribute_validate);
    }

    @Override
    public void refresh(Card card) {
        if (null != card) {
            if (front) {
                txtName.setText(card.getName());
                txtGender.setText(card.getGender());
                txtMinzu.setText(card.getRace());
                txtBirth.setText(card.getBirthday());
                txtAddress.setText(card.getAddress());
                txtNum.setText(card.getId_card_number());
            } else {
                txtJiguan.setText(card.getIssued_by());
                txtValidate.setText(card.getValid_date());
            }
            txtFront.setText(card.getSide());
            txtreal.setText(card.getLegality());
        }
    }


}
