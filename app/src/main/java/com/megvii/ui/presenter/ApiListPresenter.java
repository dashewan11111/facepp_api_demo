package com.megvii.ui.presenter;

import com.megvii.ui.fragment.ApiListFragment;

import cn.droidlover.xdroidmvp.mvp.XPresent;

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
