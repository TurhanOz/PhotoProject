package com.turhan.android.photoproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.turhan.android.photoproject.adapter.PhotoAdapter;
import com.turhan.android.photoproject.rest.RetrofitClient;
import com.turhan.android.photoproject.rx.PhotoOperation;

import java.util.ArrayList;

public class PhotoController {
    RetrofitClient client;
    PhotoOperation operation;
    PhotoAdapter adapter;

    public PhotoController(Context context, RecyclerView recyclerView) {
        initAdapter(context, recyclerView);
        initRetrofit(context);
        operation = new PhotoOperation(client.getPhotoService(), adapter);
    }

    private void initAdapter(Context context, RecyclerView recyclerView) {
        adapter = new PhotoAdapter(context, new ArrayList());
        recyclerView.setAdapter(adapter);
    }

    private void initRetrofit(Context context) {
        client = new RetrofitClient(context);
    }

    public void fetchPhotos() {
        operation.compute();
    }

    public void cancelFetchingPhotos(){
        operation.cancelPreviousOperation();
    }
}
