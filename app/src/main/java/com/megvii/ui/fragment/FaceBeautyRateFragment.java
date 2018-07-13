package com.megvii.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.Beauty;
import com.megvii.facepp.api.bean.Face;
import com.megvii.ui.R;
import com.megvii.ui.presenter.FaceBeautyRatePresenter;
import com.megvii.ui.view.BeautyRatingResultView;
import com.megvii.ui.view.BaseResultView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by licheng on 2018/7/12.
 */

public class FaceBeautyRateFragment extends FaceDetectFragment {

    @Override
    int getSampleResId() {
        return R.drawable.demo_beauty;
    }

    @Override
    void doAction() {
        dialogHelper.showAtProgress();
        Map<String, String> params = new HashMap<>();
        params.put("return_attribute", "beauty");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap));
    }

    @Override
    public void onActionSuccess(Object... response) {
        super.onActionSuccess(response);
    }

    @Override
    protected void refreshResultView(Face face) {
        baseResultView.refresh(face.getAttributes().getBeauty());
    }

    @Override
    BaseResultView resultView(Context context) {
        return new BeautyRatingResultView(context);
    }
}
