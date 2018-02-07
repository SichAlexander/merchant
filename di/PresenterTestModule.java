package com.whenin.bludarts.merchant.di;


import com.whenin.bludarts.merchant.model.api.APIModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.mock;

/**
 * Created by Alex on 8/3/2016.
 */
@Module
public class PresenterTestModule {
    @Provides
    @Singleton
    APIModel provideDataRepository() {
        return mock(APIModel.class);
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
