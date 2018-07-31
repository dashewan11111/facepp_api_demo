package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.HumanBodyDetectResponse;
import com.megvii.facepp.api.bean.HumanSegmentResponse;
import com.megvii.ui.fragment.BodyDetectFragment;
import com.megvii.ui.fragment.BodySegmentFragment;

import java.util.Map;

/**
 * @author by licheng on 2018/7/30.
 */

public class BodySegmentPresenter extends BaseFaceActionPresenter<BodySegmentFragment> {
    @Override
    public void doAction(Object... params) {
        faceppApi.segment((Map<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<HumanSegmentResponse>() {
            @Override
            public void onSuccess(HumanSegmentResponse humanBodyDetectResponse) {
                getV().onSuccess(humanBodyDetectResponse);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
