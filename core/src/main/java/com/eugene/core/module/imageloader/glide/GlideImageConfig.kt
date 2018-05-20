package com.eugene.core.module.imageloader.glide

import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.eugene.core.module.imageloader.ImageConfig

class GlideImageConfig(builder: Builder) : ImageConfig() {

    /**
     * 0对应DiskCacheStrategy.all,
     * 1对应DiskCa|cheStrategy.NONE,
     * 2对应DiskCacheStrategy.SOURCE,
     * 3对应DiskCacheStrategy.RESULT
     */
    var cacheStrategy: Int
    /**
     * 请求 url 为空,则使用此图片作为占位符
     */
    var fallback: Int
    /**
     * 图片每个圆角的大小
     */
    var imageRadius: Int
    /**
     * 高斯模糊值, 值越大模糊效果越大
     */
    var blurValue: Int

    /**
     * glide用它来改变图形的形状
     */
    @Deprecated("")
    var transformation: BitmapTransformation?

    var imageViews: Array<ImageView>?
    /**
     * 是否使用淡入淡出过渡动画
     */
    var isCrossFade: Boolean?
    /**
     * 是否将图片剪切为 CenterCrop
     */
    var isCenterCrop: Boolean?
    /**
     * 是否将图片剪切为圆形
     */
    var isCircle: Boolean?
    /**
     * 清理内存缓存
     */
    var isClearMemory: Boolean?
    /**
     * 清理本地缓存
     */
    var isClearDiskCache: Boolean?

    init {
        url = builder.url
        imageView = builder.imageView
        placeholder = builder.placeholder
        errorPic = builder.errorPic
        cacheStrategy = builder.cacheStrategy
        fallback = builder.fallback
        imageRadius = builder.imageRadius
        blurValue = builder.blurValue
        transformation = builder.transformation
        imageViews = builder.imageViews
        isCrossFade = builder.isCrossFade
        isCenterCrop = builder.isCenterCrop
        isCircle = builder.isCircle
        isClearMemory = builder.isClearMemory
        isClearDiskCache = builder.isClearDiskCache
    }


    class Builder(init: Builder.() -> Unit) {

        var url: String? = null
        var imageView: ImageView? = null
        var placeholder: Int = 0
        var errorPic: Int = 0

        /**
         * 0对应DiskCacheStrategy.all,
         * 1对应DiskCa|cheStrategy.NONE,
         * 2对应DiskCacheStrategy.SOURCE,
         * 3对应DiskCacheStrategy.RESULT
         */
        var cacheStrategy: Int = 0
        /**
         * 请求 url 为空,则使用此图片作为占位符
         */
        var fallback: Int = 0
        /**
         * 图片每个圆角的大小
         */
        var imageRadius: Int = 0
        /**
         * 高斯模糊值, 值越大模糊效果越大
         */
        var blurValue: Int = 0

        /**
         * glide用它来改变图形的形状
         */
        @Deprecated("")
        var transformation: BitmapTransformation? = null

        var imageViews: Array<ImageView>? = null
        /**
         * 是否使用淡入淡出过渡动画
         */
        var isCrossFade: Boolean? = null
        /**
         * 是否将图片剪切为 CenterCrop
         */
        var isCenterCrop: Boolean? = null
        /**
         * 是否将图片剪切为圆形
         */
        var isCircle: Boolean? = null
        /**
         * 清理内存缓存
         */
        var isClearMemory: Boolean? = null
        /**
         * 清理本地缓存
         */
        var isClearDiskCache: Boolean? = null

        fun url(init: Builder.() -> String) = apply { init() }

        fun imageView(init: Builder.() -> ImageView) = apply { init() }

        fun placeholder(init: Builder.() -> Int) = apply { init() }

        fun errorPic(init: Builder.() -> Int) = apply { init() }

        fun cacheStrategy(init: Builder.() -> Int) = apply { init() }

        fun fallback(init: Builder.() -> Int) = apply { init() }

        fun imageRadius(init: Builder.() -> Int) = apply { init() }

        fun blurValue(init: Builder.() -> Int) = apply { init() }

        fun transformation(init: Builder.() -> BitmapTransformation) = apply { init() }

        fun imageViews(init: Builder.() -> Array<ImageView>) = apply { init() }

        fun isCrossFade(init: Builder.() -> Boolean) = apply { init() }

        fun isCenterCrop(init: Builder.() -> Boolean) = apply { init() }

        fun isCircle(init: Builder.() -> Boolean) = apply { init() }

        fun isClearMemory(init: Builder.() -> Boolean) = apply { init() }

        fun isClearDiskCache(init: Builder.() -> Boolean) = apply { init() }


    }

}