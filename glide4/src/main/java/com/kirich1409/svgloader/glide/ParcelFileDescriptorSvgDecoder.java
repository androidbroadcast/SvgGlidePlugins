package com.kirich1409.svgloader.glide;

import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class ParcelFileDescriptorSvgDecoder extends SvgDecoder<ParcelFileDescriptor> {

    private final FileDescriptorSvgDecoder mDecoder = new FileDescriptorSvgDecoder();

    @Override
    public boolean handles(@NonNull ParcelFileDescriptor source, @NonNull Options options)
            throws IOException {
        return mDecoder.handles(source.getFileDescriptor(), options);
    }

    @Override
    SVG loadSvg(ParcelFileDescriptor source, int width, int height, @NonNull Options options)
            throws SVGParseException {
        return mDecoder.loadSvg(source.getFileDescriptor(), width, height, options);
    }

    @Override
    protected int getSize(@NonNull ParcelFileDescriptor source) throws IOException {
        return SizeUtils.getSize(source);
    }
}
