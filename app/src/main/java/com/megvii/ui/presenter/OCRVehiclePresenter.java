package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.DriverLicenseResponse;
import com.megvii.facepp.api.bean.VehicleResponse;
import com.megvii.ui.fragment.OCRDriverFragment;
import com.megvii.ui.fragment.OCRVehicleFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class OCRVehiclePresenter extends BaseFaceActionPresenter<OCRVehicleFragment> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.ocrVehicle((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<VehicleResponse>() {

            @Override
            public void onSuccess(VehicleResponse response) {
                getV().onSuccess(response.getCards().get(0));
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
