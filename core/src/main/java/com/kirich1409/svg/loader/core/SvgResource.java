package com.kirich1409.svg.loader.core;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.caverock.androidsvg.SVG;

public class SvgResource {

    private final SVG mSvg;
    private final int mWidth;
    private final int mHeight;
    private final int mSize;

    protected SvgResource(
            @NonNull SVG svg,
            @IntRange(from = 1) int width,
            @IntRange(from = 1) int height,
            @IntRange(from = 1) int size
    ) {
        SvgUtils.fix(svg);
        mSvg = svg;
        mWidth = width;
        mHeight = height;
        mSize = size;
    }

    @IntRange(from = 1)
    public int getWidth() {
        return mWidth;
    }

    @IntRange(from = 1)
    public int getHeight() {
        return mHeight;
    }

    public Class<SVG> getResourceClass() {
        return SVG.class;
    }

    public SVG get() {
        return mSvg;
    }

    public int getSize() {
        return mSize;
    }

    public void recycle() {
    }

    public String toString() {
        return "SvgResource{ width=" + mWidth + ", height=" + mHeight + ", size=" + mSize + '}';
    }
}