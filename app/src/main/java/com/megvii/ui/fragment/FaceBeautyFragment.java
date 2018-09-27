package com.megvii.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.ui.R;
import com.megvii.ui.presenter.FaceBeautyPresenter;
import com.megvii.ui.utils.ImageChooseHelper;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * @author by licheng on 2018/7/18.
 */

public class FaceBeautyFragment extends BaseActionFragment<FaceBeautyPresenter> {

    @BindView(R.id.image_1)
    ImageView image1;

    @BindView(R.id.image_2)
    ImageView image2;

    @BindView(R.id.image2_container)
    LinearLayout container;

    @BindView(R.id.result)
    TextView txtResult;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        image1.setImageResource(R.drawable.demo_compare);
        doAction(BitmapFactory.decodeResource(getResources(), R.drawable.demo_compare));
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        image1.setImageBitmap(bitmap[0]);
        container.setVisibility(View.GONE);
        getP().doAction(new HashMap<String, String>(), BitmapUtil.toByteArray(bitmap[0]));
    }

    @OnClick(R.id.btn_choose_file)
    public void chooseFile(View view) {
        imageChooseHelper.chooseImage();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageChooseHelper.doOnActivityResult(requestCode, resultCode, data, new ImageChooseHelper.OnFileChooseListener() {
            @Override
            public void onFileChoose(Bitmap bitmap) {
                doAction(bitmap);
            }
        });
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final String image = (String) response[0];
        final String result = (String) response[1];
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                container.setVisibility(View.VISIBLE);
                image2.setImageBitmap(BitmapUtil.base64ToBitmap(image));
                txtResult.setText(result);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_beauty;
    }

    @Override
    public FaceBeautyPresenter newP() {
        return new FaceBeautyPresenter();
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_compare;
    }
}
