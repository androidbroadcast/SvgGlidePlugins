package com.kirich1409.svgloader.glide4;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

final class StringSvgDecoder extends SvgDecoder<String> {

    @Override
    SVG loadSvg(String source, int width, int height, Options options) throws SVGParseException {
        return SVG.getFromString(source);
    }

    @Override
    protected int getSize(@NonNull String source) {
        return source.length() * 2;
    }
}