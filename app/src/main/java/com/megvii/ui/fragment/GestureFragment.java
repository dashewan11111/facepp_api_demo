package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.buz.view.loading.ProgressDialogHelper;
import com.megvii.facepp.api.bean.CommonRect;
import com.megvii.facepp.api.bean.Gesture;
import com.megvii.facepp.api.bean.GestureResponse;
import com.megvii.facepp.api.bean.Hands;
import com.megvii.facepp.api.bean.HumanBody;
import com.megvii.ui.R;
import com.megvii.ui.datasource.GestureDataSource;
import com.megvii.ui.presenter.BodySegmentPresenter;
import com.megvii.ui.presenter.GesturePresenter;
import com.megvii.ui.view.BaseResultView;
import com.megvii.ui.view.FaceView;
import com.megvii.ui.view.GestureResultView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author by licheng on 2018/7/30.
 */

public class GestureFragment extends FaceActionFragment<GesturePresenter> {

    private GestureResultView resultView;
    private final List<Bitmap> bmpList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        dialogHelper = new ProgressDialogHelper(getActivity());
        dialogHelper.showAtProgress();
        addGestures();
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        params.put("return_gesture", "1");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap[0]));
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final GestureResponse response1 = (GestureResponse) response[0];
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                resultView = new GestureResultView(context);
                resultView.refresh(response1.getHands().get(0).getGesture());
                resultContainer.addView(resultView);

                final List<RectF> rectFList = new ArrayList<>();
                for (Hands hand : response1.getHands()) {
                    CommonRect commonRect = hand.getHand_rectangle();
                    Rect rect = new Rect(commonRect.getLeft(), commonRect.getTop(),
                            commonRect.getLeft() + commonRect.getWidth(),
                            commonRect.getTop() + commonRect.getHeight());
                    // 人脸框
                    RectF rectF = new RectF(rect);
                    rectFList.add(rectF);
                }
                addFaceRect(rectFList);
            }
        });

    }

    @Override
    public GesturePresenter newP() {
        return new GesturePresenter();
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_body;
    }

    /**
     * 添加手势示例图片
     */
    void addGestures() {

        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                for (GestureDataSource.GestureResult result : GestureDataSource.GESTURES) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeResource(getResources(), result.getResId(), options);
                    Bitmap bmp = FaceView.drawableToBitmap(getResources().getDrawable(result.getResId()), options);
                    e.onNext(bmp);
                }
                e.onComplete();
            }
        }).subscribe(new Consumer<Bitmap>() {
            @Override
            public void accept(Bitmap bitmap) throws Exception {
                bmpList.add(bitmap);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                dialogHelper.dismiss();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                addCropFaceView(bmpList);
                dialogHelper.dismiss();
                faceView.setImageResource(bmpList.get(0), new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        doAction(bitmap);
                    }
                });
            }
        });
    }

    @Override
    protected void onCropFaceClicked(int index) {
        super.onCropFaceClicked(index);
        faceView.setImageResource(bmpList.get(index), new Consumer<Bitmap>() {
            @Override
            public void accept(Bitmap bitmap) throws Exception {
                doAction(bitmap);
            }
        });
    }
}
