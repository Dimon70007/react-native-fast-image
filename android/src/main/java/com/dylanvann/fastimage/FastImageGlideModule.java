package com.dylanvann.fastimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

// We need an AppGlideModule to be present for progress events to work.
@GlideModule
public final class FastImageGlideModule extends AppGlideModule {
//    @Override
//    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
//
//        registry.prepend(Registry.BUCKET_BITMAP, Uri.class,Bitmap.class, new FFmpegVideoDecoder(glide.getBitmapPool()));
//        super.registerComponents(context, glide, registry);
//    }

//    @Override
//    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
//         RequestOptions requestOptions = new RequestOptions()
//            // Get frame at 10% into the media (default 3%)
////            .set(FFmpegVideoDecoder.PERCENTAGE_DURATION, 0.10F);
//            // OR get frame at some time (micro-seconds)
//             .set(FFmpegVideoDecoder.FRAME_AT_TIME, 0);
//
//        builder.setDefaultRequestOptions(requestOptions);
//        super.applyOptions(context, builder);
//    }
}
