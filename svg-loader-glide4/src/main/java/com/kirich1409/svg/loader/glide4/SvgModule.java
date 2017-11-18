package com.kirich1409.svg.loader.glide4;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.load.model.ResourceLoader;
import com.bumptech.glide.load.model.UnitModelLoader;
import com.bumptech.glide.load.model.UrlUriLoader;
import com.bumptech.glide.load.model.stream.HttpUriLoader;
import com.bumptech.glide.module.LibraryGlideModule;
import com.caverock.androidsvg.SVG;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;

@GlideModule
public class SvgModule extends LibraryGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.register(SVG.class, BitmapDrawable.class, new SvgBitmapDrawableTranscoder(context, glide));

        ResourceLoader.StreamFactory resourceStreamLoadFactory = new ResourceLoader.StreamFactory(context.getResources());
        registry.append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets()))
                .append(Uri.class, InputStream.class, new HttpUriLoader.Factory())
                .append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory())
                .append(int.class, InputStream.class, resourceStreamLoadFactory)
                .append(SVG.class, SVG.class, UnitModelLoader.Factory.<SVG>getInstance());

        registry.append(InputStream.class, SVG.class, new InputStreamSvgDecoder())
                .append(File.class, SVG.class, new FileSvgDecoder())
                .append(FileDescriptor.class, SVG.class, new FileDescriptorSvgDecoder())
                .append(SVG.class, SVG.class, new UnitSVGDecoder());
    }
}
