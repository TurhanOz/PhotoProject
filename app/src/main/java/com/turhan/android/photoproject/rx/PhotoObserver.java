package com.turhan.android.photoproject.rx;

import android.util.Log;

import com.turhan.android.photoproject.adapter.PhotoAdapter;
import com.turhan.android.photoproject.rest.response.PhotosResponse;

import java.io.File;
import java.util.ArrayList;

import rx.Observer;

public class PhotoObserver implements Observer<PhotosResponse> {
    PhotoAdapter adapter;
    ArrayList photos;

    public PhotoObserver(PhotoAdapter adapter, ArrayList photos) {
        this.adapter = adapter;
        this.photos = photos;
    }

    @Override
    public void onCompleted() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(PhotosResponse response) {
        photos.clear();
        photos.addAll(response);
    }
}
