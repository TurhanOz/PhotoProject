package com.turhan.android.photoproject;

import android.support.v7.widget.RecyclerView;

import com.turhan.android.photoproject.rx.PhotoOperation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import rx.Observable;
import rx.Subscription;

import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PhotoControllerTest  {
    @Mock
    PhotoOperation operation;
    @Mock
    RecyclerView recyclerView;
    PhotoController sut;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        sut = new PhotoController(RuntimeEnvironment.application, recyclerView);
    }

    @Test
    public void fetchPhoto_shouldCollaborate_withPhotoOperation() throws Exception {
        sut.operation = operation;

        sut.fetchPhotos();

        verify(operation).compute();
    }
}