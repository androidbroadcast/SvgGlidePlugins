package com.kirich1409.svg.loader.glide4;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

final class StringSvgDecoder extends SvgDecoder<String> {

    @Override
    SVG loadSvg(String source, int width, int height, Options options) throws SVGParseException {
        return SVG.getFromString(source);
    }
}