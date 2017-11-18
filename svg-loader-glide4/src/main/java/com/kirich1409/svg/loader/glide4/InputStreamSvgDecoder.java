package com.kirich1409.svg.loader.glide4;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.InputStream;

class InputStreamSvgDecoder extends SvgDecoder<InputStream> {

    @Override
    SVG loadSvg(@NonNull InputStream source, int width, int height, Options options)
            throws SVGParseException {
        return SVG.getFromInputStream(source);
    }
}