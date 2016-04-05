package com.turhan.android.photoproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivityFragment extends Fragment {
    PhotoController controller;
    RecyclerView recyclerView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initRecyclerView(rootView);
        initController();
        return rootView;
    }

    private void initController() {
        controller = new PhotoController(getActivity(), recyclerView);
    }

    private void initRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(), getString(R.string.update_in_progress), Toast.LENGTH_SHORT).show();
        controller.fetchPhotos();
    }

    @Override
    public void onStop() {
        super.onStop();
        controller.cancelFetchingPhotos();
    }
}
