package com.kirich1409.svgloader.glide;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class ByteBufferSvgDecoder extends SvgDecoder<ByteBuffer> {

    @Override
    SVG loadSvg(ByteBuffer source, int width, int height) throws SVGParseException {
        try (InputStream is = new ByteBufferInputStream(source)) {
            return SVG.getFromInputStream(is);
        } catch (IOException e) {
            throw new SVGParseException(e);
        }
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide.ByteBufferSvgDecoder";
    }

    @Override
    protected int getSize(@NonNull ByteBuffer source) {
        return SizeUtils.getSize(source);
    }
}
