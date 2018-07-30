package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.FaceSetCreatResponse;
import com.megvii.ui.fragment.FaceSetCreateFragment;

import java.util.Map;

/**
 * @author by licheng on 2018/7/24.
 */

public class FaceSetCreatePresenter extends BaseActionPresenter<FaceSetCreateFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.facesetCreate((Map<String, String>) params[0], new IFacePPCallBack<FaceSetCreatResponse>() {
            @Override
            public void onSuccess(FaceSetCreatResponse faceSetCreatResponse) {
                getV().onSuccess(faceSetCreatResponse);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
