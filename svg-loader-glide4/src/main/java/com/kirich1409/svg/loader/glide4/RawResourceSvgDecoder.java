package com.kirich1409.svg.loader.glide4;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;

import com.bumptech.glide.load.Options;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.io.IOException;
import java.util.List;

final class RawResourceSvgDecoder extends SvgDecoder<Uri> {

    private static final int NAME_URI_PATH_SEGMENTS = 2;
    private static final int ID_PATH_SEGMENTS = 1;

    private static final int TYPE_PATH_SEGMENT_INDEX = 0;
    private static final int NAME_PATH_SEGMENT_INDEX = 1;

    private static final int RES_ID_SEGMENT_INDEX = 0;

    private static final String RESOURCE_TYPE_RAW = "raw";

    private final Resources mResources;

    public RawResourceSvgDecoder(@NonNull Context context) {
        this.mResources = context.getResources();
    }

    @Override
    public boolean handles(@NonNull Uri source, Options options) throws IOException {
        if (!ContentResolver.SCHEME_ANDROID_RESOURCE.equals(source.getScheme())) {
            return false;
        }

        List<String> pathSegments = source.getPathSegments();
        if (pathSegments.size() == NAME_URI_PATH_SEGMENTS) {
            return pathSegments.size() > TYPE_PATH_SEGMENT_INDEX
                    && RESOURCE_TYPE_RAW.equals(pathSegments.get(TYPE_PATH_SEGMENT_INDEX));

        } else if (pathSegments.size() == ID_PATH_SEGMENTS) {
            try {
                int resId = Integer.parseInt(pathSegments.get(RES_ID_SEGMENT_INDEX));
                return resId != 0
                        && RESOURCE_TYPE_RAW.equals(mResources.getResourceTypeName(resId));
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    SVG loadSvg(Uri source, int width, int height, Options options) throws SVGParseException {
        return SVG.getFromResource(mResources, getResourceId(source));
    }

    @RawRes
    private int getResourceId(@NonNull Uri source) {
        List<String> segments = source.getPathSegments();
        int resId;
        if (segments.size() == NAME_URI_PATH_SEGMENTS) {
            String typeName = segments.get(TYPE_PATH_SEGMENT_INDEX);
            checkResourceType(typeName);
            String packageName = source.getAuthority();
            String resourceName = segments.get(NAME_PATH_SEGMENT_INDEX);
            resId = mResources.getIdentifier(resourceName, typeName, packageName);
        } else if (segments.size() == ID_PATH_SEGMENTS) {
            try {
                resId = Integer.valueOf(segments.get(RES_ID_SEGMENT_INDEX));
                if (resId != 0) {
                    checkResourceType(mResources.getResourceTypeName(resId));
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Unrecognized Uri format: " + source, e);
            }
        } else {
            throw new IllegalArgumentException("Unrecognized Uri format: " + source);
        }

        if (resId == 0) {
            throw new IllegalArgumentException("Failed to obtain resource id for: " + source);
        }
        return resId;
    }

    private static void checkResourceType(@Nullable String resType) {
        if (!RESOURCE_TYPE_RAW.equals(resType)) {
            throw new IllegalArgumentException("Unsupported resource type: " + resType);
        }
    }
}
