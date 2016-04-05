package com.turhan.android.photoproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.turhan.android.photoproject.R;
import com.turhan.android.photoproject.model.Photo;

import java.util.ArrayList;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private ArrayList<Photo> photos;
    private Context context;

    public PhotoAdapter(Context context, ArrayList<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.photo_row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Photo photo = photos.get(i);
        viewHolder.tv_title.setText(photo.getTitle());
        Glide.with(context)
                .load(photo.getThumbnailUrl())
                .placeholder(R.drawable.waiting)
                .error(R.drawable.error)
                .into(viewHolder.iv_thumbnail);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_thumbnail;

        public ViewHolder(View view) {
            super(view);

            tv_title = (TextView) view.findViewById(R.id.tv_android);
            iv_thumbnail = (ImageView) view.findViewById(R.id.img_android);
        }
    }
}