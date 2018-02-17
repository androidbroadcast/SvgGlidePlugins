package com.kirich1409.svgloader.glide;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

@RestrictTo(RestrictTo.Scope.LIBRARY)
abstract class SvgDecoder<T> implements ResourceDecoder<T, SVG> {

    @Override
    public boolean handles(@NonNull T source, @NonNull Options options) throws IOException {
        return true;
    }

    @Nullable
    @Override
    public Resource<SVG> decode(
            @NonNull T source, int width, int height, @NonNull Options options) throws IOException {
        try {
            SVG svg = loadSvg(source, width, height, options);
            int sourceSize = getSize(source);
            return new SvgResource(svg, width, height, sourceSize);
        } catch (SVGParseException e) {
            throw new IOException("Cannot load SVG", e);
        }
    }

    @IntRange(from = 0)
    protected abstract int getSize(@NonNull T source) throws IOException;

    abstract SVG loadSvg(T source, int width, int height, @NonNull Options options) throws SVGParseException;
}
