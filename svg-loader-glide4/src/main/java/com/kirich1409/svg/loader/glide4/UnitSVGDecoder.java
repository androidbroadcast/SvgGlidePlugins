package com.kirich1409.svg.loader.glide4;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.caverock.androidsvg.SVG;

import java.io.IOException;

/**
 * Created by Kiryl Rozau on 18/11/17.
 */

class UnitSVGDecoder implements ResourceDecoder<SVG, SVG> {

    @Override
    public boolean handles(SVG source, Options options) throws IOException {
        return true;
    }

    @Nullable
    @Override
    public Resource<SVG> decode(SVG source, int width, int height, Options options) throws IOException {
        return new SimpleResource<>(source);
    }
}
