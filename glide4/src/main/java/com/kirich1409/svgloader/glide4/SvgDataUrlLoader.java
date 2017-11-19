package com.kirich1409.svgloader.glide4;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

final class SvgDataUrlLoader<Data> implements ModelLoader<String, Data> {

    private static final String SVG_DATA_SCHEME_IMAGE = "data:svg+xml";
    private static final String BASE64_TAG = ";base64";

    private final DataDecoder<Data> mDataDecoder;

    SvgDataUrlLoader(DataDecoder<Data> dataDecoder) {
        mDataDecoder = dataDecoder;
    }

    @Override
    public LoadData<Data> buildLoadData(String model, int width, int height, Options options) {
        return new LoadData<>(new ObjectKey(model), new DataUriFetcher<>(model, mDataDecoder));
    }

    @Override
    public boolean handles(String url) {
        return url.startsWith(SVG_DATA_SCHEME_IMAGE);
    }

    /**
     * Allows decoding a specific type of data from a Data URL String.
     *
     * @param <Data> The type of data that can be opened.
     */
    public interface DataDecoder<Data> {

        Data decode(String uri) throws IllegalArgumentException;

        void close(Data data) throws IOException;

        Class<Data> getDataClass();
    }

    private static final class DataUriFetcher<Data> implements DataFetcher<Data> {

        private final String dataUri;
        private final DataDecoder<Data> reader;
        private Data data;

        DataUriFetcher(String dataUri, DataDecoder<Data> reader) {
            this.dataUri = dataUri;
            this.reader = reader;
        }

        @Override
        public void loadData(Priority priority, DataCallback<? super Data> callback) {
            try {
                data = reader.decode(dataUri);
                callback.onDataReady(data);
            } catch (IllegalArgumentException e) {
                callback.onLoadFailed(e);
            }
        }

        @Override
        public void cleanup() {
            try {
                reader.close(data);
            } catch (IOException e) {
                // Ignored.
            }
        }

        @Override
        public void cancel() {
            // Do nothing.
        }

        @NonNull
        @Override
        public Class<Data> getDataClass() {
            return reader.getDataClass();
        }

        @NonNull
        @Override
        public DataSource getDataSource() {
            return DataSource.LOCAL;
        }
    }

    /**
     * Factory for loading {@link InputStream} from Data URL string.
     */
    static final class StreamFactory implements ModelLoaderFactory<String, InputStream> {

        private final DataDecoder<InputStream> opener;

        StreamFactory() {
            opener = new DataDecoder<InputStream>() {
                @Override
                public InputStream decode(String url) {
                    if (!url.startsWith(SVG_DATA_SCHEME_IMAGE)) {
                        throw new IllegalArgumentException("Not a valid image data URL.");
                    }

                    int commaIndex = url.indexOf(',');
                    if (commaIndex == -1) {
                        throw new IllegalArgumentException("Missing comma in data URL.");
                    }

                    String beforeComma = url.substring(0, commaIndex);
                    if (!beforeComma.endsWith(BASE64_TAG)) {
                        throw new IllegalArgumentException("Not a base64 image data URL.");
                    }

                    String afterComma = url.substring(commaIndex + 1);
                    byte[] bytes = Base64.decode(afterComma, Base64.DEFAULT);

                    return new ByteArrayInputStream(bytes);
                }

                @Override
                public void close(InputStream inputStream) throws IOException {
                    inputStream.close();
                }

                @Override
                public Class<InputStream> getDataClass() {
                    return InputStream.class;
                }
            };
        }

        @Override
        public final ModelLoader<String, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new SvgDataUrlLoader<>(opener);
        }

        @Override
        public final void teardown() {
            // Do nothing.
        }
    }
}