package com.megvii.ui.presenter;

import android.graphics.Bitmap;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.DetectResponse;
import com.megvii.facepp.api.bean.HumanSegmentResponse;
import com.megvii.ui.fragment.HeadSegmentFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/30.
 */

public class HeadSegmentPresenter extends BaseFaceActionPresenter<HeadSegmentFragment> {
    @Override
    public void doAction(Object... params) {
        final Bitmap originalBitmap = (Bitmap) params[1];
        faceppApi.detect(new HashMap<String, String>(), BitmapUtil.toByteArray(originalBitmap), new IFacePPCallBack<DetectResponse>() {

            @Override
            public void onSuccess(DetectResponse detectResponse) {
                getCropHeadList(originalBitmap, detectResponse.getFaces());
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }

    public void segment(Map<String, String> params, byte[] file) {
        faceppApi.segment(params, file, new IFacePPCallBack<HumanSegmentResponse>() {
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
