package com.dylanvann.fastimage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import static com.dylanvann.fastimage.FastImageRequestListener.REACT_ON_ERROR_EVENT;
import static com.dylanvann.fastimage.FastImageRequestListener.REACT_ON_LOAD_END_EVENT;
import static com.dylanvann.fastimage.FastImageRequestListener.REACT_ON_LOAD_EVENT;
import static com.dylanvann.fastimage.FastImageViewManager.sendEventToJS;

public class BitmapRequestListaner implements RequestListener<Bitmap> {
    private String key;

    BitmapRequestListaner(String key) {
        this.key = key;
    }

    private static WritableMap mapFromResource(Bitmap bitmap, ThemedReactContext context) {
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        WritableMap resourceData = new WritableNativeMap();
        resourceData.putInt("width", drawable.getIntrinsicWidth());
        resourceData.putInt("height", drawable.getIntrinsicHeight());
        return resourceData;
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap>  target, boolean isFirstResource) {
        FastImageOkHttpProgressGlideModule.forget(key);
        if (!(target instanceof ImageViewTarget)) {
            Log.e(getClass().getName(), "onLoadFailed " + e.toString());
            return false;
        }
        FastImageViewWithUrl view = (FastImageViewWithUrl) ((ImageViewTarget) target).getView();
        sendEventToJS(view, REACT_ON_ERROR_EVENT, new WritableNativeMap());
        sendEventToJS(view, REACT_ON_LOAD_END_EVENT, new WritableNativeMap());
        return false;
    }

    @Override
    public boolean onResourceReady(Bitmap bitmap, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
        if (!(target instanceof ImageViewTarget)) {
            Log.e(getClass().getName(), "onResourceReady " + bitmap.toString());
            return false;
        }
        FastImageViewWithUrl view = (FastImageViewWithUrl) ((ImageViewTarget) target).getView();
        view.setImageBitmap(bitmap);
        ThemedReactContext context = (ThemedReactContext) view.getContext();
        sendEventToJS(view, REACT_ON_LOAD_EVENT, mapFromResource(bitmap, context));
        sendEventToJS(view, REACT_ON_LOAD_END_EVENT, new WritableNativeMap());
        return false;
    }


}
