package com.whenin.bludarts.merchant.di.api;


import com.whenin.bludarts.merchant.global.Constants;
import com.whenin.bludarts.merchant.model.ApiInterface;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;

/**
 * Created by Alex on 8/3/2016.
 */
@Module
public class ApiTestModelModule {

    @Provides
    @Singleton
    public ApiInterface getApiInterface() {
        return mock(ApiInterface.class);
    }
    @Provides
    @Singleton
    @Named(Constants.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return Schedulers.immediate();
    }

    @Provides
    @Singleton
    @Named(Constants.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.immediate();
    }

}
