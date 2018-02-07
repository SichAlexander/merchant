package com.whenin.bludarts.merchant;


import com.whenin.bludarts.merchant.di.api.ApiTestComponent;
import com.whenin.bludarts.merchant.untils.TestUtils;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(application = TestApplication.class,
        constants = BuildConfig.class,
        sdk = 19)
@Ignore
public class BaseTest {

    public ApiTestComponent component;
    public TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        component = (ApiTestComponent) App.getApiComponent();
        testUtils = new TestUtils();
    }

}
