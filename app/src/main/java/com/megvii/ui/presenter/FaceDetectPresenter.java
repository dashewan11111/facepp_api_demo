package com.megvii.ui.presenter;

import android.graphics.Bitmap;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.DetectResponse;
import com.megvii.ui.fragment.FaceDetectFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class FaceDetectPresenter extends BaseFaceActionPresenter<FaceDetectFragment> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.detect((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<DetectResponse>() {

            @Override
            public void onSuccess(DetectResponse detectResponse) {
                getCropFaceList((Bitmap) params[2], detectResponse.getFaces());

                getV().onSuccess(detectResponse.getFaces());
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
