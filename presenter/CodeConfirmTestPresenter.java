package com.whenin.bludarts.merchant.presenter;

import com.whenin.bludarts.merchant.BaseTest;
import com.whenin.bludarts.merchant.activities.LoginActivity;
import com.whenin.bludarts.merchant.model.api.APIModel;
import com.whenin.bludarts.merchant.presenter.code_confirm.CodeConfirmPresenterImpl;
import com.whenin.bludarts.merchant.untils.TestConst;
import com.whenin.bludarts.merchant.view.code_confirm.CodeConfirmView;
import com.whenin.bludarts.merchant.webapi.response.CodeConfirmResponse;
import com.whenin.bludarts.merchant.webapi.response.login_part.ResetConfirmationCodeResponse;

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
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 7/29/2016.
 */

public class CodeConfirmTestPresenter extends BaseTest {

    private LoginActivity activity;
    public static final String CORRECT_EMAIL        = "sichalexander73@gmail.com";
    public static final String INCORRECT_CODE       = "qwe";


    @Inject
    APIModel model;


    @Inject
    CodeConfirmResponse confirmCode;

    @Inject
    ResetConfirmationCodeResponse resendCode;

    private CodeConfirmView mockView;
    private CodeConfirmPresenterImpl presenter;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        component.inject(this);

        mockView = mock(CodeConfirmView.class);
        presenter = new CodeConfirmPresenterImpl();

        presenter.init(activity, mockView);

        doAnswer(invocation -> Observable.just(confirmCode))
                .when(model)
                .confirmRegistrationCodeRx(INCORRECT_CODE);

        doAnswer(invocation -> Observable.just(resendCode))
                .when(model)
                .resendConfirmCodeRx(CORRECT_EMAIL);


    }

    @Test
    public void testConfirm() {
        presenter.confirm(INCORRECT_CODE);
        presenter.onStop();

        verify(mockView).confirmSuccess(any());
    }

    @Test
    public void testConfirmNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .confirmRegistrationCodeRx(INCORRECT_CODE);

        presenter.confirm(INCORRECT_CODE);
        presenter.onStop();
        verify(mockView).confirmError();
    }



    @Test
    public void testOnFailConfirm() {
        doAnswer(invocation -> Observable.just(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .confirmRegistrationCodeRx(INCORRECT_CODE);

        presenter.confirm(INCORRECT_CODE);
        presenter.onStop();

        verify(mockView).confirmError();
    }


    @Test
    public void testResend() {
        presenter.resend(CORRECT_EMAIL);
        presenter.onStop();

        verify(mockView).resendSuccess(any());
    }

    @Test
    public void testResendNullData() {
        doAnswer(invocation -> Observable.just(null))
                .when(model)
                .resendConfirmCodeRx(INCORRECT_CODE);

        presenter.resend(INCORRECT_CODE);
        presenter.onStop();
        verify(mockView).resendError();
    }



    @Test
    public void testOnFailResend() {
        doAnswer(invocation -> Observable.just(new Throwable(TestConst.TEST_ERROR)))
                .when(model)
                .resendConfirmCodeRx(INCORRECT_CODE);

        presenter.resend(INCORRECT_CODE);
        presenter.onStop();

        verify(mockView).resendError();
    }

    @Test
    public void testSubscribe() {
        presenter = spy(new CodeConfirmPresenterImpl()); //for ArgumentCaptor
        presenter.init(activity, mockView);
        presenter.confirm(INCORRECT_CODE);
        presenter.resend(CORRECT_EMAIL);
        presenter.onStop();

        ArgumentCaptor<Subscription> captor = ArgumentCaptor.forClass(Subscription.class);
        verify(presenter, times(2)).addSubscription(captor.capture());
        List<Subscription> subscriptions = captor.getAllValues();
        assertEquals(2, subscriptions.size());
        assertTrue(subscriptions.get(0).isUnsubscribed());
        assertTrue(subscriptions.get(1).isUnsubscribed());
    }

}
