package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.Card;
import com.megvii.facepp.api.bean.DriverLicenseMain;
import com.megvii.facepp.api.bean.DriverLicenseResponse;
import com.megvii.facepp.api.bean.DriverLicenseSecond;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/31.
 */

public class OCRDriverResultView extends LinearLayout implements IResultView<DriverLicenseResponse> {

    private TextView txtConfidence;
    private TextView txtConfidence2;
    private TextView txtName;
    private TextView txtName2;
    private TextView txtDriverNum;
    private TextView txtDriverNum2;

    private TextView txtGender;
    private TextView txtNation;
    private TextView txtBirth;
    private TextView txtAddress;
    private TextView txtFirst;
    private TextView txtSign;
    private TextView txtType;
    private TextView txtVersion;
    private TextView txtValidate;

    private TextView txtDocNo;

    public OCRDriverResultView(Context context) {
        super(context);
        init(context);
    }

    public OCRDriverResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OCRDriverResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_ocr_driver, this);
        txtName = rootView.findViewById(R.id.attribute_name);
        txtDriverNum = rootView.findViewById(R.id.attribute_driver_num);
        txtConfidence = rootView.findViewById(R.id.attribute_confidence);

        txtName2 = rootView.findViewById(R.id.attribute_name_2);
        txtDriverNum2 = rootView.findViewById(R.id.attribute_driver_num_2);
        txtConfidence2 = rootView.findViewById(R.id.attribute_confidence_2);

        txtGender = rootView.findViewById(R.id.attribute_gender);
        txtNation = rootView.findViewById(R.id.attribute_nation);

        txtBirth = rootView.findViewById(R.id.attribute_birth);
        txtAddress = rootView.findViewById(R.id.attribute_address);
        txtFirst = rootView.findViewById(R.id.attribute_first);
        txtSign = rootView.findViewById(R.id.attribute_sign);
        txtType = rootView.findViewById(R.id.attribute_type);
        txtVersion = rootView.findViewById(R.id.attribute_version);
        txtValidate = rootView.findViewById(R.id.attribute_validate);
        txtDocNo = rootView.findViewById(R.id.attribute_doc_no);
    }

    @Override
    public void refresh(DriverLicenseResponse card) {
        if (null != card) {
            DriverLicenseMain main = card.getMain()[0];
            DriverLicenseSecond second = card.getSecond()[0];

            txtName.setText(main.getName().getContent());
            txtDriverNum.setText(main.getLicense_number().getContent());
            txtConfidence.setText(String.valueOf(main.getConfidence()));

            txtName2.setText(second.getName().getContent());
            txtDriverNum2.setText(second.getLicense_number().getContent());
            txtConfidence2.setText(String.valueOf(second.getConfidence()));

            txtGender.setText(main.getGender().getContent());
            txtNation.setText(main.getNationality().getContent());
            txtBirth.setText(main.getBirthday().getContent());
            txtAddress.setText(main.getAddress().getContent());
            txtFirst.setText(main.getIssue_date().getContent());

            txtSign.setText(main.getIssued_by().getContent());
            txtType.setText(main.getClazz().getContent());
            txtVersion.setText(main.getVersion().getContent());
            txtValidate.setText(main.getValid_for().getContent());
            txtDocNo.setText(second.getFile_number().getContent());
        }
    }
}
