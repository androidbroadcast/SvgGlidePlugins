package com.kirich1409.svg.loader.core;

import android.support.annotation.NonNull;

import com.caverock.androidsvg.SVG;

/**
 * Created by Kiryl Rozau on 18/11/17.
 */

public final class SvgUtils {

    public static void fix(@NonNull SVG svg) {
        if (svg.getDocumentViewBox() == null) {
            svg.setDocumentViewBox(0F, 0F, svg.getDocumentWidth(), svg.getDocumentHeight());
        }
    }

    private SvgUtils() {
    }
}
