package com.kirich1409.svgloader.glide.utils;

import android.graphics.Bitmap;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public interface BitmapProvider {

    @NonNull
    Bitmap get(
            @IntRange(from = 0) int width,
            @IntRange(from = 0) int height,
            @NonNull Bitmap.Config config
    );
}
