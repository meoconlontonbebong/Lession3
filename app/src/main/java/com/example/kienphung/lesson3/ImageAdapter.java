package com.example.kienphung.lesson3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHoder> {
    private Context mContext;
    private ArrayList<Image> mImages;

    public ImageAdapter(Context context, ArrayList<Image> images) {
        mContext = context;
        mImages = images;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(mContext)
                .inflate(R.layout.model_view, viewGroup, false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, int i) {
        Image modelImages = mImages.get(i);
        Picasso.with(mContext)
                .load(modelImages.getUri())
                .placeholder(R.mipmap.ic_launcher)
                .into(viewHoder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImages != null ? mImages.size() : 0;
    }

    static class ViewHoder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img);
        }
    }
}
