package com.whenin.bludarts.merchant.di;


import com.whenin.bludarts.merchant.presenter.company.profile_detail.CompanyDetailPresenterImpl;
import com.whenin.bludarts.merchant.presenter.register.RegistrationPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by Alex on 8/15/2016.
 */
@Module
public class CompanyDetailViewTestModel {
    @Provides
    @Singleton
    public CompanyDetailPresenterImpl getCompanyDetail() {
        return mock(CompanyDetailPresenterImpl.class);
    }

}
