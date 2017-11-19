package com.kirich1409.svgloader.glide3;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.kirich1409.svg.loader.core.ResourceUtils;

public final class RawResourceSvgDecoder extends SvgDecoder<Uri> {

    private final Resources mResources;

    public RawResourceSvgDecoder(@NonNull Context context) {
        this.mResources = context.getResources();
    }

    @Override
    SVG loadSvg(Uri source, int width, int height) throws SVGParseException {
        return SVG.getFromResource(mResources, ResourceUtils.getRawResourceId(mResources, source));
    }

    @Override
    public String getId() {
        return "com.kirich1409.svgloader.glide3.RawResourceSvgDecoder";
    }
}
