package com.whenin.bludarts.merchant.di.login_part;


import com.whenin.bludarts.merchant.di.confirm_code.CodeConfirmComponent;
import com.whenin.bludarts.merchant.view.CodeConfirmTestView;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex on 8/3/2016.
 */
@Singleton
@Component(modules = {CodeConfirmModule.class})
public interface CodeConfirmTestComponent extends CodeConfirmComponent {

    void inject(CodeConfirmTestView codeConfirmTestView);

}
