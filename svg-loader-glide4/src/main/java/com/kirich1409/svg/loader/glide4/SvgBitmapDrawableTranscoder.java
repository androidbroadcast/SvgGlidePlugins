package com.kirich1409.svg.loader.glide4;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.LazyBitmapDrawableResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.caverock.androidsvg.SVG;
import com.kirich1409.svg.loader.core.SvgUtils;

class SvgBitmapDrawableTranscoder implements ResourceTranscoder<SVG, BitmapDrawable> {

    @NonNull
    private final BitmapPool mBitmapPool;

    @NonNull
    private final Resources mResources;

    SvgBitmapDrawableTranscoder(@NonNull Context context, @NonNull Glide glide) {
        mResources = context.getResources();
        mBitmapPool = glide.getBitmapPool();
    }

    @Override
    public Resource<BitmapDrawable> transcode(
            @NonNull Resource<SVG> toTranscode,
            @Nullable Options options
    ) {
        SvgUtils.fix(toTranscode.get());
        scaleDocumentSize(toTranscode);
        Bitmap bitmap = toBitmap(toTranscode.get());
        return LazyBitmapDrawableResource.obtain(mResources, mBitmapPool, bitmap);
    }

    @NonNull
    private Bitmap toBitmap(@NonNull SVG svg) {
        int outImageWidth = Math.round(svg.getDocumentWidth());
        int outImageHeight = Math.round(svg.getDocumentHeight());
        Bitmap bitmap = mBitmapPool.get(outImageWidth, outImageHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        svg.renderToCanvas(canvas);
        return bitmap;
    }

    private static void scaleDocumentSize(@NonNull Resource<SVG> toTranscode) {
        if (toTranscode instanceof SvgResource) {
            SvgResource svgResource = (SvgResource) toTranscode;
            SVG svg = toTranscode.get();
            float scale = Math.min(
                    svgResource.getWidth() / svg.getDocumentWidth(),
                    svgResource.getHeight() / svg.getDocumentHeight()
            );
            if (scale >= 0) {
                svg.setDocumentWidth(svg.getDocumentWidth() * scale);
                svg.setDocumentHeight(svg.getDocumentHeight() * scale);
            }
        }
    }
}
