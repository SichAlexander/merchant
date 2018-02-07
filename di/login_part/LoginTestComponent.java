package com.whenin.bludarts.merchant.di.login_part;


import com.whenin.bludarts.merchant.di.login.LoginComponent;
import com.whenin.bludarts.merchant.view.LoginTestView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex on 8/3/2016.
 */
@Singleton
@Component(modules = {LoginViewTest.class})
public interface LoginTestComponent extends LoginComponent {

//    void inject(LoginTestView loginTest);
//
//    void inject(APIModelImpl modelImpl);

//    void inject(LoginPresenterImpl loginPresenter);

    void inject(LoginTestView loginFragment);

}
