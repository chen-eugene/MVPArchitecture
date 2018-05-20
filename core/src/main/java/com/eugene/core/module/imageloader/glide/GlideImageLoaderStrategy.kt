package com.eugene.core.module.imageloader.glide

import android.content.Context
import com.eugene.core.module.imageloader.IImageLoaderStrategy
import com.eugene.core.utils.Preconditions

/**
 * 此类只是简单的实现了 Glide 加载的策略,方便快速使用,但大部分情况会需要应对复杂的场景
 * 这时可自行实现 {@link IImageLoaderStrategy} 和 {@link ImageConfig} 替换现有策略
 */
class GlideImageLoaderStrategy : IImageLoaderStrategy<GlideImageConfig> {
    override fun loadImage(ctx: Context, config: GlideImageConfig) {

        Preconditions.checkNotNull(ctx,"Context is required")
        Preconditions.checkNotNull(config,"GlideImageConfig is required")
        if (config.url.isNullOrEmpty()) throw NullPointerException("Url is required")
        Preconditions.checkNotNull(config.imageView,"ImageView is required")



    }

    override fun clear(ctx: Context, config: GlideImageConfig) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}