package com.whenin.bludarts.merchant.di.login_part;


import com.whenin.bludarts.merchant.presenter.login.LoginPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;


/**
 * Created by Alex on 8/4/2016.
 */
@Module
public class LoginViewTest {

    @Provides
    @Singleton
    public LoginPresenterImpl getLoginPresenter() {
        return mock(LoginPresenterImpl.class);
    }

}
