package com.kirich1409.svgloader.glide3;

import android.os.ParcelFileDescriptor;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

public final class ParcelFileDescriptorSvgDecoder extends SvgDecoder<ParcelFileDescriptor> {

    private final FileDescriptorSvgDecoder mDecoder = new FileDescriptorSvgDecoder();

    @Override
    SVG loadSvg(ParcelFileDescriptor source, int width, int height) throws SVGParseException {
        return mDecoder.loadSvg(source.getFileDescriptor(), width, height);
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide3.ParcelFileDescriptorSvgDecoder";
    }
}