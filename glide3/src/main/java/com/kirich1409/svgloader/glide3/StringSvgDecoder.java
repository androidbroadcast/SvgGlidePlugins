package com.kirich1409.svgloader.glide3;

import android.support.annotation.NonNull;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

public final class StringSvgDecoder extends SvgDecoder<String> {

    @Override
    SVG loadSvg(String source, int width, int height) throws SVGParseException {
        return SVG.getFromString(source);
    }

    @Override
    protected int getSize(@NonNull String source) {
        return source.length() * 2;
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide3.StringSvgDecoder";
    }
}