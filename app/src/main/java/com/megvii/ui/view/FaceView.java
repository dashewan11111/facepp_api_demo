package com.megvii.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.ui.R;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author by licheng on 2018/7/10.
 */

public class FaceView extends FrameLayout {

    private ImageView imageView;
    private FaceRectPointView faceRectPointView;

    private int viewWidth;
    private int viewHeight;

    private int toolbarHeight;

    public FaceView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FaceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FaceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_face_view, this);
        imageView = rootView.findViewById(R.id.sample_image);
        faceRectPointView = rootView.findViewById(R.id.face_rect_view);
    }

    public void setImageResource(final Bitmap bmp, final Consumer<Bitmap> consumer) {
        if (null != bmp) {
            faceRectPointView.clear();
            imageView.setImageBitmap(bmp);
            imageView.post(new Runnable() {
                @Override
                public void run() {
                    viewWidth = imageView.getMeasuredWidth();
                    viewHeight = imageView.getMeasuredHeight();

                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) faceRectPointView.getLayoutParams();
                    if (null == layoutParams) {
                        layoutParams = new FrameLayout.LayoutParams(viewWidth, viewHeight);
                    } else {
                        layoutParams.width = viewWidth;
                        layoutParams.height = viewHeight;
                    }
                    layoutParams.leftMargin = imageView.getLeft();
                    layoutParams.topMargin = imageView.getTop();
                    faceRectPointView.setLayoutParams(layoutParams);
                    faceRectPointView.setScale(viewWidth / (float) bmp.getWidth(), 0, 0);
                    try {
                        if (null != consumer) {
                            consumer.accept(bmp);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void setImageResource(final int resId) {
        setImageResource(resId, 0, null);
    }

    public void setImageResource(final int resId, final int toolbarHeight, final Consumer<Bitmap> consumer) {
        faceRectPointView.clear();
        imageView.setImageResource(resId);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                viewWidth = imageView.getMeasuredWidth();
                viewHeight = imageView.getMeasuredHeight();

                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) faceRectPointView.getLayoutParams();
                if (null == layoutParams) {
                    layoutParams = new FrameLayout.LayoutParams(viewWidth, viewHeight);
                } else {
                    layoutParams.width = viewWidth;
                    layoutParams.height = viewHeight;
                }
                layoutParams.leftMargin = imageView.getLeft();
                layoutParams.topMargin = imageView.getTop();
                faceRectPointView.setLayoutParams(layoutParams);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(getResources(), resId, options);

                Bitmap bmp = drawableToBitmap(getResources().getDrawable(resId), options);

//                options.inSampleSize = BitmapUtil.computeSampleSize(options, -1, viewWidth * viewHeight);
//                options.inJustDecodeBounds = false;
//                Bitmap bmp = BitmapFactory.decodeResource(getResources(), resId, options);

                float scale = viewWidth / (float) bmp.getWidth();
                faceRectPointView.setScale(scale, 0, 0);
                try {
                    if (null != consumer) {
                        consumer.accept(bmp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Bitmap drawableToBitmap(Drawable drawable, BitmapFactory.Options options) {
        // 取 drawable 的长宽
        int w = options.outWidth;
        int h = options.outHeight;

        // 取 drawable 的颜色格式
        Bitmap.Config config = Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    public void setItemSelected(int index) {
        this.faceRectPointView.setIndex(index);
    }

    public void drawRect(List<RectF> rectList) {
        drawRect(rectList, 0);
    }

    public void drawRect(List<RectF> rectList, int index) {
        faceRectPointView.drawRect(rectList, index);
    }

    public void drawPoints(List<List<float[]>> points) {
        faceRectPointView.drawPoints(points);
    }

    public void setToolBarHeight(int toolbarHeight) {
        this.toolbarHeight = toolbarHeight;
    }
}
