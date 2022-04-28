package nprk.ktxmenu.ext

import android.graphics.Bitmap
import java.util.*

internal fun Int.colorPackRes() = ColorPack.Resource(this)
internal fun Int.colorPackRaw() = ColorPack.Raw(this)

internal fun Bitmap.drawablePackBitmap() = DrawablePack.Bitmap(this)
internal fun Int.drawablePackRes() = DrawablePack.Resource(this)

internal fun String.stringPackRaw() = StringPack.Raw(this)
internal fun Int.stringPackRes() = StringPack.Resource(this)

internal fun <T> Iterable<T>.joinToStringPack(
    transform: (T) -> (StringPack),
    separator: StringPack = " ,".stringPackRaw(),
    truncated: StringPack = "...".stringPackRaw(),
    postfix: StringPack? = null,
    limit: Int = -1
): StringPack.Composition {
    val buffer = LinkedList<StringPack>()
    var count = 0
    for (element in this) {
        if (++count > 1) buffer.add(separator)
        if (limit < 0 || count <= limit) {
            buffer.add(transform(element))
        } else break
    }
    if (limit in 0 until count) buffer.add(truncated)
    postfix?.let {
        buffer.add(postfix)
    }

    return StringPack.Composition(buffer)
}