package com.megvii.ui.presenter;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.CommonRect;
import com.megvii.facepp.api.bean.Face;
import com.megvii.facepp.api.bean.HumanBody;
import com.megvii.ui.fragment.FaceActionFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by licheng on 2018/7/13.
 */

public abstract class BaseFaceActionPresenter<F extends FaceActionFragment> extends BaseActionPresenter<F> {

    /**
     * 获取裁剪之后的小头像
     *
     * @param faceList
     * @return
     */
    protected void getCropFaceList(final Bitmap original, final List<Face> faceList) {
        if (null == faceList || faceList.size() == 0) {
            return;
        }
        // 从左到右排序
        Collections.sort(faceList, new Comparator<Face>() {
            @Override
            public int compare(Face face1, Face face2) {
                return face1.getFace_rectangle().getLeft() - face2.getFace_rectangle().getLeft();
            }
        });

        final List<RectF> rectFList = new ArrayList<>();
        // 裁剪出小图，计算出人脸框
        Observable.create(new ObservableOnSubscribe<List<Bitmap>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Bitmap>> e) throws Exception {
                List<Bitmap> bitmapList = new ArrayList<>();
                for (Face face : faceList) {
                    // 裁剪小图
                    CommonRect commonRect = face.getFace_rectangle();
                    Rect rect = new Rect(commonRect.getLeft(), commonRect.getTop(), commonRect.getLeft() + commonRect.getWidth(), commonRect.getTop() + commonRect.getHeight());
                    bitmapList.add(BitmapUtil.cropBitmap(original.copy(Bitmap.Config.RGB_565, true), rect));
                    // 人脸框
                    RectF rectF = new RectF(rect);
                    rectFList.add(rectF);
                }
                e.onNext(bitmapList);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Bitmap>>() {
            @Override
            public void accept(final List<Bitmap> bitmaps) throws Exception {
                onFaceCrop(bitmaps);
                onFaceRect(rectFList);
            }
        });
    }

    /**
     * 获取裁剪之后的小人体图
     *
     * @param bodyList
     * @return
     */
    protected void getCropBodyList(final Bitmap original, final List<HumanBody> bodyList) {
        if (null == bodyList || bodyList.size() == 0) {
            return;
        }
        // 从左到右排序
        Collections.sort(bodyList, new Comparator<HumanBody>() {
            @Override
            public int compare(HumanBody body1, HumanBody body2) {
                return body1.getHumanbody_rectangle().getLeft() - body2.getHumanbody_rectangle().getLeft();
            }
        });

        final List<RectF> rectFList = new ArrayList<>();
        // 裁剪出小图，计算出人脸框
        Observable.create(new ObservableOnSubscribe<List<Bitmap>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Bitmap>> e) throws Exception {
                List<Bitmap> bitmapList = new ArrayList<>();
                for (HumanBody body : bodyList) {
                    // 裁剪小图
                    CommonRect commonRect = body.getHumanbody_rectangle();
                    Rect rect = new Rect(commonRect.getLeft(), commonRect.getTop(), commonRect.getLeft() + commonRect.getWidth(), commonRect.getTop() + commonRect.getHeight());
                    bitmapList.add(BitmapUtil.cropBitmap(original.copy(Bitmap.Config.RGB_565, true), rect));
                    // 人脸框
                    RectF rectF = new RectF(rect);
                    rectFList.add(rectF);
                }
                e.onNext(bitmapList);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Bitmap>>() {
            @Override
            public void accept(final List<Bitmap> bitmaps) throws Exception {
                onFaceCrop(bitmaps);
                onFaceRect(rectFList);
            }
        });
    }

    /**
     * 获取裁剪之后的小头像
     *
     * @param faceList
     * @return
     */
    protected void getCropHeadList(final Bitmap original, final List<Face> faceList) {
        if (null == faceList || faceList.size() == 0) {
            return;
        }
        // 从左到右排序
        Collections.sort(faceList, new Comparator<Face>() {
            @Override
            public int compare(Face face1, Face face2) {
                return face1.getFace_rectangle().getLeft() - face2.getFace_rectangle().getLeft();
            }
        });


        // 裁剪出小图，计算出人脸框
        Observable.create(new ObservableOnSubscribe<List<Bitmap>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Bitmap>> e) throws Exception {
                List<Bitmap> bitmapList = new ArrayList<>();
                for (Face face : faceList) {
                    // 裁剪小图
                    CommonRect commonRect = face.getFace_rectangle();
                    Rect rect = new Rect(commonRect.getLeft() * 7 / 10, commonRect.getTop() * 4 / 10, commonRect.getLeft() + commonRect.getWidth() * 10 / 8, commonRect.getTop() + commonRect.getHeight() * 10 / 9);
                    bitmapList.add(BitmapUtil.cropBitmap(original.copy(Bitmap.Config.RGB_565, true), rect));
                }
                e.onNext(bitmapList);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Bitmap>>() {
            @Override
            public void accept(final List<Bitmap> bitmaps) throws Exception {
                onHeadCrop(bitmaps);
            }
        });
    }


    private void onFaceCrop(List<Bitmap> cropFaceList) {
        getV().addCropFaceView(cropFaceList);
    }

    private void onFaceRect(List<RectF> rectFList) {
        getV().addFaceRect(rectFList);
    }

    private void onHeadCrop(List<Bitmap> cropFaceList) {
        getV().onHeadCrop(cropFaceList);
    }
}
