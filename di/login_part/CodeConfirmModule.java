package com.whenin.bludarts.merchant.di.login_part;


import com.whenin.bludarts.merchant.presenter.code_confirm.CodeConfirmPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Alex on 8/4/2016.
 */
@Module
public class CodeConfirmModule {

    @Provides
    @Singleton
    public CodeConfirmPresenterImpl getConfirmPresenter() {
        return mock(CodeConfirmPresenterImpl.class);
    }

}
