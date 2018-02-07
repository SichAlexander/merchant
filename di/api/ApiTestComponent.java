package com.whenin.bludarts.merchant.di.api;


import com.whenin.bludarts.merchant.di.PresenterTestModule;
import com.whenin.bludarts.merchant.presenter.CodeConfirmTestPresenter;
import com.whenin.bludarts.merchant.presenter.LoginTestPresenter;
import com.whenin.bludarts.merchant.presenter.RegistrationTestPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex on 8/9/2016.
 */

@Singleton
@Component(modules = {ApiTestModelModule.class, PresenterTestModule.class, DataTestModule.class})
public interface ApiTestComponent extends ApiComponent {

    void inject(LoginTestPresenter loginPresenter);

    void inject(RegistrationTestPresenter registrationTestPresenter);

    void inject(CodeConfirmTestPresenter codeConfirmTestPresenter);


}
