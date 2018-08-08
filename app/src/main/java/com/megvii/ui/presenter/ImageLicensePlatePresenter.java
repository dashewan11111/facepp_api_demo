package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.BankCardResponse;
import com.megvii.facepp.api.bean.LicensePlatResponse;
import com.megvii.ui.fragment.ImageLicensePlateFragment;
import com.megvii.ui.fragment.OCRBankCardFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class ImageLicensePlatePresenter extends BaseFaceActionPresenter<ImageLicensePlateFragment> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.recognizePlat((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<LicensePlatResponse>() {

            @Override
            public void onSuccess(LicensePlatResponse response) {
                getV().onSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
