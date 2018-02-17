package com.kirich1409.svgloader.glide;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.File;
import java.io.IOException;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class FileSvgDecoder extends SvgDecoder<File> {

    @Override
    SVG loadSvg(File source, int width, int height) throws SVGParseException {
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

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide.FileSvgDecoder";
    }
}
