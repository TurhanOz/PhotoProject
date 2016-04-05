package com.turhan.android.photoproject.rx;

import com.turhan.android.photoproject.BuildConfig;
import com.turhan.android.photoproject.adapter.PhotoAdapter;
import com.turhan.android.photoproject.rest.response.PhotosResponse;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PhotoObserverTest {
    @Mock
    PhotoAdapter adapter;
    @Mock
    ArrayList photos;

    PhotoObserver sut;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        sut = new PhotoObserver(adapter, photos);

    }

    @Test
    public void onComplete_shouldNotify_adapterDataSetChanged() throws Exception {
        sut.onCompleted();

        verify(adapter).notifyDataSetChanged();
    }

    @Test
    public void onNext_shouldClearAndUpdate_list() throws Exception {
        PhotosResponse response = mock(PhotosResponse.class);

        sut.onNext(response);

        verify(photos).clear();
        verify(photos).addAll(response);

    }
}