package com.megvii.ui.presenter;

import com.megvii.facepp.api.IFacePPCallBack;
import com.megvii.facepp.api.bean.BankCardResponse;
import com.megvii.facepp.api.bean.VehicleResponse;
import com.megvii.ui.fragment.OCRBankCardFragment;
import com.megvii.ui.fragment.OCRVehicleFragment;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class OCRBankCardPresenter extends BaseFaceActionPresenter<OCRBankCardFragment> {

    @Override
    public void doAction(final Object... params) {
        faceppApi.ocrBankCard((HashMap<String, String>) params[0], (byte[]) params[1], new IFacePPCallBack<BankCardResponse>() {

            @Override
            public void onSuccess(BankCardResponse response) {
                getV().onSuccess(response.getBank_cards().get(0));
            }

            @Override
            public void onFailed(String error) {
                getV().onFailed(error);
            }
        });
    }
}
