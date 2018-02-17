package com.kirich1409.svgloader.glide;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.FileDescriptor;
import java.io.IOException;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class FileDescriptorSvgDecoder extends SvgDecoder<FileDescriptor> {

    @Override
    SVG loadSvg(@NonNull FileDescriptor source, int width, int height, @NonNull Options options)
            throws SVGParseException {
        try {
            return SvgUtils.getSvg(source);
        } catch (IOException e) {
            throw new SVGParseException(e);
        }
    }

    @Override
    protected int getSize(@NonNull FileDescriptor source) throws IOException {
        return SizeUtils.getSize(source);
    }
}
