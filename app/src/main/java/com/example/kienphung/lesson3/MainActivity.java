package com.example.kienphung.lesson3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int READ_EXTERNAL_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        initView();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //display dialog xin allow permission and result to onRequestPermission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE);
        }
    }

    private void initView() {
        RecyclerView recyclerViewImages = findViewById(R.id.recycler_view);
        ArrayList<Image> images = getData();
        recyclerViewImages.setLayoutManager(new GridLayoutManager(this, 2));
        ImageAdapter imageAdapter = new ImageAdapter(this, images);
        recyclerViewImages.setAdapter(imageAdapter);
    }

    private ArrayList<Image> getData() {
        ArrayList<Image> images = new ArrayList<>();
        File downloadsFolder =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Image image;
        if (downloadsFolder.exists()) {
            File[] files = downloadsFolder.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                image = new Image();
                image.setUri(Uri.fromFile(file));
                images.add(image);
            }
        }
        return images;
    }
}
