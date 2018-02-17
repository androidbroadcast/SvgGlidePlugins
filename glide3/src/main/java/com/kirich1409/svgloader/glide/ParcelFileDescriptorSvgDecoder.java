package com.kirich1409.svgloader.glide;

import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.FileDescriptor;
import java.io.IOException;

public final class ParcelFileDescriptorSvgDecoder extends SvgDecoder<ParcelFileDescriptor> {

    private final SvgDecoder<FileDescriptor> mDecoder = new FileDescriptorSvgDecoder();

    @Override
    SVG loadSvg(ParcelFileDescriptor source, int width, int height) throws SVGParseException {
        return mDecoder.loadSvg(source.getFileDescriptor(), width, height);
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide.ParcelFileDescriptorSvgDecoder";
    }

    @Override
    protected int getSize(@NonNull ParcelFileDescriptor source) throws IOException {
        return SizeUtils.getSize(source);
    }
}
