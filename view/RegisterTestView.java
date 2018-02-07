package com.whenin.bludarts.merchant.view;

import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.whenin.bludarts.merchant.App;
import com.whenin.bludarts.merchant.BuildConfig;
import com.whenin.bludarts.merchant.R;
import com.whenin.bludarts.merchant.TestApplication;
import com.whenin.bludarts.merchant.activities.LoginActivity;
import com.whenin.bludarts.merchant.di.login_part.RegisterTestComponent;
import com.whenin.bludarts.merchant.presenter.register.RegistrationPresenterImpl;
import com.whenin.bludarts.merchant.view.registration.RegistrationFragment;

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
public class RegisterTestView {

    private LoginActivity activity;
    private RegistrationFragment fragment;
    public View fragmentView;
    public EditText email;
    public EditText pass;
    public EditText confirm;
    public static final String CORRECT_EMAIL        = "njakawaii@mail.ru";
    public static final String CORRECT_PASS         = "123123";
    public static final String INCORRECT_EMAIL      = "qwe";
    public static final String INCORRECT_PASS       = "qwe";


    @Inject
    RegistrationPresenterImpl presenter;

    public RegisterTestComponent component;

    @Before
    public void setUp() throws Exception {
        component = (RegisterTestComponent) App.getRegistrationComponent();
        fragment = new RegistrationFragment();
        component.inject(this);
        activity = buildActivity(LoginActivity.class)
                .create()
                .start()
                .resume()
                .get();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        RegistrationFragment myFragment = new RegistrationFragment();
        transaction.replace(R.id.container, myFragment, "myfrag");
        transaction.commit();
        fragment.onCreate(null);
        fragmentView = fragment.onCreateView(LayoutInflater.from(activity),
                (ViewGroup) activity.findViewById(R.id.container), null);

        email       = (EditText) fragmentView.findViewById(R.id.email_et_RegistrationFragment);
        pass        = (EditText) fragmentView.findViewById(R.id.password_et_RegistrationFragment);
        confirm     = (EditText) fragmentView.findViewById(R.id.confirmPassword_et_RegistrationFragment);
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
    public void testDataNotValid() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        when(presenter.isEmptyFields(email.getText().toString(), pass.getText().toString(),
                confirm.getText().toString())).thenReturn(false);
    }

    @Test
    public void testWrongLenthEmailValid() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        email.setText(INCORRECT_EMAIL);
        when(presenter.isWrongLenthEmail(email.getText().toString())).thenReturn(true);
    }

    @Test
    public void testEmailValid() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        email.setText(INCORRECT_EMAIL);
        when(presenter.isEmailValid(email.getText().toString())).thenReturn(true);
    }

    @Test
    public void testPassValid() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        pass.setText(CORRECT_PASS);
        when(presenter.isValidPass(pass.getText().toString())).thenReturn(true);
    }

    @Test
    public void testPassValidError() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        pass.setText(INCORRECT_PASS);
        when(presenter.isValidPass(pass.getText().toString())).thenReturn(false);
    }

    @Test
    public void testPassMatchValid() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        pass.setText(CORRECT_PASS);
        confirm.setText(CORRECT_PASS);
        when(presenter.isPassMatch(pass.getText().toString(), confirm.getText().toString()))
                .thenReturn(true);
    }

    @Test
    public void testPassMatchError() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        pass.setText(CORRECT_PASS);
        confirm.setText(INCORRECT_PASS);
        when(presenter.isPassMatch(pass.getText().toString(), confirm.getText().toString()))
                .thenReturn(false);
    }

}
