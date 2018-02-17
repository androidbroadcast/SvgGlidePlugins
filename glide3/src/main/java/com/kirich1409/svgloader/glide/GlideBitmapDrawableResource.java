package com.kirich1409.svgloader.glide;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.util.Util;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class GlideBitmapDrawableResource implements Resource<GlideDrawable> {

    private final GlideBitmapDrawable mDrawable;
    private final int mSize;
    private final BitmapPool mBitmapPool;

    GlideBitmapDrawableResource(
            @NonNull GlideBitmapDrawable drawable,
            @NonNull BitmapPool bitmapPool
    ) {
        mDrawable = drawable;
        mSize = Util.getBitmapByteSize(drawable.getBitmap());
        mBitmapPool = bitmapPool;
    }

    @Override
    public GlideBitmapDrawable get() {
        return mDrawable;
    }

    @Override
    public int getSize() {
        return mSize;
    }

    @Override
    public void recycle() {
        mBitmapPool.put(mDrawable.getBitmap());
    }
}
