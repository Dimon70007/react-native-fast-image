package com.dylanvann.fastimage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;

public class FrameModelLoader implements ModelLoader {
    @Nullable
    @Override
    public LoadData buildLoadData(@NonNull Object o, int width, int height, @NonNull Options options) {
        return null;
    }

    @Override
    public boolean handles(@NonNull Object o) {
        return false;
    }
}
