package com.android.chen.lib.utils;

import java.io.File;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;

public class ImageUtils {
    public ImageLoaderConfiguration getImageLoaderConfig(Context context,
	    File file) {
	Builder builder = new ImageLoaderConfiguration.Builder(context)
		.threadPoolSize(3)
		.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
		.memoryCacheSizePercentage(13).writeDebugLogs();
	if (file != null) {
	    if (!file.exists()) {
		file.mkdirs();
	    }
	    builder.diskCache(
		    new LruDiscCache(file, new HashCodeFileNameGenerator(),
			    30 * 1024 * 1024)).diskCacheFileCount(100);
	}
	ImageLoaderConfiguration config2 = builder.build();
	return config2;
    }

    public DisplayImageOptions getOptions() {
	return new DisplayImageOptions.Builder().cacheInMemory(true)
		.cacheOnDisk(true).build();
    }
}
