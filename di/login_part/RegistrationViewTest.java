package com.whenin.bludarts.merchant.di.login_part;


import com.whenin.bludarts.merchant.presenter.register.RegistrationPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Alex on 8/15/2016.
 */
@Module
public class RegistrationViewTest {
    @Provides
    @Singleton
    public RegistrationPresenterImpl getRegistrationPresenter() {
        return mock(RegistrationPresenterImpl.class);
    }

}
