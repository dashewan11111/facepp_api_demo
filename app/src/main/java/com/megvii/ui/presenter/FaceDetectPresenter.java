package com.megvii.ui.presenter;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.CommonRect;
import com.megvii.facepp.api.bean.DetectResponse;
import com.megvii.facepp.api.bean.Face;
import com.megvii.ui.fragment.FaceDetectFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by licheng on 2018/7/11.
 */

public class FaceDetectPresenter extends BaseApiPresenter<FaceDetectFragment> {

    @Override
    public void doAction(Map<String, String> params, byte[] data) {
        faceppApi.detect(params, data, new IFacePPCallBack<DetectResponse>() {

            @Override
            public void onSuccess(DetectResponse detectResponse) {
                // 从左到右排序
                final List<Face> faceList = detectResponse.getFaces();
                Collections.sort(faceList, new Comparator<Face>() {
                    @Override
                    public int compare(Face face1, Face face2) {
                        return face1.getFace_rectangle().getLeft() - face2.getFace_rectangle().getLeft();
                    }
                });
                // 裁剪出小图，计算出人脸框
                final List<RectF> faceRect = new ArrayList<>();

                Observable.create(new ObservableOnSubscribe<List<Bitmap>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<Bitmap>> e) throws Exception {
                        List<Bitmap> bitmapList = new ArrayList<>();
                        for (Face face : faceList) {
                            // 裁剪小图
                            CommonRect commonRect = face.getFace_rectangle();
                            Rect rect = new Rect(commonRect.getLeft(), commonRect.getTop(),
                                    commonRect.getLeft() + commonRect.getWidth(),
                                    commonRect.getTop() + commonRect.getHeight());
                            bitmapList.add(BitmapUtil.cropBitmap(getV().getBitmap().copy(Bitmap.Config.RGB_565, true), rect));
                            // 人脸框
                            RectF rectF = new RectF(rect);
                            faceRect.add(rectF);
                        }
                        e.onNext(bitmapList);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Bitmap>>() {
                    @Override
                    public void accept(final List<Bitmap> bitmaps) throws Exception {
                        getV().onActionSuccess(bitmaps, faceRect, faceList);
                    }
                });
            }

            @Override
            public void onFailed(String s) {
                getV().onActionFailed(s);
            }
        });
    }
}
