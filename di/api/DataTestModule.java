package com.whenin.bludarts.merchant.di.api;


import com.whenin.bludarts.merchant.untils.TestUtils;
import com.whenin.bludarts.merchant.webapi.response.CodeConfirmResponse;
import com.whenin.bludarts.merchant.webapi.response.login_part.ForgotResponse;
import com.whenin.bludarts.merchant.webapi.response.login_part.LoginResponse;
import com.whenin.bludarts.merchant.webapi.response.login_part.RegisterResponse;
import com.whenin.bludarts.merchant.webapi.response.login_part.ResetConfirmationCodeResponse;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alex on 8/12/2016.
 */
@Module
public class DataTestModule {

    private TestUtils testUtils;

    public DataTestModule() {
        testUtils = new TestUtils();
    }

    @Provides
    @Singleton
    LoginResponse getLogin() {
        LoginResponse login = testUtils.readJson("json/login", LoginResponse.class);
        return login;
    }

    @Provides
    @Singleton
    ForgotResponse getForgotPass() {
        ForgotResponse forgotPassword = testUtils.readJson("json/forgot_pass", ForgotResponse.class);
        return forgotPassword;
    }

    @Provides
    @Singleton
    RegisterResponse getRegistration() {
        RegisterResponse response = testUtils.readJson("json/registration", RegisterResponse.class);
        return response;
    }

    @Provides
    @Singleton
    ResetConfirmationCodeResponse getResendCode() {
        ResetConfirmationCodeResponse response = testUtils.readJson("json/resend_confirm", ResetConfirmationCodeResponse.class);
        return response;
    }

    @Provides
    @Singleton
    CodeConfirmResponse getCodeConfirmation() {
        CodeConfirmResponse response = testUtils.readJson("json/confirm_code", CodeConfirmResponse.class);
        return response;
    }
}
