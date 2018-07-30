package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.DetectResponse;
import com.megvii.facepp.api.bean.Face;
import com.megvii.facepp.api.bean.FaceSetAddResponse;
import com.megvii.facepp.api.bean.SearchResponse;
import com.megvii.ui.fragment.FaceSetAddFaceFragment;
import com.megvii.ui.fragment.FaceSetFinalFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/24.
 */

public class FaceSetFinalPresenter extends BaseActionPresenter<FaceSetFinalFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.search((Map<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<SearchResponse>() {
            @Override
            public void onSuccess(SearchResponse response) {
                getV().onSuccess(response);
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
