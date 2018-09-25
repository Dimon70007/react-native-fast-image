package com.dylanvann.fastimage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestFutureTarget;
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

public class BitmapRequestListener implements RequestListener<Bitmap> {
    private final FastImageViewWithUrl view;
    private final FastImageViewManager fastImageViewManager;
    private String key;
    private int retriesCounter = 0;

    BitmapRequestListener(String key, FastImageViewWithUrl view, FastImageViewManager fastImageViewManager) {
        this.key = key;
        this.view = view;
        this.fastImageViewManager = fastImageViewManager;
    }

    private static WritableMap mapFromResource(Drawable drawable, ThemedReactContext context) {
        WritableMap resourceData = new WritableNativeMap();
        resourceData.putInt("width", drawable.getIntrinsicWidth());
        resourceData.putInt("height", drawable.getIntrinsicHeight());
        return resourceData;
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap>  target, boolean isFirstResource) {
        FastImageOkHttpProgressGlideModule.forget(key);
        Log.e(getClass().getName(), "onLoadFailed ",e);
        if (view == null) {
            return false;
        }
        sendEventToJS(view, REACT_ON_ERROR_EVENT, new WritableNativeMap());
        sendEventToJS(view, REACT_ON_LOAD_END_EVENT, new WritableNativeMap());
        return false;
    }

    @Override
    public boolean onResourceReady(Bitmap bitmap, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
        Log.e(getClass().getName(), "onResourceReady " + target.getClass().getName() + bitmap.toString());
        if( bitmap == null && retriesCounter < 2){
            this.retriesCounter+=1;
        }
//        if( bmp == null){
//            mMediaData = new MediaMetadataRetriever();
//            mMediaData.setDataSource( /* same parameters as before */ );
//            bmp = mMediaData.getFrameAtTime( /* same parameters */ );
//        }
        if (view == null) {
            return false;
        }
        ThemedReactContext context = (ThemedReactContext) view.getContext();
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        view.setImageBitmap(bitmap);
        sendEventToJS(view, REACT_ON_LOAD_EVENT, mapFromResource(drawable, context));
        sendEventToJS(view, REACT_ON_LOAD_END_EVENT, new WritableNativeMap());
        return false;
    }


}
