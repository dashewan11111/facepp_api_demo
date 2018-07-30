package com.megvii.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.megvii.ui.R;
import com.megvii.ui.presenter.BaseFaceActionPresenter;
import com.megvii.ui.view.FaceView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

import static android.app.Activity.RESULT_OK;

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

    public void onFileChoose(Bitmap bmp) {
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
        showFileChooseDialog();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_action;
    }


    protected void showFileChooseDialog() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    //通过uri的方式返回，部分手机uri可能为空
                    if (uri != null) {
                        try {
                            //通过uri获取到bitmap对象
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                            onFileChoose(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //部分手机可能直接存放在bundle中
                        Bundle bundleExtras = data.getExtras();
                        if (bundleExtras != null) {
                            Bitmap bitmaps = bundleExtras.getParcelable("data");
                            onFileChoose(bitmaps);
                            // scrollView.smoothScrollTo(0, 0);
                        }
                    }
                }
                break;
        }
    }
}