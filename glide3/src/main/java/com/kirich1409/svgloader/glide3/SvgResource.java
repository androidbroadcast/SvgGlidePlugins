package com.kirich1409.svgloader.glide3;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.Resource;
import com.caverock.androidsvg.SVG;

final class SvgResource extends com.kirich1409.svg.loader.core.SvgResource implements Resource<SVG> {

    SvgResource(
            @NonNull SVG svg,
            @IntRange(from = 1) int width,
            @IntRange(from = 1) int height,
            @IntRange(from = 1) int size
    ) {
        super(svg, width, height, size);
    }
}
