package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.Face;
import com.megvii.facepp.api.bean.OcrIdCardResponse;
import com.megvii.ui.R;
import com.megvii.ui.datasource.ParametersConstant;
import com.megvii.ui.presenter.OCRIDCardPresenter;
import com.megvii.ui.view.FaceDetectAttributeView;
import com.megvii.ui.view.OCRIDCardResultView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by licheng on 2018/7/13.
 */

public class OCRIDCardFragment1 extends FaceActionFragment<OCRIDCardPresenter> {

    private OCRIDCardResultView attributeView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        params.put("legality", "1");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap[0]));
    }

    public void onSuccess(final OcrIdCardResponse response) {
        super.onSuccess();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new OCRIDCardResultView(context, getArguments().getBoolean("front"));
                attributeView.refresh(response.getCards().get(0));
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    int sampleResId() {
        boolean isFront = true;
        if (null != getArguments()) {
            isFront = getArguments().getBoolean("front");
        }
        return isFront ? R.drawable.demo_id_card_1 : R.drawable.demo_id_card_2;
    }

    @Override
    public OCRIDCardPresenter newP() {
        return new OCRIDCardPresenter();
    }
}
