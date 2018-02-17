package com.kirich1409.svgloader.glide;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public final class SvgUtils {

    public static void fix(@NonNull SVG svg) {
        if (svg.getDocumentViewBox() == null) {
            svg.setDocumentViewBox(0F, 0F, svg.getDocumentWidth(), svg.getDocumentHeight());
        }
    }

    public static SVG getSvg(@NonNull File file) throws SVGParseException, IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File: '" + file.getAbsolutePath() + "' not exists");
        }

        InputStream sourceInputStream = null;
        try {
            sourceInputStream = new BufferedInputStream(new FileInputStream(file));
            return SVG.getFromInputStream(sourceInputStream);
        } finally {
            if (sourceInputStream != null) {
                sourceInputStream.close();
            }
        }
    }

    public static SVG getSvg(@NonNull FileDescriptor descriptor) throws SVGParseException, IOException {
        InputStream inputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(descriptor));
            return SVG.getFromInputStream(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void scaleDocumentSize(@NonNull BaseSvgResource resource) {
        SVG svg = resource.get();
        float scale = Math.min(
                resource.getWidth() / svg.getDocumentWidth(),
                resource.getHeight() / svg.getDocumentHeight()
        );
        if (scale >= 0) {
            svg.setDocumentWidth(svg.getDocumentWidth() * scale);
            svg.setDocumentHeight(svg.getDocumentHeight() * scale);
        }
    }

    @NonNull
    public static Bitmap toBitmap(@NonNull SVG svg, @NonNull BitmapProvider provider) {
        int outImageWidth = Math.round(svg.getDocumentWidth());
        int outImageHeight = Math.round(svg.getDocumentHeight());
        Bitmap bitmap = provider.get(outImageWidth, outImageHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        svg.renderToCanvas(canvas);
        return bitmap;
    }

    public interface BitmapProvider {

        @NonNull
        Bitmap get(@IntRange(from = 0) int width, @IntRange(from = 0) int height, @NonNull Bitmap.Config config);
    }

    private SvgUtils() {
    }
}
