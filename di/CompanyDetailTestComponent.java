package com.whenin.bludarts.merchant.di;


import com.whenin.bludarts.merchant.di.company.detail.CompanyDetailComponent;
import com.whenin.bludarts.merchant.view.CompanyDetailTestView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex on 8/3/2016.
 */
@Singleton
@Component(modules = {CompanyDetailViewTestModel.class})
public interface CompanyDetailTestComponent extends CompanyDetailComponent {

    void inject(CompanyDetailTestView detailTestView);

}
