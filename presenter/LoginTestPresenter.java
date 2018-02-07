package com.whenin.bludarts.merchant.presenter;

import com.whenin.bludarts.merchant.BaseTest;
import com.whenin.bludarts.merchant.activities.LoginActivity;
import com.whenin.bludarts.merchant.model.api.APIModel;
import com.whenin.bludarts.merchant.presenter.login.LoginPresenterImpl;
import com.whenin.bludarts.merchant.untils.TestConst;
import com.whenin.bludarts.merchant.view.login.LoginView;
import com.whenin.bludarts.merchant.webapi.response.login_part.ForgotResponse;
import com.whenin.bludarts.merchant.webapi.response.login_part.LoginResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
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

public class LoginTestPresenter extends BaseTest {

    private LoginActivity activity;

    public static final String CORRECT_EMAIL        = "njakawaii@mail.ru";
    public static final String CORRECT_PASS         = "123123";
    public static final String INCORRECT_EMAIL      = "qwasdf@gmail.com";
    public static final String INCORRECT_PASS       = "qwe";


    @Inject
    APIModel model;

    @Inject
    LoginResponse loginModel;

    @Inject
    ForgotResponse forgotPassword;

    private LoginView mockView;
    private LoginPresenterImpl presenter;



    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        mockView = mock(LoginView.class);
        presenter = new LoginPresenterImpl();
        presenter.init(activity, mockView);

        doAnswer(invocation -> Observable.just(loginModel))
                .when(model)
                .loginRx(CORRECT_EMAIL, CORRECT_PASS);

        doAnswer(invocation -> Observable.just(forgotPassword))
                .when(model)
                .forgotPasswordRx(CORRECT_EMAIL);

    }

    @Test
    public void testLogin() {
        presenter.doLogin(CORRECT_EMAIL, CORRECT_PASS);
        presenter.onStop();

        verify(mockView).loginSuccess(loginModel.getToken());
    }

    @Test
    public void testLoginNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .loginRx(CORRECT_EMAIL, CORRECT_PASS);

        presenter.doLogin(CORRECT_EMAIL, CORRECT_PASS);
        presenter.onStop();
        verify(mockView, never()).loginPassError(any());
    }

    @Test
    public void testOnFailLogin() {
        doAnswer(invocation -> Observable.just(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .loginRx(CORRECT_EMAIL, CORRECT_PASS);

        presenter.doLogin(CORRECT_EMAIL, CORRECT_PASS);
        presenter.onStop();

        verify(mockView).loginNoDataError();
    }

    @Test
    public void testOnErrorLogin() {
        doAnswer(invocation -> Observable.just(model))
                .when(model)
                .loginRx(INCORRECT_EMAIL, CORRECT_PASS);

        presenter.doLogin(INCORRECT_EMAIL, CORRECT_PASS);
        presenter.onStop();

        verify(mockView).loginNoDataError();
    }

    @Test
    public void testForgotPass() {
        presenter.forgotPassword(CORRECT_EMAIL);
        presenter.onStop();

        verify(mockView).forgotPassSuccess(forgotPassword.getMsg());
    }

    @Test
    public void testForgotPassNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .forgotPasswordRx(CORRECT_EMAIL);

        presenter.forgotPassword(CORRECT_EMAIL);
        presenter.onStop();
        verify(mockView).forgotPassError();
    }

    @Test
    public void testOnFailForgotPass() {
        doAnswer(invocation -> Observable.just(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .forgotPasswordRx(INCORRECT_EMAIL);

        presenter.forgotPassword(INCORRECT_EMAIL);
        presenter.onStop();

        verify(mockView).forgotPassError();
    }



    @Test
    public void testSubscribe() {
        presenter = spy(new LoginPresenterImpl()); //for ArgumentCaptor
        presenter.init(activity, mockView);
        presenter.doLogin(CORRECT_EMAIL, CORRECT_PASS);
        presenter.forgotPassword(CORRECT_EMAIL);
        presenter.onStop();

        ArgumentCaptor<Subscription> captor = ArgumentCaptor.forClass(Subscription.class);
        verify(presenter, times(2)).addSubscription(captor.capture());
        List<Subscription> subscriptions = captor.getAllValues();
        assertEquals(2, subscriptions.size());
        assertTrue(subscriptions.get(0).isUnsubscribed());
        assertTrue(subscriptions.get(1).isUnsubscribed());
    }

}
