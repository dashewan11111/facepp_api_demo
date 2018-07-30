package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.FaceSetDetailResponse;
import com.megvii.ui.fragment.FaceSetDetailFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/27.
 */

public class FaceSetDetailPresenter extends BaseActionPresenter<FaceSetDetailFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.facesetDetail((HashMap<String, String>) params[0], new IFacePPCallBack<FaceSetDetailResponse>() {
            @Override
            public void onSuccess(FaceSetDetailResponse faceSetDetailResponse) {
                getV().onSuccess(faceSetDetailResponse);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
