package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.DriverLicenseMain;
import com.megvii.facepp.api.bean.DriverLicenseResponse;
import com.megvii.facepp.api.bean.DriverLicenseSecond;
import com.megvii.facepp.api.bean.Vehicle;
import com.megvii.facepp.api.bean.VehicleResponse;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/31.
 */

public class OCRVehicleResultView extends LinearLayout implements IResultView<Vehicle> {

    private TextView txtVehicleNum;
    private TextView txtCarType;
    private TextView txtOwner;
    private TextView txtAddress;
    private TextView txtUseFor;
    private TextView txtBrand;

    private TextView txtRecognizeCode;
    private TextView txtEngineNum;
    private TextView txtRegisterData;
    private TextView txtSend;
    private TextView txtSign;


    public OCRVehicleResultView(Context context) {
        super(context);
        init(context);
    }

    public OCRVehicleResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public OCRVehicleResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_ocr_vehicle, this);
        txtVehicleNum = rootView.findViewById(R.id.attribute_vehicle_num);
        txtCarType = rootView.findViewById(R.id.attribute_car_type);
        txtOwner = rootView.findViewById(R.id.attribute_owner);

        txtAddress = rootView.findViewById(R.id.attribute_address);
        txtUseFor = rootView.findViewById(R.id.attribute_use_for);
        txtBrand = rootView.findViewById(R.id.attribute_brand);

        txtRecognizeCode = rootView.findViewById(R.id.attribute_recognize_code);
        txtEngineNum = rootView.findViewById(R.id.attribute_engine_num);

        txtRegisterData = rootView.findViewById(R.id.attribute_register_data);
        txtSend = rootView.findViewById(R.id.attribute_send);
        txtSign = rootView.findViewById(R.id.attribute_sign);

    }

    @Override
    public void refresh(Vehicle vehicle) {
        if (null != vehicle) {

            txtVehicleNum.setText(vehicle.getPlate_no());
            txtCarType.setText(String.valueOf(vehicle.getType()));
            txtOwner.setText(vehicle.getOwner());

            txtAddress.setText(vehicle.getAddress());
            txtUseFor.setText(vehicle.getUse_character());
            txtBrand.setText(vehicle.getModel());

            txtRecognizeCode.setText(vehicle.getVin());
            txtEngineNum.setText(vehicle.getEngine_no());
            txtRegisterData.setText(vehicle.getRegister_date());
            txtSend.setText(vehicle.getIssue_date());
            txtSign.setText(vehicle.getIssued_by());
        }
    }
}
