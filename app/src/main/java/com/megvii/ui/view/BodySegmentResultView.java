package com.megvii.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.HumanSegmentResponse;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/9/27.
 */

public class BodySegmentResultView extends BaseResultView<HumanSegmentResponse> implements View.OnClickListener {

    private ImageView imageSource;

    private ImageView imageBg;

    private Bitmap person;

    private static final int[] resIds = new int[]{R.drawable.card14, R.drawable.card15, R.drawable.card16, R.drawable.card17, R.drawable.card18, R.drawable.card19, R.drawable.card20};

    private int currentIndex;

    public BodySegmentResultView(Context context) {
        super(context);
    }

    public BodySegmentResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BodySegmentResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(HumanSegmentResponse data) {
        person = BitmapUtil.base64ToBitmap(data.getBody_image());
        Log.e("图片大小", person.getWidth() + " * " + person.getHeight());
        imageSource.setImageBitmap(person);
    }

    /**
     * 换背景
     */
    public void combine(Bitmap background) {
        imageSource.setImageBitmap(BitmapUtil.combine(background.copy(Bitmap.Config.ARGB_8888, true), person));
    }

    @Override
    public View getSubView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_change_bg, null);
        View viewLeft = rootView.findViewById(R.id.btn_left);
        View viewRight = rootView.findViewById(R.id.btn_right);

        viewLeft.setOnClickListener(this);
        viewRight.setOnClickListener(this);

        imageBg = rootView.findViewById(R.id.image_bg);
        imageBg.setImageResource(resIds[0]);

        imageSource = rootView.findViewById(R.id.image_source);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                if (0 == currentIndex) {
                    return;
                }
                currentIndex--;
                imageBg.setImageResource(resIds[currentIndex]);
                break;
            case R.id.btn_right:
                if (resIds.length - 1 == currentIndex) {
                    return;
                }
                currentIndex++;
                imageBg.setImageResource(resIds[currentIndex]);
                break;
        }
    }
}
