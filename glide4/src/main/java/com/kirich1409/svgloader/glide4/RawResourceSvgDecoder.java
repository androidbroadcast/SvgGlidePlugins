package com.kirich1409.svgloader.glide4;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.kirich1409.svg.loader.core.ResourceUtils;

import java.io.IOException;

final class RawResourceSvgDecoder extends SvgDecoder<Uri> {

    private final Resources mResources;

    RawResourceSvgDecoder(@NonNull Context context) {
        mResources = context.getResources();
    }

    @Override
    public boolean handles(@NonNull Uri source, Options options) throws IOException {
        return ResourceUtils.isRawResource(mResources, source);
    }

    @Override
    SVG loadSvg(Uri source, int width, int height, Options options) throws SVGParseException {
        return SVG.getFromResource(mResources, ResourceUtils.getRawResourceId(mResources, source));
    }
}
