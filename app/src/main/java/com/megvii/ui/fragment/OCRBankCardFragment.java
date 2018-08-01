package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.BankCard;
import com.megvii.facepp.api.bean.Vehicle;
import com.megvii.ui.R;
import com.megvii.ui.presenter.OCRBankCardPresenter;
import com.megvii.ui.presenter.OCRVehiclePresenter;
import com.megvii.ui.view.OCRBankCardResultView;
import com.megvii.ui.view.OCRVehicleResultView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/13.
 */

public class OCRBankCardFragment extends FaceActionFragment<OCRBankCardPresenter> {

    private OCRBankCardResultView attributeView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        params.put("return_score", "1");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap[0]));
    }

    public void onSuccess(final BankCard bankCard) {
        super.onSuccess();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new OCRBankCardResultView(context);
                attributeView.refresh(bankCard);
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_bank_card;
    }

    @Override
    public OCRBankCardPresenter newP() {
        return new OCRBankCardPresenter();
    }
}
