package com.megvii.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.ui.R;
import com.megvii.ui.bean.RatingBean;
import com.megvii.ui.presenter.FaceComparePresenter;
import com.megvii.ui.utils.ImageChooseHelper;
import com.megvii.ui.view.CommonRatingView;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * @author by licheng on 2018/7/16.
 */

public class FaceCompareFragment extends BaseActionFragment<FaceComparePresenter> {

    private static final int ACTION_1 = 1;
    private static final int ACTION_2 = 2;

    @BindView(R.id.image_1)
    ImageView image1;

    @BindView(R.id.image_2)
    ImageView image2;

    @BindView(R.id.result)
    CommonRatingView ratingView;

    @BindView(R.id.btn_choose_file_1)
    Button btnChooseFile1;

    @BindView(R.id.btn_choose_file_2)
    Button btnChooseFile2;

    Bitmap bmp1, bmp2;

    int actionId;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        image1.setImageResource(R.drawable.demo_compare);
        image2.setImageResource(R.drawable.demo_compare);

        bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.demo_compare);
        bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.demo_compare);

        doAction(bmp1, bmp2);
    }

    @OnClick(R.id.btn_choose_file_1)
    public void chooseFile1(View view) {
        actionId = ACTION_1;
        imageChooseHelper.chooseImage();
    }

    @OnClick(R.id.btn_choose_file_2)
    public void chooseFile2(View view) {
        actionId = ACTION_2;
        imageChooseHelper.chooseImage();
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        if (bitmap.length == 2) {

            if (null != bitmap[0]) {
                image1.setImageBitmap(bitmap[0]);
            }

            if (null != bitmap[1]) {
                image2.setImageBitmap(bitmap[1]);
            }

            if (null != bitmap[0] && null != bitmap[1]) {
                getP().doAction(new HashMap<String, String>(), BitmapUtil.toByteArray(bitmap[0]), BitmapUtil.toByteArray(bitmap[1]));
            } else {
                dialogHelper.dismiss();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageChooseHelper.doOnActivityResult(requestCode, resultCode, data, new ImageChooseHelper.OnFileChooseListener() {
            @Override
            public void onFileChoose(Bitmap bitmap) {
                if (ACTION_1 == actionId) {
                    bmp1 = bitmap;
                } else if (ACTION_2 == actionId) {
                    bmp2 = bitmap;
                }
                doAction(bmp1, bmp2);
            }
        });
    }


    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final float confidence = (float) response[0];
        final RatingBean ratingBean = new RatingBean();
        ratingBean.setScore(confidence);
        ratingBean.setDesc("相似度为 " + (float) (Math.round(confidence * 10f)) / 10f + " %");
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ratingView.refresh(ratingBean);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_compare;
    }

    @Override
    public FaceComparePresenter newP() {
        return new FaceComparePresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
