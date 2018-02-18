package com.kirich1409.svgimageloaderplugins;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {

    private static final String HOMER_URL = "http://thenewcode.com/assets/images/thumbnails/homer-simpson.svg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.sample_image);

        GlideApp.with(this)
                .load(R.raw.bart_simpson)
                .apply(GlideOptions.fitCenterTransform())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);
    }
}
