package com.turhan.android.photoproject.rx;

import com.turhan.android.photoproject.adapter.PhotoAdapter;
import com.turhan.android.photoproject.model.Photo;
import com.turhan.android.photoproject.rest.PhotoService;
import com.turhan.android.photoproject.rest.response.PhotosResponse;

import java.io.File;
import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PhotoOperation {
    private final PhotoService service;
    private final ArrayList<Photo> photos;
    private final PhotoAdapter adapter;
    Subscription subscription;


    public PhotoOperation(PhotoService service, PhotoAdapter adapter, ArrayList photos) {
        this.service = service;
        this.adapter = adapter;
        this.photos = photos;
    }

    public void compute() {
        cancelPreviousOperation();

        Observable<PhotosResponse> observable = service.getPhotos();
        Observer<PhotosResponse> observer = new PhotoObserver(adapter, photos);

        subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void cancelPreviousOperation() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = null;
    }
}
