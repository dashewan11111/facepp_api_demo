package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/12.
 */

public abstract class BaseResultView<D> extends LinearLayout implements IResultView<D> {

    protected LinearLayout resultViewContainer;

    protected Context context;

    public BaseResultView(Context context) {
        super(context);
        init(context);
    }

    public BaseResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_result_base, this);
        resultViewContainer = rootView.findViewById(R.id.result_view_container);

         resultViewContainer.addView(getSubView(context));
    }

    public abstract View getSubView(Context context);
}
