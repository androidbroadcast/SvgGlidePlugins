package com.kirich1409.svgloader.glide;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class SvgUtils {

    static void fix(@NonNull SVG svg) throws IOException {
        RectF viewBox = svg.getDocumentViewBox();
        float docWidth = svg.getDocumentWidth();
        float docHeight = svg.getDocumentHeight();

        if (viewBox == null) {
            if (docWidth > 0 && docHeight > 0) {
                svg.setDocumentViewBox(0F, 0F, docWidth, docHeight);
            } else {
                throw new IOException("SVG must have specify 'width' & 'height' tags or 'viewbox'");
            }

        } else if (docWidth <= 0 && docHeight <= 0) {
            svg.setDocumentWidth(viewBox.width());
            svg.setDocumentHeight(viewBox.height());

        } else if (docWidth <= 0) {
            svg.setDocumentWidth(aspectRation(viewBox) * docHeight);

        } else if (docHeight <= 0) {
            svg.setDocumentHeight(docWidth / aspectRation(viewBox));
        }
    }

    private static float aspectRation(@NonNull RectF rect) {
        return rect.width() / rect.height();
    }

    static SVG getSvg(@NonNull File file) throws SVGParseException, IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File: '" + file.getAbsolutePath() + "' not exists");
        }

        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            return SVG.getFromInputStream(is);
        }
    }

    static SVG getSvg(@NonNull FileDescriptor descriptor)
            throws SVGParseException, IOException {

        try (InputStream is = new BufferedInputStream(new FileInputStream(descriptor))) {
            return SVG.getFromInputStream(is);
        }
    }

    static void scaleDocumentSize(
            @NonNull SVG svg,
            @FloatRange(from = 0, fromInclusive = false) float scale
    ) {
        svg.setDocumentWidth(svg.getDocumentWidth() * scale);
        svg.setDocumentHeight(svg.getDocumentHeight() * scale);
    }

    @NonNull
    static Bitmap toBitmap(
            @NonNull SVG svg,
            @NonNull BitmapProvider provider,
            @NonNull Bitmap.Config config
    ) {
        int outImageWidth = Math.round(svg.getDocumentWidth());
        int outImageHeight = Math.round(svg.getDocumentHeight());
        Bitmap bitmap = provider.get(outImageWidth, outImageHeight, config);
        Canvas canvas = new Canvas(bitmap);
        svg.renderToCanvas(canvas);
        return bitmap;
    }

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    interface BitmapProvider {

        @NonNull
        Bitmap get(
                @IntRange(from = 0) int width,
                @IntRange(from = 0) int height,
                @NonNull Bitmap.Config config
        );
    }

    private SvgUtils() {
    }
}
