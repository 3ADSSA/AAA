package com.baway.shoppingbwiedemo.view.iview;

public interface IClassView extends IMvpView {
    void callBackSuccess(String str);
    void callBackErr(String errMsg,String errCode);
}
