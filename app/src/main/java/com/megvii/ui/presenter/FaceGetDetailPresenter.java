package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.DetectResponse;
import com.megvii.facepp.api.bean.Face;
import com.megvii.facepp.api.bean.FaceAnalyzeResponse;
import com.megvii.facepp.api.bean.FaceDetailResponse;
import com.megvii.ui.fragment.FaceAnalyzeFragment;
import com.megvii.ui.fragment.FaceGetDetailFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/26.
 */

public class FaceGetDetailPresenter extends BaseActionPresenter<FaceGetDetailFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.faceDetail((Map<String, String>) params[0], new IFacePPCallBack<FaceDetailResponse>() {

            @Override
            public void onSuccess(FaceDetailResponse faceDetailResponse) {
                getV().onSuccess(faceDetailResponse);
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
