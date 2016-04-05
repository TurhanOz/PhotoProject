package com.turhan.android.photoproject.rest;

import com.turhan.android.photoproject.rest.response.PhotosResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface PhotoService {
    @GET("/photos")
    Observable<PhotosResponse> getPhotos();
}
