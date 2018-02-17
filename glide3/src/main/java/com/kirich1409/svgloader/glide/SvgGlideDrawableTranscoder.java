package com.kirich1409.svgloader.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.caverock.androidsvg.SVG;

@RestrictTo(RestrictTo.Scope.LIBRARY)
class SvgGlideDrawableTranscoder implements ResourceTranscoder<SVG, GlideDrawable> {

    private final Resources mResources;
    private final BitmapPool mBitmapPool;
    private final SvgUtils.BitmapProvider mBitmapProvider;

    public SvgGlideDrawableTranscoder(@NonNull Context context) {
        this(context.getResources(), Glide.get(context).getBitmapPool());
    }

    SvgGlideDrawableTranscoder(@NonNull Resources resources, @NonNull BitmapPool bitmapPool) {
        mResources = resources;
        mBitmapPool = bitmapPool;
        mBitmapProvider = new PoolBitmapProvider(bitmapPool);
    }

    @Override
    public Resource<GlideDrawable> transcode(Resource<SVG> toTranscode) {
        if (toTranscode instanceof BaseSvgResource) {
            SvgUtils.scaleDocumentSize((BaseSvgResource) toTranscode);
        }
        Bitmap bitmap = SvgUtils.toBitmap(toTranscode.get(), mBitmapProvider);
        GlideBitmapDrawable drawable = new GlideBitmapDrawable(mResources, bitmap);
        return new GlideBitmapDrawableResource(drawable, mBitmapPool);
    }

    private static final class PoolBitmapProvider implements SvgUtils.BitmapProvider {

        private final BitmapPool mBitmapPool;

        PoolBitmapProvider(@NonNull BitmapPool bitmapPool) {
            mBitmapPool = bitmapPool;
        }

        @NonNull
        @Override
        public Bitmap get(int width, int height, @NonNull Bitmap.Config config) {
            Bitmap bitmap = mBitmapPool.get(width, height, config);
            return bitmap == null ? Bitmap.createBitmap(width, height, config) : bitmap;
        }
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide.SvgGlideDrawableTranscoder";
    }
}
