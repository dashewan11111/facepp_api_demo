package com.megvii.ui.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.ui.R;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * @author by licheng on 2018/8/18.
 */

public class ImageChooseHelper {

    private static final int PHOTO_REQUEST_CODE = 0x10;
    private static final int CAMERA_REQUEST_CODE = 0x20;

    private Object context;

    public ImageChooseHelper(Object context) {
        this.context = context;
    }

    private AlertDialog alertDialog;

    public void chooseImage() {
        if (null == getContext()) {
            return;
        }
        final AlertDialog.Builder mDialog = new AlertDialog.Builder((getContext()));
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_choose_image, null);

        View camera = rootView.findViewById(R.id.camera);
        View gallery = rootView.findViewById(R.id.gallery);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseByCamera();
                alertDialog.dismiss();
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseByFile();
                alertDialog.dismiss();
            }
        });
        mDialog.setView(rootView);
        mDialog.setCancelable(true);

        alertDialog = mDialog.create();
        alertDialog.show();
    }

    private Context getContext() {
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof Fragment) {
            return ((Fragment) context).getContext();
        }

        return null;
    }

    private void chooseByCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp.jpg")));
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } else if (context instanceof Fragment) {
            ((Fragment) context).startActivityForResult(intent, CAMERA_REQUEST_CODE);
        }
    }

    private void chooseByFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", true);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, PHOTO_REQUEST_CODE);
        } else if (context instanceof Fragment) {
            ((Fragment) context).startActivityForResult(intent, PHOTO_REQUEST_CODE);
        }
    }

    public void doOnActivityResult(int requestCode, int resultCode, Intent data, OnFileChooseListener listener) {
        if (null == listener || null == getContext()) {
            return;
        }
        switch (requestCode) {
            case PHOTO_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    //通过uri的方式返回，部分手机uri可能为空
                    if (uri != null) {
                        try {
                            //通过uri获取到bitmap对象
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                            listener.onFileChoose(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //部分手机可能直接存放在bundle中
                        Bundle bundleExtras = data.getExtras();
                        if (bundleExtras != null) {
                            Bitmap bitmaps = bundleExtras.getParcelable("data");
                            listener.onFileChoose(bitmaps);
                            // scrollView.smoothScrollTo(0, 0);
                        }
                    }
                }
                break;
            case CAMERA_REQUEST_CODE:
                Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp.jpg", 1024, 1024);
                if (null == bitmap) {
                    return;
                }
                Bitmap bitmapReal = BitmapUtil.amendRotatePhoto(Environment.getExternalStorageDirectory().getAbsolutePath() + "/temp.jpg", bitmap);
                if (null != bitmapReal) {
                    listener.onFileChoose(bitmapReal);
                }
                break;
        }
    }

    public interface OnFileChooseListener {
        void onFileChoose(Bitmap bitmap);
    }
}
