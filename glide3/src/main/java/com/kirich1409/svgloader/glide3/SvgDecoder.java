package com.kirich1409.svgloader.glide3;

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
            return new SvgResource(svg, width, height, getSize(source));
        } catch (SVGParseException e) {
            throw new IOException("Cannot load SVG from", e);
        }
    }

    @IntRange(from = 1)
    protected int getSize(@NonNull T source) {
        return 1;
    }

    abstract SVG loadSvg(T source, int width, int height) throws SVGParseException;
}