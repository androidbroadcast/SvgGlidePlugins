package com.kirich1409.svgloader.glide4;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.kirich1409.svg.loader.core.SvgUtils;

import java.io.File;
import java.io.IOException;

class FileSvgDecoder extends SvgDecoder<File> {

    @Override
    SVG loadSvg(@NonNull File source, int width, int height, Options options)
            throws SVGParseException {
        try {
            return SvgUtils.getSvg(source);
        } catch (IOException e) {
            throw new SVGParseException(e);
        }
    }

    @Override
    protected int getSize(@NonNull File source) {
        return (int) source.length();
    }
}