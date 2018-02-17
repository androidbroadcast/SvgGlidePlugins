package com.kirich1409.svgloader.glide;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

abstract class SvgDecoder<T> implements ResourceDecoder<T, SVG> {

    @Override
    public Resource<SVG> decode(T source, int width, int height) throws IOException {
        try {
            SVG svg = loadSvg(source, width, height);
            int sourceSize = getSize(source);
            return new SvgResource(svg, width, height, sourceSize);
        } catch (SVGParseException e) {
            throw new IOException("Cannot load SVG from", e);
        }
    }

    @IntRange(from = 0)
    protected abstract int getSize(@NonNull T source) throws IOException;

    abstract SVG loadSvg(T source, int width, int height) throws SVGParseException;
}
