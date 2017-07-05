package com.baway.shoppingbwiedemo.presenter;

import com.baway.shoppingbwiedemo.model.utils.HttpUtils;
import com.baway.shoppingbwiedemo.view.iview.ILoginView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<ILoginView> {

    private String uri="http://169.254.65.30/";
    private String link_ramus="mobile/index.php";

    public void getDataFromServer(Map<String,String> map){
        HttpUtils.postRequestData(uri, link_ramus, map, new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                if (getMvpView() != null){
                    getMvpView().callBackSuccess(value);
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
