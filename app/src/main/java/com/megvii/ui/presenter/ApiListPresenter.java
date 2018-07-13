package com.megvii.ui.presenter;

import com.megvii.ui.bean.ApiListItem;
import com.megvii.ui.fragment.ApiListFragment;
import com.megvii.ui.utils.AppUtils;

import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by licheng on 2018/7/6.
 */

public class ApiListPresenter extends XPresent<ApiListFragment> {

    public void loadData() {
//        try {
//            Observable.create(new ObservableOnSubscribe<List<ApiListItem>>() {
//                @Override
//                public void subscribe(ObservableEmitter<List<ApiListItem>> e) throws Exception {
//                    e.onNext(AppUtils.getApis(getV().getResources().getAssets().open("apis.xml"), "FaceApis"));
//                }
//            }).subscribeOn(Schedulers.io())
//                    .subscribeOn(AndroidSchedulers.mainThread())
//                    .compose(getV().<List<ApiListItem>>bindToLifecycle())
//                    .subscribe(new Consumer<List<ApiListItem>>() {
//                        @Override
//                        public void accept(List<ApiListItem> data) throws Exception {
//                            getV().onDataLoaded(data);
//                        }
//                    });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
