package com.megvii.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.megvii.ui.bean.IParameters;

/**
 * @author by licheng on 2018/7/24.
 */

public abstract class BaseParametersView extends LinearLayout {

    public BaseParametersView(Context context) {
        super(context);
        init(context);
    }

    public BaseParametersView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseParametersView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected abstract void init(Context context);

    public abstract void refresh(IParameters parameters, Bundle bundle);

    public abstract IParameters getValue();
}
