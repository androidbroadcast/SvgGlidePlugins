package com.kirich1409.svg.loader.glide4;

import android.support.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class FileDescriptorSvgDecoder extends SvgDecoder<FileDescriptor> {

    @Override
    SVG loadSvg(@NonNull FileDescriptor source, int width, int height, Options options)
            throws SVGParseException {
        InputStream fis = null;
        try {
            try {
                fis = new BufferedInputStream(new FileInputStream(source));
                return SVG.getFromInputStream(fis);
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } catch (IOException e) {
            throw new SVGParseException(e);
        }
    }
}