package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.DetectResponse;
import com.megvii.facepp.api.bean.Face;
import com.megvii.facepp.api.bean.FaceAnalyzeResponse;
import com.megvii.facepp.api.bean.FaceToken;
import com.megvii.ui.fragment.FaceAnalyzeFragment;
import com.megvii.ui.fragment.FaceSetUserIdFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/26.
 */

public class FaceSetUserIdPresenter extends BaseActionPresenter<FaceSetUserIdFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.setFaceUserId((Map<String, String>) params[0], new IFacePPCallBack<FaceToken>() {

            @Override
            public void onSuccess(FaceToken faceAnalyzeResponse) {
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
