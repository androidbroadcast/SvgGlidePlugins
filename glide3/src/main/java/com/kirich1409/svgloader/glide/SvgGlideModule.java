package com.kirich1409.svgloader.glide;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.FileLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorFileLoader;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorResourceLoader;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorStringLoader;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorUriLoader;
import com.bumptech.glide.load.model.stream.HttpUrlGlideUrlLoader;
import com.bumptech.glide.load.model.stream.StreamByteArrayLoader;
import com.bumptech.glide.load.model.stream.StreamFileLoader;
import com.bumptech.glide.load.model.stream.StreamResourceLoader;
import com.bumptech.glide.load.model.stream.StreamStringLoader;
import com.bumptech.glide.load.model.stream.StreamUriLoader;
import com.bumptech.glide.load.model.stream.StreamUrlLoader;
import com.bumptech.glide.module.GlideModule;
import com.caverock.androidsvg.SVG;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public final class SvgGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(File.class, ParcelFileDescriptor.class, new FileDescriptorFileLoader.Factory());
        glide.register(File.class, InputStream.class, new StreamFileLoader.Factory());
        glide.register(int.class, ParcelFileDescriptor.class, new FileDescriptorResourceLoader.Factory());
        glide.register(int.class, InputStream.class, new StreamResourceLoader.Factory());
        glide.register(Integer.class, ParcelFileDescriptor.class, new FileDescriptorResourceLoader.Factory());
        glide.register(Integer.class, InputStream.class, new StreamResourceLoader.Factory());
        glide.register(String.class, ParcelFileDescriptor.class, new FileDescriptorStringLoader.Factory());
        glide.register(String.class, InputStream.class, new StreamStringLoader.Factory());
        glide.register(Uri.class, ParcelFileDescriptor.class, new FileDescriptorUriLoader.Factory());
        glide.register(Uri.class, InputStream.class, new StreamUriLoader.Factory());
        glide.register(URL.class, InputStream.class, new StreamUrlLoader.Factory());
        glide.register(GlideUrl.class, InputStream.class, new HttpUrlGlideUrlLoader.Factory());
        glide.register(byte[].class, InputStream.class, new StreamByteArrayLoader.Factory());
    }
}
