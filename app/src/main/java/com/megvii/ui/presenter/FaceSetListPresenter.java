package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.FaceSetListResponse;
import com.megvii.ui.fragment.FaceSetListFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/27.
 */

public class FaceSetListPresenter extends BaseActionPresenter<FaceSetListFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.facesetList(new HashMap<String, String>(), new IFacePPCallBack<FaceSetListResponse>() {
            @Override
            public void onSuccess(FaceSetListResponse faceSetListResponse) {
                getV().onSuccess(faceSetListResponse.getFaceSets());
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
