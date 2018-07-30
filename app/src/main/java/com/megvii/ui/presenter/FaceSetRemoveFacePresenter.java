package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.FaceSetDetailResponse;
import com.megvii.facepp.api.bean.FaceSetRemoveResponse;
import com.megvii.ui.fragment.FaceSetRemoveFaceFragment;

import java.util.Map;

/**
 * @author by licheng on 2018/7/24.
 */

public class FaceSetRemoveFacePresenter extends BaseActionPresenter<FaceSetRemoveFaceFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.facesetRemoveFace((Map<String, String>) params[0], new IFacePPCallBack<FaceSetRemoveResponse>() {
            @Override
            public void onSuccess(FaceSetRemoveResponse response) {
                getV().onSuccess(response);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }

    public void getFaceSetDetails(Object... params) {
        faceppApi.facesetDetail((Map<String, String>) params[0], new IFacePPCallBack<FaceSetDetailResponse>() {

            @Override
            public void onSuccess(FaceSetDetailResponse response) {
                getV().onGetFaceTokensSuccess(response.getFace_tokens());
            }

            @Override
            public void onFailed(String error) {
                getV().onGetFaceTokensFailed(error);
            }
        });
    }
}
