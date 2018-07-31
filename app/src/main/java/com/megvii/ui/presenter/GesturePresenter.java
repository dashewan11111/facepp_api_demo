package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.GestureResponse;
import com.megvii.facepp.api.bean.HumanSegmentResponse;
import com.megvii.ui.fragment.BodySegmentFragment;
import com.megvii.ui.fragment.GestureFragment;

import java.util.Map;

/**
 * @author by licheng on 2018/7/30.
 */

public class GesturePresenter extends BaseFaceActionPresenter<GestureFragment> {
    @Override
    public void doAction(Object... params) {
        faceppApi.gesture((Map<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<GestureResponse>() {
            @Override
            public void onSuccess(GestureResponse gestureResponse) {
                getV().onSuccess(gestureResponse);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
