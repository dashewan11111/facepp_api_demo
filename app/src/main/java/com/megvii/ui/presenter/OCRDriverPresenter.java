package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.DriverLicenseResponse;
import com.megvii.facepp.api.bean.OcrIdCardResponse;
import com.megvii.ui.fragment.OCRDriverFragment;
import com.megvii.ui.fragment.OCRIDCardFragment1;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class OCRDriverPresenter extends BaseFaceActionPresenter<OCRDriverFragment> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.ocrDriver((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<DriverLicenseResponse>() {

            @Override
            public void onSuccess(DriverLicenseResponse response) {
                getV().onSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
