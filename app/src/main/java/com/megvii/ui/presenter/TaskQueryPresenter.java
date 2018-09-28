package com.megvii.ui.presenter;

import android.text.TextUtils;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.TaskQueryResponse;
import com.megvii.ui.fragment.TaskQueryFragment;

import java.util.Map;

/**
 * @author by licheng on 2018/9/28.
 */

public class TaskQueryPresenter extends BaseActionPresenter<TaskQueryFragment> {

    @Override
    public void doAction(Object... params) {
        faceppApi.facesetTaskQuery((Map<String, String>) params[0], new IFacePPCallBack<TaskQueryResponse>() {
            @Override
            public void onSuccess(TaskQueryResponse response) {
                if (!TextUtils.isEmpty(response.getError_message())) {
                    getV().onFailed(response.getError_message());
                } else {
                    getV().onSuccess(response);
                }
            }

            @Override
            public void onFailed(String s) {
                getV().onFailed(s);
            }
        });
    }
}
