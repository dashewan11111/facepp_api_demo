package com.megvii.ui.presenter;

import com.megvii.facepp.api.FacePPApi;
import com.megvii.ui.fragment.BaseActionFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author by licheng on 2018/7/13.
 */
public abstract class BaseActionPresenter<F extends BaseActionFragment> extends XPresent<F> {

    // TODO 存储
    protected FacePPApi faceppApi = new FacePPApi("syAe75QXfQHDt9YcmC8BJAJD0mX5nwqJ", "Q23rhNN6TsA8A6TcTOHkBsu-a7hBOUEB");

    public abstract void doAction(Object... params);
}
