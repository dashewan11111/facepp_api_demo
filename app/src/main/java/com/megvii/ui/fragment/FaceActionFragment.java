package com.megvii.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.megvii.ui.R;
import com.megvii.ui.presenter.BaseFaceActionPresenter;
import com.megvii.ui.utils.ImageChooseHelper;
import com.megvii.ui.view.FaceView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * @author by licheng on 2018/7/10.
 */

public abstract class FaceActionFragment<P extends BaseFaceActionPresenter> extends BaseActionFragment<P> {

    @BindView(R.id.faceView)
    FaceView faceView;

    @BindView(R.id.face_container)
    LinearLayout faceContainer;

    @BindView(R.id.result_container)
    LinearLayout resultContainer;

    @BindView(R.id.btn_choose_file)
    Button btnChooseFile;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        faceView.setImageResource(sampleResId(), 0, new Consumer<Bitmap>() {
            @Override
            public void accept(Bitmap bitmap) throws Exception {
                doAction(bitmap);
            }
        });
    }

    public void addCropFaceView(final List<Bitmap> cropFaceList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                faceContainer.removeAllViews();
                for (int index = 0; index < cropFaceList.size(); index++) {
                    faceContainer.addView(createItemView(index, cropFaceList.get(index)));
                }
            }
        });
    }

    public void addFaceRect(List<RectF> rectFList) {
        faceView.drawRect(rectFList);
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
    }

    private View createItemView(final int index, Bitmap bmp) {
        final LinearLayout faceViewItem = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_face, null);
        final ImageView imageView = faceViewItem.findViewById(R.id.imageView);
        imageView.setImageBitmap(bmp);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOnCropFaceClick(index, faceViewItem);
            }
        });
        if (0 != index) {
            faceViewItem.setAlpha(0.3f);
        }
        return faceViewItem;
    }

    private void doOnCropFaceClick(int index, LinearLayout faceViewItem) {
        for (int i = 0; i < faceContainer.getChildCount(); i++) {
            faceContainer.getChildAt(i).setAlpha(0.3f);
        }
        faceViewItem.setAlpha(1f);
        faceView.setItemSelected(index);
        onCropFaceClicked(index);
    }

    protected void onCropFaceClicked(int index) {

    }

    public void onHeadCrop(final List<Bitmap> cropFaceList) {

    }

    public void refreshUI(Bitmap bmp) {
        faceContainer.removeAllViews();
        resultContainer.removeAllViews();
        faceView.setImageResource(bmp, new Consumer<Bitmap>() {
            @Override
            public void accept(Bitmap bitmap) throws Exception {
                doAction(bitmap);
            }
        });
    }

    @OnClick(R.id.btn_choose_file)
    public void chooseFile(View view) {
        imageChooseHelper.chooseImage();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_action;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageChooseHelper.doOnActivityResult(requestCode, resultCode, data, new ImageChooseHelper.OnFileChooseListener() {
            @Override
            public void onFileChoose(Bitmap bitmap) {
                refreshUI(bitmap);
            }
        });
    }
}