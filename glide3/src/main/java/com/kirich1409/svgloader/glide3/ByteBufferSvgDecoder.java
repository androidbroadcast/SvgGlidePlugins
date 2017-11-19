package com.kirich1409.svgloader.glide3;

import android.support.annotation.NonNull;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class ByteBufferSvgDecoder extends SvgDecoder<ByteBuffer> {

    @Override
    SVG loadSvg(ByteBuffer source, int width, int height) throws SVGParseException {
        InputStream is = null;
        try {
            try {
                is = new ByteBufferInputStream(source);
                return SVG.getFromInputStream(is);
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        } catch (IOException e) {
            throw new SVGParseException(e);
        }
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide3.ByteBufferSvgDecoder";
    }

    @Override
    protected int getSize(@NonNull ByteBuffer source) {
        return source.limit();
    }
}