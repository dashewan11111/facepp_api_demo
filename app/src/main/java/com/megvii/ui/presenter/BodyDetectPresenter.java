package com.megvii.ui.presenter;

import android.graphics.Bitmap;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.HumanBodyDetectResponse;
import com.megvii.ui.fragment.BodyDetectFragment;

import java.util.Map;

/**
 * @author by licheng on 2018/7/30.
 */

public class BodyDetectPresenter extends BaseFaceActionPresenter<BodyDetectFragment> {
    @Override
    public void doAction(final Object... params) {
        faceppApi.detectHumanBody((Map<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<HumanBodyDetectResponse>() {
            @Override
            public void onSuccess(HumanBodyDetectResponse humanBodyDetectResponse) {
                getCropBodyList((Bitmap) params[2], humanBodyDetectResponse.getHumanbodies());
                getV().onSuccess(humanBodyDetectResponse);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
