package com.megvii.ui.presenter;

import com.megvii.facepp.api.FacePPApi;
import com.megvii.ui.fragment.BaseResultFragment;

import java.util.Map;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * @author by licheng on 2018/7/5.
 */

public class BaseApiPresenter<P extends BaseResultFragment> extends XPresent<P> {

    protected FacePPApi faceppApi = new FacePPApi("Kx_DXTvQeTR1UJCASVyOweuJSLBN2Yno", "6T431Rj_cFg8u_yrbbw3BKZVP2PwE0gU");

    public void doAction(Map<String, String> params) {

    }

    public void doAction(Map<String, String> params, byte[] data) {

    }

    public void doAction(Map<String, String> params, byte[] data1, byte[] data2) {

    }
}
