package com.whenin.bludarts.merchant.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.whenin.bludarts.merchant.App;
import com.whenin.bludarts.merchant.BuildConfig;
import com.whenin.bludarts.merchant.R;
import com.whenin.bludarts.merchant.TestApplication;
import com.whenin.bludarts.merchant.activities.LoginActivity;
import com.whenin.bludarts.merchant.di.login_part.LoginTestComponent;
import com.whenin.bludarts.merchant.presenter.login.LoginPresenterImpl;
import com.whenin.bludarts.merchant.view.login.LoginFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Robolectric.buildActivity;

/**
 * Created by Alex on 7/29/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestApplication.class,
        constants = BuildConfig.class,
        sdk = 19)
public class LoginTestView {

    private LoginActivity activity;
    private LoginFragment fragment;
    public View fragmentView;
    public EditText email;
    public EditText pass;
    public Button signIn;
    public static final String CORRECT_EMAIL        = "njakawaii@mail.ru";
    public static final String CORRECT_PASS         = "123123";
    public static final String INCORRECT_EMAIL      = "qwe";
    public static final String INCORRECT_PASS       = "qwe";


    @Inject
    LoginPresenterImpl presenter;

    public LoginTestComponent component;

    @Before
    public void setUp() throws Exception {
        component = (LoginTestComponent) App.getLoginComponent();
        fragment = new LoginFragment();
        component.inject(this);
        activity = buildActivity(LoginActivity.class)
                .create()
                .start()
                .resume()
                .get();
        fragment.onCreate(null);
        fragmentView = fragment.onCreateView(LayoutInflater.from(activity),
                (ViewGroup) activity.findViewById(R.id.container), null);

        email   = (EditText) fragmentView.findViewById(R.id.email_et_LoginFragment);
        pass    = (EditText) fragmentView.findViewById(R.id.pass_et_LoginFragment);
        signIn  = (Button) fragmentView.findViewById(R.id.signIn_btn_LoginFragment);
    }

    @Test
    public void testOnCreateView() {
        assertNotNull(fragmentView);
    }

    @Test
    public void testOnStop() {
        fragment.onStop();
        verify(presenter).onStop();
    }

    @Test
    public void testDataNotEmpty() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        fragment.processSignIn();
        when(presenter.isNonEmtyFields(email.getText().toString(), pass.getText().toString()))
                .thenReturn(false);
    }

    @Test
    public void testEmailNotValid() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        email.setText(INCORRECT_EMAIL);
        fragment.processSignIn();
        when(presenter.isValidEmail(email.getText().toString())).thenReturn(false);
    }

    @Test
    public void testPassNotValid() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        email.setText(INCORRECT_PASS);
        when(presenter.isValidPass(pass.getText().toString())).thenReturn(false);
    }

    @Test
    public void testDataValid() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        email.setText(CORRECT_EMAIL);
        pass.setText(CORRECT_PASS);
        when(presenter.isValidEmail(email.getText().toString()))
                .thenReturn(false);
    }

}
