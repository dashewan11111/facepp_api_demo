package com.megvii.ui.presenter;

import com.megvii.facepp.api.FacePPApi;
import com.megvii.ui.fragment.BaseActionFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author by licheng on 2018/7/13.
 */
public abstract class BaseActionPresenter<F extends BaseActionFragment> extends XPresent<F> {

    // TODO 存储
    protected FacePPApi faceppApi = new FacePPApi("Kx_DXTvQeTR1UJCASVyOweuJSLBN2Yno", "6T431Rj_cFg8u_yrbbw3BKZVP2PwE0gU");

    public abstract void doAction(Object... params);
}
