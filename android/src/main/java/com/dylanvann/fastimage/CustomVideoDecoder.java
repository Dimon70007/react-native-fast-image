package com.dylanvann.fastimage;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;

import java.io.IOException;

public class CustomVideoDecoder implements ResourceDecoder<Uri, Bitmap> {

    @Override
    public boolean handles(@NonNull Uri source, @NonNull Options options) throws IOException {
        return false;
    }

    @Nullable
    @Override
    public Resource<Bitmap> decode(@NonNull Uri source, int width, int height, @NonNull Options options) throws IOException {
        return null;
    }
}
