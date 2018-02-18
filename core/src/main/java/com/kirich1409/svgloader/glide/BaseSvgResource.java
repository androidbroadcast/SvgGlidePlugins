package com.kirich1409.svgloader.glide;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.caverock.androidsvg.SVG;

@RestrictTo(RestrictTo.Scope.LIBRARY)
abstract class BaseSvgResource {

    private final SVG mSvg;
    private final int mWidth;
    private final int mHeight;
    private final int mSize;

    BaseSvgResource(
            @NonNull SVG svg,
            @IntRange(from = 1) int width,
            @IntRange(from = 1) int height,
            @IntRange(from = 0) int size
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

    /**
     * Returns the size in bytes of the wrapped resource to use to determine how much of the memory
     * cache this resource uses.
     */
    public SVG get() {
        return mSvg;
    }

    public int getSize() {
        return mSize;
    }

    public void recycle() {
    }

    public String toString() {
        return getClass().getSimpleName() +
                "{width=" + mWidth + ',' +
                " height=" + mHeight + ',' +
                " size=" + mSize + '}';
    }
}
