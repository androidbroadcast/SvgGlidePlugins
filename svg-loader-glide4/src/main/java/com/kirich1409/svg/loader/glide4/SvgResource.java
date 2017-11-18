package com.kirich1409.svg.loader.glide4;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.resource.SimpleResource;
import com.caverock.androidsvg.SVG;

final class SvgResource extends SimpleResource<SVG> {

    private final int mWidth;
    private final int mHeight;

    SvgResource(
            @NonNull SVG data,
            @IntRange(from = 1) int width,
            @IntRange(from = 1) int height
    ) {
        super(data);
        mWidth = width;
        mHeight = height;
    }

    @IntRange(from = 1)
    int getWidth() {
        return mWidth;
    }

    @IntRange(from = 1)
    int getHeight() {
        return mHeight;
    }

    @Override
    public String toString() {
        return "SvgResource{width=" + mWidth + ", height=" + mHeight + '}';
    }
}
