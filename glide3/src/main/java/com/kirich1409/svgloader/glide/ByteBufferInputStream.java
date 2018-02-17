package com.kirich1409.svgloader.glide;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RestrictTo(RestrictTo.Scope.LIBRARY)
final class ByteBufferInputStream extends InputStream {

    private static final int UNSET = -1;

    private final ByteBuffer mByteBuffer;
    private int markPos = UNSET;

    ByteBufferInputStream(@NonNull ByteBuffer byteBuffer) {
        mByteBuffer = byteBuffer;
    }

    @Override
    public int available() {
        return mByteBuffer.remaining();
    }

    @Override
    public int read() {
        return mByteBuffer.hasRemaining() ? mByteBuffer.get() : -1;
    }

    @Override
    public synchronized void mark(int limit) {
        markPos = mByteBuffer.position();
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    @Override
    public int read(@NonNull byte[] buffer, int byteOffset, int byteCount) {
        if (mByteBuffer.hasRemaining()) {
            int toRead = Math.min(byteCount, available());
            mByteBuffer.get(buffer, byteOffset, toRead);
            return toRead;
        } else {
            return -1;
        }
    }

    @Override
    public synchronized void reset() throws IOException {
        if (markPos == UNSET) {
            throw new IOException("Cannot reset to unset mark position");
        }
        // reset() was not implemented correctly in 4.0.4, so we track the mark position ourselves.
        mByteBuffer.position(markPos);
    }

    @Override
    public long skip(long byteCount) {
        if (mByteBuffer.hasRemaining()) {
            long toSkip = Math.min(byteCount, available());
            mByteBuffer.position((int) (mByteBuffer.position() + toSkip));
            return toSkip;
        } else {
            return -1;
        }
    }
}
