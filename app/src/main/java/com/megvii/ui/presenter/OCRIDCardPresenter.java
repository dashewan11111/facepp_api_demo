package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.OcrIdCardResponse;
import com.megvii.ui.fragment.OCRIDCardFragment1;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class OCRIDCardPresenter extends BaseFaceActionPresenter<OCRIDCardFragment1> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.ocrIDCard((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<OcrIdCardResponse>() {

            @Override
            public void onSuccess(OcrIdCardResponse response) {
                getV().onSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
