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
import com.megvii.ui.presenter.ImageMergePresenter;

import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * @author by licheng on 2018/7/13.
 */

public class ImageMergeFragment extends BaseActionFragment<ImageMergePresenter> {

    @BindView(R.id.image_1)
    ImageView image1;

    @BindView(R.id.image_2)
    ImageView image2;

    @BindView(R.id.result)
    ImageView mergedView;

    @BindView(R.id.btn_choose_file_1)
    Button btnChooseFile1;

    @BindView(R.id.btn_choose_file_2)
    Button btnChooseFile2;

    Bitmap bmp1, bmp2;

    int actionId;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        image1.setImageResource(R.drawable.demo_template_file);
        image2.setImageResource(R.drawable.demo_merge_file);

        bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.demo_template_file);
        bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.demo_merge_file);

        doAction(bmp1, bmp2);
    }

    @OnClick(R.id.btn_choose_file_1)
    public void chooseFile1(View view) {
        actionId = 1;
        showFileChooseDialog(actionId);
    }

    @OnClick(R.id.btn_choose_file_2)
    public void chooseFile2(View view) {
        actionId = 2;
        showFileChooseDialog(actionId);
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


    protected void showFileChooseDialog(int actionId) {
        this.actionId = actionId;
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
                            if (1 == actionId) {
                                bmp1 = bitmap;
                            } else if (2 == actionId) {
                                bmp2 = bitmap;
                            }
                            doAction(bmp1, bmp2);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //部分手机可能直接存放在bundle中
                        Bundle bundleExtras = data.getExtras();
                        if (bundleExtras != null) {
                            Bitmap bitmap = bundleExtras.getParcelable("data");
                            if (1 == actionId) {
                                bmp1 = bitmap;
                            } else if (2 == actionId) {
                                bmp2 = bitmap;
                            }
                            doAction(bmp1, bmp2);
                            // scrollView.smoothScrollTo(0, 0);
                        }
                    }
                }
                break;
        }
    }

    public void onSuccess(final String response) {
        super.onSuccess(response);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mergedView.setImageBitmap(BitmapUtil.base64ToBitmap(response));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_merge;
    }

    @Override
    public ImageMergePresenter newP() {
        return new ImageMergePresenter();
    }

    @Override
    int sampleResId() {
        return 0;
    }
}
