package com.baway.shoppingbwiedemo.presenter;

import com.baway.shoppingbwiedemo.model.utils.HttpUtils;
import com.baway.shoppingbwiedemo.view.iview.IClassRightView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作用：
 * 作者：贾涛
 * 时间：2017/6/16
 * 思路：
 */

public class ClassRightPresenter extends BasePresenter<IClassRightView> {

    String uri="http://169.254.65.30/";
    String link_ramus="mobile/index.php";

    public void getDataFromServer(Map<String,String> map) {

        HttpUtils.getRequestData(uri, link_ramus, map, new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                if (getMvpView() != null) {
                    getMvpView().callBackSuc(value);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
