package com.kirich1409.svgloader.glide;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.engine.Resource;
import com.caverock.androidsvg.SVG;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class SvgResource extends BaseSvgResource implements Resource<SVG> {

    SvgResource(
            @NonNull SVG svg,
            @IntRange(from = 1) int width,
            @IntRange(from = 1) int height,
            @IntRange(from = 0) int size
    ) {
        super(svg, width, height, size);
    }
}
