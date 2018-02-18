package com.kirich1409.svgloader.glide;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class StringSvgDecoder extends SvgDecoder<String> {

    @Override
    SVG loadSvg(String source, int width, int height, @NonNull Options options)
            throws SVGParseException {
        return SVG.getFromString(source);
    }

    @Override
    protected int getSize(@NonNull String source) throws IOException {
        return SizeUtils.getSize(source);
    }
}
