package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.DetectResponse;
import com.megvii.facepp.api.bean.Face;
import com.megvii.facepp.api.bean.FaceAnalyzeResponse;
import com.megvii.ui.fragment.FaceAnalyzeFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/26.
 */

public class FaceAnalyzePresenter extends BaseActionPresenter<FaceAnalyzeFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.faceAnalyze((Map<String, String>) params[0], new IFacePPCallBack<FaceAnalyzeResponse>() {

            @Override
            public void onSuccess(FaceAnalyzeResponse faceAnalyzeResponse) {
                getV().onSuccess(faceAnalyzeResponse);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }

    public void detect(Object... params) {
        faceppApi.detect(new HashMap<String, String>(), (byte[]) params[0], new IFacePPCallBack<DetectResponse>() {

            @Override
            public void onSuccess(DetectResponse detectResponse) {
                String faceTokens = "";
                for (Face face : detectResponse.getFaces()) {
                    faceTokens += face.getFace_token() + ",";
                }
                getV().onDetectSuccess(faceTokens.substring(0, faceTokens.length() - 1));
            }

            @Override
            public void onFailed(String error) {
                getV().onDetectFailed(error);
            }
        });
    }
}
