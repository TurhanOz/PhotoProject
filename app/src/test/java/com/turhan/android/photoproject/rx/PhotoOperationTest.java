package com.turhan.android.photoproject.rx;

import com.turhan.android.photoproject.BuildConfig;
import com.turhan.android.photoproject.adapter.PhotoAdapter;
import com.turhan.android.photoproject.rest.PhotoService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;

import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PhotoOperationTest {
    @Mock PhotoAdapter adapter;
    @Mock ArrayList photos;
    @Mock PhotoService service;

    PhotoOperation sut;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        when(adapter.getPhotos()).thenReturn(photos);
        sut = new PhotoOperation(service, adapter);
    }

    @Test
    public void onCompute_shouldCancelPreviousSubscription_andCreateNewOne() throws Exception {
        Subscription previousSubscription = mock(Subscription.class);
        sut.subscription = previousSubscription;
        when(service.getPhotos()).thenReturn(mock(Observable.class));

        sut.compute();

        assertNotSame(previousSubscription, sut.subscription);
    }
}