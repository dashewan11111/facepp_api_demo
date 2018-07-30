package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.BeautyResponse;
import com.megvii.facepp.api.bean.CompareResponse;
import com.megvii.ui.fragment.FaceBeautyFragment;
import com.megvii.ui.fragment.FaceCompareFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/16.
 */

public class FaceBeautyPresenter extends BaseActionPresenter<FaceBeautyFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.beautify((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<BeautyResponse>() {
            @Override
            public void onSuccess(BeautyResponse beautyResponse) {

                String base64 = beautyResponse.getResult();
                beautyResponse.setResult("太长了，人工省略...");
                getV().onSuccess(base64, beautyResponse.toString());
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
