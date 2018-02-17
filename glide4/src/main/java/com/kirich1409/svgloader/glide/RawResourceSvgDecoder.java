package com.kirich1409.svgloader.glide;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class RawResourceSvgDecoder extends SvgDecoder<Uri> {

    private final Resources mResources;

    RawResourceSvgDecoder(@NonNull Context context) {
        mResources = context.getResources();
    }

    @Override
    public boolean handles(@NonNull Uri source, @NonNull Options options) {
        return ResourceUtils.isRawResource(mResources, source);
    }

    @Override
    SVG loadSvg(Uri source, int width, int height, @NonNull Options options) throws SVGParseException {
        return SVG.getFromResource(mResources, ResourceUtils.getRawResourceId(mResources, source));
    }

    @Override
    protected int getSize(@NonNull Uri source) throws IOException {
        return SizeUtils.getRawResourceSize(mResources, source);
    }
}
