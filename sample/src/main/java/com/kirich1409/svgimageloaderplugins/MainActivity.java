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

package com.kirich1409.svgimageloaderplugins;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Nullable
    private Object mSource;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.sample_image);

        Spinner imageSourceView = findViewById(R.id.image_sources);
        imageSourceView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ImageSource.values()));
        imageSourceView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImageSource imageSource =
                        ((ImageSource) ((ArrayAdapter) parent.getAdapter()).getItem(position));
                setSource(imageSource);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setSource(null);
            }
        });
    }

    public void setSource(@Nullable ImageSource source) {
        if (mSource != source) {
            mSource = source;
            GlideApp.with(this)
                    .load(source.getValue())
                    .apply(GlideOptions.fitCenterTransform())
                    .into(mImageView);
        }
    }
}
