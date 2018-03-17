/*
 * Copyright 2018 Kirill Rozov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kirich1409.svgloader.glide.decoder;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import com.kirich1409.svgloader.glide.SvgResource;

import java.io.IOException;

@RestrictTo(RestrictTo.Scope.LIBRARY)
public abstract class SvgDecoder<T> implements ResourceDecoder<T, SVG> {

    @Override
    public boolean handles(@NonNull T source, @NonNull Options options) throws IOException {
        return true;
    }

    @Nullable
    @Override
    public Resource<SVG> decode(@NonNull T source, int width, int height, @NonNull Options options)
            throws IOException {
        try {
            int sourceSize = getSize(source);
            SVG svg = loadSvg(source, width, height, options);
            return new SvgResource(svg, width, height, sourceSize);
        } catch (SVGParseException e) {
            throw new IOException("Cannot load SVG", e);
        }
    }

    @IntRange(from = 0)
    protected abstract int getSize(@NonNull T source) throws IOException;

    abstract SVG loadSvg(T source, int width, int height, @NonNull Options options)
            throws SVGParseException;
}
