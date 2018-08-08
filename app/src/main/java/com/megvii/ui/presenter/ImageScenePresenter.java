package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.BankCardResponse;
import com.megvii.facepp.api.bean.SceneDetectResponse;
import com.megvii.ui.fragment.ImageLicensePlateFragment;
import com.megvii.ui.fragment.ImageSceneFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class ImageScenePresenter extends BaseFaceActionPresenter<ImageSceneFragment> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.detectScene((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<SceneDetectResponse>() {

            @Override
            public void onSuccess(SceneDetectResponse response) {
                getV().onSuccess(response);
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
