package com.kirich1409.svgloader.glide;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class SizeUtils {

    public static int getSize(@NonNull ByteBuffer buffer) {
        return buffer.limit();
    }

    public static int getSize(@NonNull FileDescriptor source) throws IOException {
        try (FileInputStream fis = new FileInputStream(source)) {
            try (FileChannel channel = fis.getChannel()) {
                return (int) channel.size();
            }
        }
    }

    public static int getSize(@NonNull ParcelFileDescriptor source) throws IOException {
        return SizeUtils.getSize(source.getFileDescriptor());
    }

    public static int getSize(@NonNull AssetFileDescriptor source) throws IOException {
        return SizeUtils.getSize(source.getFileDescriptor());
    }

    public static int getRawResourceSize(@NonNull Resources resources, @NonNull Uri source) throws IOException {
        int rawResourceId = ResourceUtils.getRawResourceId(resources, source);
        try {
            return SizeUtils.getSize(resources.openRawResourceFd(rawResourceId));
        } catch (Resources.NotFoundException e) {
            throw new IOException(e);
        }
    }

    public static int getSize(String string, String encoding) throws IOException {
        try {
            return string.getBytes(encoding).length;
        } catch (UnsupportedEncodingException e) {
            throw new IOException(e);
        }
    }

    public static int getSize(String string) throws IOException {
        return getSize(string, "UTF-8");
    }

    public static int getSize(InputStream stream) throws IOException {
        return stream.available();
    }

    private SizeUtils() {
    }
}
