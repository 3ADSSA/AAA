package com.baway.shoppingbwiedemo.presenter;

import com.baway.shoppingbwiedemo.view.iview.IMvpView;

public class BasePresenter<T extends IMvpView> {
    private T mT;
    public void attachView(T t){
        this.mT=t;
    }
    public T getMvpView(){
        return mT;
    }
}
