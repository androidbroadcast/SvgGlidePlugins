package com.kirich1409.svgloader.glide4;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.model.AssetUriLoader;
import com.bumptech.glide.load.model.ByteArrayLoader;
import com.bumptech.glide.load.model.ByteBufferFileLoader;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ResourceLoader;
import com.bumptech.glide.load.model.StringLoader;
import com.bumptech.glide.load.model.UnitModelLoader;
import com.bumptech.glide.load.model.UriLoader;
import com.bumptech.glide.load.model.UrlUriLoader;
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader;
import com.bumptech.glide.load.model.stream.HttpUriLoader;
import com.bumptech.glide.load.model.stream.UrlLoader;
import com.bumptech.glide.module.LibraryGlideModule;
import com.caverock.androidsvg.SVG;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

@GlideModule
public class SvgModule extends LibraryGlideModule {

    @SuppressWarnings("WeakerAccess")
    public static final String REGISTRY_SVG = "SVG";

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.register(SVG.class, BitmapDrawable.class, new SvgBitmapDrawableTranscoder(context, glide));
        registerLoaders(context, glide, registry);
        registerDecoders(context, registry);
    }

    private void registerLoaders(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        ResourceLoader.StreamFactory resourceLoaderStreamFactory =
                new ResourceLoader.StreamFactory(context.getResources());

        ResourceLoader.FileDescriptorFactory resourceLoaderFileDescriptorFactory =
                new ResourceLoader.FileDescriptorFactory(context.getResources());

        ResourceLoader.UriFactory resourceLoaderUriFactory =
                new ResourceLoader.UriFactory(context.getResources());

        registry.append(File.class, ByteBuffer.class, new ByteBufferFileLoader.Factory())
                .append(File.class, InputStream.class, new FileLoader.StreamFactory())
                .append(File.class, ParcelFileDescriptor.class, new FileLoader.FileDescriptorFactory())
                .append(File.class, File.class, UnitModelLoader.Factory.<File>getInstance())

                .append(Uri.class, InputStream.class, new AssetUriLoader.StreamFactory(context.getAssets()))
                .append(Uri.class, InputStream.class, new HttpUriLoader.Factory())
                .append(Uri.class, InputStream.class, new UrlUriLoader.StreamFactory())
                .append(Uri.class, ParcelFileDescriptor.class, new AssetUriLoader.FileDescriptorFactory(context.getAssets()))
                .append(Uri.class, ParcelFileDescriptor.class, new UriLoader.FileDescriptorFactory(context.getContentResolver()))
                .append(Uri.class, Uri.class, UnitModelLoader.Factory.<Uri>getInstance())

                .append(int.class, InputStream.class, resourceLoaderStreamFactory)
                .append(Integer.class, InputStream.class, resourceLoaderStreamFactory)
                .append(int.class, ParcelFileDescriptor.class, resourceLoaderFileDescriptorFactory)
                .append(Integer.class, ParcelFileDescriptor.class, resourceLoaderFileDescriptorFactory)
                .append(int.class, Uri.class, resourceLoaderUriFactory)
                .append(Integer.class, Uri.class, resourceLoaderUriFactory)

                .append(GlideUrl.class, InputStream.class, new HttpGlideUrlLoader.Factory())

                .append(URL.class, InputStream.class, new UrlLoader.StreamFactory())

                .append(byte[].class, ByteBuffer.class, new ByteArrayLoader.ByteBufferFactory())
                .append(byte[].class, InputStream.class, new ByteArrayLoader.StreamFactory())

                .append(String.class, InputStream.class, new StringLoader.StreamFactory())
                .append(String.class, InputStream.class, new SvgDataUrlLoader.StreamFactory())
                .append(String.class, ParcelFileDescriptor.class, new StringLoader.FileDescriptorFactory())
                .append(String.class, String.class, UnitModelLoader.Factory.<String>getInstance())

                .append(SVG.class, SVG.class, UnitModelLoader.Factory.<SVG>getInstance());
    }

    private void registerDecoders(@NonNull Context context, @NonNull Registry registry) {
        registry.append(REGISTRY_SVG, InputStream.class, SVG.class, new InputStreamSvgDecoder())
                .append(REGISTRY_SVG, File.class, SVG.class, new FileSvgDecoder())
                .append(REGISTRY_SVG, FileDescriptor.class, SVG.class, new FileDescriptorSvgDecoder())
                .append(REGISTRY_SVG, ParcelFileDescriptor.class, SVG.class, new ParcelFileDescriptorSvgDecoder())
                .append(REGISTRY_SVG, SVG.class, SVG.class, new UnitSVGDecoder())
                .append(REGISTRY_SVG, ByteBuffer.class, SVG.class, new ByteBufferSvgDecoder())
                .append(REGISTRY_SVG, String.class, SVG.class, new StringSvgDecoder())
                .append(REGISTRY_SVG, Uri.class, SVG.class, new RawResourceSvgDecoder(context));
    }
}
