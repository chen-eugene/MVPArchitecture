package com.eugene.core.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.AttributeSet
import android.view.View
import com.eugene.core.base.activity.IActivityDelegate.Companion.LAYOUT_FRAMELAYOUT
import com.eugene.core.base.activity.IActivityDelegate.Companion.LAYOUT_LINEARLAYOUT
import com.eugene.core.base.activity.IActivityDelegate.Companion.LAYOUT_RELATIVELAYOUT
import com.eugene.core.base.app.IApp
import com.eugene.core.di.component.AppComponent
import com.zhy.autolayout.AutoFrameLayout
import com.zhy.autolayout.AutoLinearLayout
import com.zhy.autolayout.AutoRelativeLayout

/**
 * 0 说明 AndroidManifest 里面没有使用 AutoLauout 的Meta,即不使用 AutoLayout,
 * 1 为有 Meta ,即需要使用
 */

object AppUtil {

    var USER_AUTO_LAYOUT = -1

    fun obtainAppComponentFromContext(context: Context): AppComponent? {
        Preconditions.checkNotNull(context, "%s cannot be null", context::class.java.name)
        Preconditions.checkState(context.applicationContext is IApp, "Application does not implements App")
        return (context.applicationContext as IApp).getAppComponent()
    }

    /**
     * 进行AutoLayout转换
     */
    fun convertAutoView(name: String, context: Context, attrs: AttributeSet): View? {
        //本框架并不强制你使用 AutoLayout
        //如果你不想使用 AutoLayout ,就不要在 AndroidManifest 中声明, AutoLayout 的 Meta属性(design_width,design_height)
        if (USER_AUTO_LAYOUT == -1) {
            USER_AUTO_LAYOUT = 1
            val packageManager = context.packageManager
            val applicationInfo: ApplicationInfo?
            try {
                applicationInfo = packageManager.getApplicationInfo(context
                        .packageName, PackageManager.GET_META_DATA)
                if (applicationInfo?.metaData == null
                        || !applicationInfo.metaData.containsKey("design_width")
                        || !applicationInfo.metaData.containsKey("design_height")) {
                    USER_AUTO_LAYOUT = 0
                }
            } catch (e: PackageManager.NameNotFoundException) {
                USER_AUTO_LAYOUT = 0
            }

        }

        if (USER_AUTO_LAYOUT == 0) {
            return null
        }

        var view: View? = null
        when (name) {
            LAYOUT_FRAMELAYOUT -> view = AutoFrameLayout(context, attrs)
            LAYOUT_LINEARLAYOUT -> view = AutoLinearLayout(context, attrs)
            LAYOUT_RELATIVELAYOUT -> view = AutoRelativeLayout(context, attrs)
        }
        return view
    }


}