package com.megvii.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;


/**
 * @author by licheng on 2018/7/4.
 */

public class FaceRectPointView extends View {

    private Paint paint = new Paint();
    private Paint paintSelected = new Paint();

    private List<RectF> rectList; //人脸框

    private List<List<float[]>> points; // 人脸关键点

    private float scale;

    private int px, py;

    private int index; // 选中项

    public FaceRectPointView(Context context) {
        super(context);
        init(context);
    }

    public FaceRectPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FaceRectPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(6);
        paint.setStyle(Paint.Style.STROKE);

        paintSelected.setColor(Color.GREEN);
        paintSelected.setStrokeWidth(10);
        paintSelected.setStyle(Paint.Style.STROKE);
    }

    public void drawRect(List<RectF> rectList, int index) {
        this.index = index;
        this.rectList = rectList;
        this.points = null;
        invalidate();
    }

    public void drawPoints(List<List<float[]>> points) {
        this.points = points;
        this.rectList = null;
        invalidate();
    }

    public void clear() {
        this.rectList = null;
        this.points = null;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(px, py);
        canvas.scale(scale, scale);
        if (null != points) {
            for (List<float[]> face : points) {
                for (float[] point : face) {
                    canvas.drawPoints(point, paint);
                }
            }
        }

        if (null != rectList) {
            for (int i = 0; i < rectList.size(); i++) {
                RectF rect = rectList.get(i);
                if (i == index) {
                    canvas.drawRect(rect, paintSelected);
                } else {
                    canvas.drawRect(rect, paint);
                }
            }
        }
    }

    public void setScale(float scale, int px, int py) {
        this.scale = scale;
        this.px = px;
        this.py = py;
    }
}
