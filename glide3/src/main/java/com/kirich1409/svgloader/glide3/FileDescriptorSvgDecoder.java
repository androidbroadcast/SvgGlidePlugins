package com.kirich1409.svgloader.glide3;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.kirich1409.svg.loader.core.SvgUtils;

import java.io.FileDescriptor;
import java.io.IOException;

public final class FileDescriptorSvgDecoder extends SvgDecoder<FileDescriptor> {

    @Override
    SVG loadSvg(FileDescriptor source, int width, int height) throws SVGParseException {
        try {
            return SvgUtils.getSvg(source);
        } catch (IOException e) {
            throw new SVGParseException(e);
        }
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide3.FileDescriptorSvgDecoder";
    }
}