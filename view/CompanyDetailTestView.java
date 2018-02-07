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
import com.whenin.bludarts.merchant.activities.MainActivity;
import com.whenin.bludarts.merchant.di.CompanyDetailTestComponent;
import com.whenin.bludarts.merchant.di.login_part.RegisterTestComponent;
import com.whenin.bludarts.merchant.presenter.company.profile_detail.CompanyDetailPresenterImpl;
import com.whenin.bludarts.merchant.presenter.register.RegistrationPresenterImpl;
import com.whenin.bludarts.merchant.view.company.detail.CompanyDetailsFragment;
import com.whenin.bludarts.merchant.view.company.profile.CompanyProfileFragment;
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
public class CompanyDetailTestView {

    private MainActivity activity;
    private CompanyDetailsFragment fragment;
    public View fragmentView;
    public EditText email;
    public EditText pass;
    public EditText confirm;
    public static final String CORRECT_EMAIL        = "njakawaii@mail.ru";
    public static final String CORRECT_PASS         = "123123";
    public static final String INCORRECT_EMAIL      = "qwe";
    public static final String INCORRECT_PASS       = "qwe";


    @Inject
    CompanyDetailPresenterImpl presenter;

    public CompanyDetailTestComponent component;

    @Before
    public void setUp() throws Exception {
        component = (CompanyDetailTestComponent) App.getCompanyDetailComponent();
        fragment = new CompanyDetailsFragment();
        component.inject(this);
        activity = buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .get();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        CompanyProfileFragment myFragment = new CompanyProfileFragment();
        transaction.replace(R.id.container, myFragment, "myfrag");
        transaction.commit();
        fragment.onCreate(null);
        fragmentView = fragment.onCreateView(LayoutInflater.from(activity),
                (ViewGroup) activity.findViewById(R.id.container), null);

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



}
