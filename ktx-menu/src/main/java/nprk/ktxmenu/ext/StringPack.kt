package nprk.ktxmenu.ext

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class StringPack {
    abstract fun toString(context: Context): String

    data class Resource(
        @StringRes val res: Int
    ) : StringPack() {
        override fun toString(context: Context): String = context.getString(res)
    }

    data class ResourceFormatted(
        @StringRes val res: Int,
        val args: List<Any>
    ) : StringPack() {
        constructor(stringRes: Int, vararg args: Any) : this(stringRes, args.toList())

        override fun toString(context: Context): String =
            context.getString(res, *(args.toTypedArray()))
    }

    data class Plural(
        @PluralsRes val res: Int,
        val number: Int
    ) : StringPack() {
        override fun toString(context: Context): String =
            context.resources.getQuantityString(res, number)
    }

    data class PluralFormatted(
        @PluralsRes val res: Int,
        val number: Int,
        val args: List<Any>
    ) : StringPack() {
        constructor(res: Int, number: Int, vararg args: Any) : this(res, number, args.toList())

        override fun toString(context: Context): String =
            context.resources.getQuantityString(res, number, *(args.toTypedArray()))
    }

    data class Raw(
        val string: String
    ) : StringPack() {
        override fun toString(context: Context): String = string
    }

    data class Composition(
        val args: List<StringPack>
    ) : StringPack() {
        override fun toString(context: Context): String = StringBuilder().apply {
            args.forEach {
                append(it.toString(context))
            }
        }.toString()
    }
}