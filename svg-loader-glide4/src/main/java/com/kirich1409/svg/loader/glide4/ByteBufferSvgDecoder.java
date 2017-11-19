package com.kirich1409.svg.loader.glide4;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.util.ByteBufferUtil;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

final class ByteBufferSvgDecoder extends SvgDecoder<ByteBuffer> {

    @Override
    SVG loadSvg(ByteBuffer source, int width, int height, Options options) throws SVGParseException {
        InputStream is = null;
        try {
            try {
                is = ByteBufferUtil.toStream(source);
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
}