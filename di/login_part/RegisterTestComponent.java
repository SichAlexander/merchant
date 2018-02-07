package com.whenin.bludarts.merchant.di.login_part;


import com.whenin.bludarts.merchant.di.registration.RegistrationComponent;
import com.whenin.bludarts.merchant.view.RegisterTestView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex on 8/3/2016.
 */
@Singleton
@Component(modules = {RegistrationViewTest.class})
public interface RegisterTestComponent extends RegistrationComponent {

    void inject(RegisterTestView registerTestView);

}
