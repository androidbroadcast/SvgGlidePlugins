package com.kirich1409.svgimageloaderplugins;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by Kiryl Rozau on 18/11/17.
 */
@GlideModule
public class FontmaniaGlideModule extends AppGlideModule {

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
