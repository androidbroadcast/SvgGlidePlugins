package com.kirich1409.svgloader.glide4;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;

final class UnitSVGDecoder extends SvgDecoder<SVG> {

    @Override
    SVG loadSvg(SVG source, int width, int height, Options options) {
        return source;
    }
}
