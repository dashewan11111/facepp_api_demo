package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.Face;
import com.megvii.ui.R;
import com.megvii.ui.presenter.FaceDetectPresenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by licheng on 2018/7/11.
 */

public class FaceDetectFragment extends BaseResultFragment {

    @Override
    void doAction() {
        dialogHelper.showAtProgress();
        Map<String, String> params = new HashMap<>();
        params.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        params.put("return_landmark", "2");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap));
    }

    @Override
    public void onActionSuccess(Object... response) {
        super.onActionSuccess(response);
        final List<Bitmap> bitmaps = (List<Bitmap>) response[0];
        final List<RectF> faceRect = (List<RectF>) response[1];

        final List<Face> faceList = (List<Face>) response[2];

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addSubViews(bitmaps, faceRect, faceList);
                faceView.drawRect(faceRect);
                refreshResultView(faceList.get(0));
            }
        });
    }

    // 添加人像的切图
    private void addSubViews(List<Bitmap> bitmaps, final List<RectF> faceRect, final List<Face> faceList) {
        faceContainer.removeAllViews();
        for (int i = 0; i < bitmaps.size(); i++) {
            final int index = i;
            final LinearLayout faceViewItem = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_face, null);
            final ImageView imageView = faceViewItem.findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmaps.get(i));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < faceContainer.getChildCount(); i++) {
                        faceContainer.getChildAt(i).setAlpha(0.3f);
                    }
                    faceViewItem.setAlpha(1f);
                    faceView.drawRect(faceRect, index);
                    refreshResultView(faceList.get(index));

                }
            });
            if (0 != index) {
                faceViewItem.setAlpha(0.3f);
            }
            faceContainer.addView(faceViewItem);
        }
    }

    protected void refreshResultView(Face face) {
        baseResultView.refresh(face);
    }

    @Override
    int getSampleResId() {
        return R.drawable.six;
    }

    @Override
    public FaceDetectPresenter newP() {
        return new FaceDetectPresenter();
    }
}
