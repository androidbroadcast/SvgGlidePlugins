package com.kirich1409.svg.loader.glide4;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class FileSvgDecoder extends SvgDecoder<File> {

    @Override
    public boolean handles(@NonNull File source, Options options) throws IOException {
        return source.getName().endsWith(".svg");
    }

    @Override
    SVG loadSvg(@NonNull File source, int width, int height, Options options)
            throws SVGParseException {
        InputStream sourceInputStream = null;
        try {
            try {
                sourceInputStream = new BufferedInputStream(new FileInputStream(source));
                return SVG.getFromInputStream(sourceInputStream);
            } finally {
                if (sourceInputStream != null) {
                    sourceInputStream.close();
                }
            }
        } catch (IOException e) {
            throw new SVGParseException(e);
        }
    }
}