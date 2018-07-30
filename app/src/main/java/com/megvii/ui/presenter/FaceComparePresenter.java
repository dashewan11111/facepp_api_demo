package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.CompareResponse;
import com.megvii.ui.fragment.FaceCompareFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/16.
 */

public class FaceComparePresenter extends BaseActionPresenter<FaceCompareFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.compare((HashMap<String, String>) params[0], (byte[]) params[1], (byte[]) params[2], new IFacePPCallBack<CompareResponse>() {
            @Override
            public void onSuccess(CompareResponse compareResponse) {
                getV().onSuccess(compareResponse.getConfidence());
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
