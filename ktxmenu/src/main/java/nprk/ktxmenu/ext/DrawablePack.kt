package nprk.ktxmenu.ext

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

sealed class DrawablePack {
    abstract fun toDrawable(context: Context): Drawable?

    data class Resource(@DrawableRes val res: Int) : DrawablePack() {
        override fun toDrawable(context: Context): Drawable? = ContextCompat.getDrawable(context, res)
    }

    data class Bitmap(val bitmap: android.graphics.Bitmap) : DrawablePack() {
        override fun toDrawable(context: Context): Drawable = BitmapDrawable(context.resources, bitmap)
    }
}