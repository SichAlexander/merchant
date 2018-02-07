package com.whenin.bludarts.merchant;


import com.whenin.bludarts.merchant.di.DaggerCompanyDetailTestComponent;
import com.whenin.bludarts.merchant.di.api.ApiComponent;
import com.whenin.bludarts.merchant.di.api.DaggerApiTestComponent;
import com.whenin.bludarts.merchant.di.company.detail.CompanyDetailComponent;
import com.whenin.bludarts.merchant.di.confirm_code.CodeConfirmComponent;
import com.whenin.bludarts.merchant.di.login.LoginComponent;
import com.whenin.bludarts.merchant.di.login_part.DaggerCodeConfirmTestComponent;
import com.whenin.bludarts.merchant.di.login_part.DaggerLoginTestComponent;
import com.whenin.bludarts.merchant.di.login_part.DaggerRegisterTestComponent;
import com.whenin.bludarts.merchant.di.registration.RegistrationComponent;

public class TestApplication extends App {

    @Override
    protected LoginComponent buildLoginComponent() {
        return DaggerLoginTestComponent.builder()
                .build();
    }

    @Override
    protected RegistrationComponent buildRegistrationComponent() {
        return DaggerRegisterTestComponent.builder()
                .build();
    }

    @Override
    protected ApiComponent buildApiComponent() {
        return DaggerApiTestComponent.builder()
                .build();
    }

    @Override
    protected CodeConfirmComponent buildCodeConfirmComponent() {
        return DaggerCodeConfirmTestComponent.builder().build();
    }

    @Override
    protected CompanyDetailComponent buildCompanyDetailComponent() {
        return DaggerCompanyDetailTestComponent.builder().build();
    }
}
