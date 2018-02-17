package com.kirich1409.svgloader.glide;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class UnitSVGDecoder extends SvgDecoder<SVG> {

    @Override
    SVG loadSvg(SVG source, int width, int height, @NonNull Options options) {
        return source;
    }

    @Override
    protected int getSize(@NonNull SVG source) {
        return 0;
    }
}
