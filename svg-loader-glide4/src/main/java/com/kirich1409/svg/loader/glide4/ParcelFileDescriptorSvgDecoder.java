package com.kirich1409.svg.loader.glide4;

import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

final class ParcelFileDescriptorSvgDecoder extends SvgDecoder<ParcelFileDescriptor> {

    private final FileDescriptorSvgDecoder mDecoder = new FileDescriptorSvgDecoder();

    @Override
    public boolean handles(@NonNull ParcelFileDescriptor source, Options options) throws IOException {
        return mDecoder.handles(source.getFileDescriptor(), options);
    }

    @Override
    SVG loadSvg(ParcelFileDescriptor source, int width, int height, Options options) throws SVGParseException {
        return mDecoder.loadSvg(source.getFileDescriptor(), width, height, options);
    }
}