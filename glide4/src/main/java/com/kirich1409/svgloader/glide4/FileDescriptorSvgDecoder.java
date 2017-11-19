package com.kirich1409.svgloader.glide4;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.kirich1409.svg.loader.core.SvgUtils;

import java.io.FileDescriptor;
import java.io.IOException;

class FileDescriptorSvgDecoder extends SvgDecoder<FileDescriptor> {

    @Override
    SVG loadSvg(@NonNull FileDescriptor source, int width, int height, Options options)
            throws SVGParseException {
        try {
            return SvgUtils.getSvg(source);
        } catch (IOException e) {
            throw new SVGParseException(e);
        }
    }
}