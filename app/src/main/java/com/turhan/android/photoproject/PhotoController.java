package com.turhan.android.photoproject;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.turhan.android.photoproject.adapter.PhotoAdapter;
import com.turhan.android.photoproject.rest.PhotoService;
import com.turhan.android.photoproject.rest.RetrofitClient;
import com.turhan.android.photoproject.rest.response.PhotosResponse;
import com.turhan.android.photoproject.rx.PhotoOperation;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PhotoController {
    RetrofitClient client;
    PhotoOperation operation;
    PhotoAdapter adapter;


    public PhotoController(Context context, RecyclerView recyclerView) {
        initRecyclerView(context, recyclerView);
        initRetrofit();
        operation = new PhotoOperation(client.getPhotoService(), adapter);
    }

    private void initRecyclerView(Context context, RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PhotoAdapter(context, new ArrayList());
        recyclerView.setAdapter(adapter);
    }

    private void initRetrofit() {
        client = new RetrofitClient();
    }

    public void fetchPhotos() {
        operation.compute();
    }
}
