package com.kirich1409.svgloader.glide3;

import android.support.annotation.NonNull;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.InputStream;

public final class InputStreamSvgDecoder extends SvgDecoder<InputStream> {

    @Override
    SVG loadSvg(@NonNull InputStream source, int width, int height) throws SVGParseException {
        return SVG.getFromInputStream(source);
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide3.InputStreamSvgDecoder";
    }
}