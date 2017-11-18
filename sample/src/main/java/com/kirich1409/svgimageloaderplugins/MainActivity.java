package com.kirich1409.svgimageloaderplugins;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.sample_image);

        GlideApp.with(this)
                .load(Uri.parse("http://thenewcode.com/assets/images/thumbnails/homer-simpson.svg"))
                .apply(GlideOptions.fitCenterTransform())
                .into(imageView);
    }
}
