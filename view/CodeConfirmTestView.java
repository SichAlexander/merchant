package com.whenin.bludarts.merchant.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.whenin.bludarts.merchant.App;
import com.whenin.bludarts.merchant.BuildConfig;
import com.whenin.bludarts.merchant.R;
import com.whenin.bludarts.merchant.TestApplication;
import com.whenin.bludarts.merchant.activities.LoginActivity;
import com.whenin.bludarts.merchant.di.login_part.CodeConfirmTestComponent;
import com.whenin.bludarts.merchant.presenter.code_confirm.CodeConfirmPresenterImpl;
import com.whenin.bludarts.merchant.view.code_confirm.CodeConfirmationFragment;

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
public class CodeConfirmTestView {

    private LoginActivity activity;
    private CodeConfirmationFragment fragment;
    public View fragmentView;
    public EditText confirm;
    public static final String CORRECT_EMAIL        = "njakawaii@mail.ru";
    public static final String INCORRECT_EMAIL      = "";


    @Inject
    CodeConfirmPresenterImpl presenter;

    public CodeConfirmTestComponent component;

    @Before
    public void setUp() throws Exception {
        component = (CodeConfirmTestComponent) App.getCodeConfirmComponent();
        fragment = new CodeConfirmationFragment();
        component.inject(this);
        activity = buildActivity(LoginActivity.class)
                .create()
                .start()
                .resume()
                .get();
        fragment.onCreate(null);
        fragmentView = fragment.onCreateView(LayoutInflater.from(activity),
                (ViewGroup) activity.findViewById(R.id.container), null);

        confirm   = (EditText) fragmentView.findViewById(R.id.confirmationCode_et_CodeConfrmationFragment);
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
    public void testCodeEmpty() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        when(presenter.isNonEmptyData("")).thenReturn(false);
    }

    @Test
    public void testCodeNotEmpty() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        confirm.setText("qweq442");
        when(presenter.isNonEmptyData(confirm.getText().toString())).thenReturn(true);
    }


    @Test
    public void testEmailNotEmpty() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        when(presenter.isNoEmptyEmail(CORRECT_EMAIL)).thenReturn(true);
    }

    @Test
    public void testEmailEmpty() {
        fragment.onViewCreated(fragmentView, null);
        fragment.onResume();
        when(presenter.isNoEmptyEmail(INCORRECT_EMAIL)).thenReturn(false);
    }

}
