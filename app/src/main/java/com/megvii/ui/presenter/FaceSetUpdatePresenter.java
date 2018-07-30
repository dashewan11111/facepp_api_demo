package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.FaceSetUpdateResponse;
import com.megvii.ui.fragment.FaceSetUpdateFragment;

import java.util.Map;

/**
 * @author by licheng on 2018/7/26.
 */

public class FaceSetUpdatePresenter extends BaseActionPresenter<FaceSetUpdateFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.facesetUpdate((Map<String, String>) params[0], new IFacePPCallBack<FaceSetUpdateResponse>() {

            @Override
            public void onSuccess(FaceSetUpdateResponse response) {
                getV().onSuccess(response);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
