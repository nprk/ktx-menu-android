package nprk.ktxmenu.ext

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

sealed class ColorPack {
    abstract fun toInt(context: Context): Int

    data class Resource(@ColorRes val res: Int) : ColorPack() {
        override fun toInt(context: Context): Int = ContextCompat.getColor(context, res)
    }

    data class Raw(val color: Int) : ColorPack() {
        override fun toInt(context: Context): Int = color
    }
}