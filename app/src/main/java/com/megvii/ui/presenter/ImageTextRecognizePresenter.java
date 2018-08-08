package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.BankCardResponse;
import com.megvii.facepp.api.bean.RecognizeTextRespons;
import com.megvii.facepp.api.bean.TextResult;
import com.megvii.ui.fragment.ImageLicensePlateFragment;
import com.megvii.ui.fragment.ImageTextRecognizeFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class ImageTextRecognizePresenter extends BaseFaceActionPresenter<ImageTextRecognizeFragment> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.recognizeText((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<RecognizeTextRespons>() {

            @Override
            public void onSuccess(RecognizeTextRespons response) {
                if (response.getResult().size() > 0) {
                    getV().onSuccess(response.getResult().get(0));
                }
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
