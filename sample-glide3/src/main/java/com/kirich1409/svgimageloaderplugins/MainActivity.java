package com.kirich1409.svgimageloaderplugins;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.kirich1409.svgloader.glide.InputStreamSvgDecoder;
import com.kirich1409.svgloader.glide.SvgGlideDrawableTranscoder;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.sample_image);

        Glide.with(this)
                .using(Glide.buildStreamModelLoader(String.class, this), InputStream.class)
                .from(String.class)
                .as(SVG.class)
                .transcode(new SvgGlideDrawableTranscoder(this), GlideDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<>(new InputStreamSvgDecoder()))
                .decoder(new InputStreamSvgDecoder())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load("http://thenewcode.com/assets/images/thumbnails/homer-simpson.svg")
                .into(imageView);
    }
}
