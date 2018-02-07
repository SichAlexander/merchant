package com.whenin.bludarts.merchant.presenter;


import com.whenin.bludarts.merchant.BaseTest;
import com.whenin.bludarts.merchant.activities.LoginActivity;
import com.whenin.bludarts.merchant.model.api.APIModel;
import com.whenin.bludarts.merchant.presenter.register.RegistrationPresenterImpl;
import com.whenin.bludarts.merchant.untils.TestConst;
import com.whenin.bludarts.merchant.view.registration.RegistrationView;
import com.whenin.bludarts.merchant.webapi.response.login_part.RegisterResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 7/29/2016.
 */

public class RegistrationTestPresenter extends BaseTest {

    private LoginActivity activity;

    public static final String CORRECT_EMAIL        = "njakawaii@mail.ru";
    public static final String CORRECT_PASS         = "123123";
    public static final String INCORRECT_EMAIL      = "qwasdf@gmail.com";
    public static final String INCORRECT_PASS       = "qweqwee";


    @Inject
    APIModel model;

    @Inject
    RegisterResponse registerResponse;


    private RegistrationView mockView;
    private RegistrationPresenterImpl presenter;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        mockView = mock(RegistrationView.class);
        presenter = new RegistrationPresenterImpl();
        presenter.init(activity, mockView);

        doAnswer(invocation -> Observable.just(registerResponse))
                .when(model)
                .registerRx(CORRECT_EMAIL, CORRECT_PASS, CORRECT_PASS);

    }

    @Test
    public void testRegistration() {
        presenter.register(CORRECT_EMAIL, CORRECT_PASS, CORRECT_PASS);
        presenter.onStop();

        verify(mockView).registerSuccess();
    }

    @Test
    public void testRegistrationNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .registerRx(CORRECT_EMAIL, CORRECT_PASS, CORRECT_PASS);

        presenter.register(CORRECT_EMAIL, CORRECT_PASS, CORRECT_PASS);
        presenter.onStop();
        verify(mockView, never()).registerErrorEmail(any());
    }

    @Test
    public void testOnFailRegistration() {
        doAnswer(invocation -> Observable.just(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .registerRx(CORRECT_EMAIL, CORRECT_PASS, CORRECT_PASS);

        presenter.register(CORRECT_EMAIL, CORRECT_PASS, CORRECT_PASS);
        presenter.onStop();

        verify(mockView).registrationFail();
    }

     @Test
    public void testSubscribe() {
        presenter = spy(new RegistrationPresenterImpl()); //for ArgumentCaptor
        presenter.init(activity, mockView);
        presenter.register(CORRECT_EMAIL, CORRECT_PASS, CORRECT_PASS);
        presenter.onStop();

        ArgumentCaptor<Subscription> captor = ArgumentCaptor.forClass(Subscription.class);
        verify(presenter, times(1)).addSubscription(captor.capture());
        List<Subscription> subscriptions = captor.getAllValues();
        assertEquals(1, subscriptions.size());
        assertTrue(subscriptions.get(0).isUnsubscribed());
    }



}
