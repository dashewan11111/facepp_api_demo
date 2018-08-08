package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.MergeFaceResponse;
import com.megvii.ui.fragment.ImageMergeFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class ImageMergePresenter extends BaseActionPresenter<ImageMergeFragment> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.mergeFace((HashMap<String, String>) params[0], (byte[]) params[1], (byte[]) params[2], new IFacePPCallBack<MergeFaceResponse>() {

            @Override
            public void onSuccess(MergeFaceResponse response) {
                getV().onSuccess(response.getResult());
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
