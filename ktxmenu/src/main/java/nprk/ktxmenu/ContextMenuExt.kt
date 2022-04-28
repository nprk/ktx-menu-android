package nprk.ktxmenu


import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import nprk.ktxmenu.ext.drawablePackRes
import nprk.ktxmenu.ext.stringPackRaw
import nprk.ktxmenu.ext.stringPackRes

fun Context.showContextMenu(
    fm: FragmentManager,
    itemList: List<ContextMenuItem>,
    listener: (action: ContextMenuAction) -> Unit
) = ContextMenuDialog(itemList, listener).show(fm, "CONTEXT_MENU")

fun Fragment.showContextMenu(
    fm: FragmentManager,
    itemList: List<ContextMenuItem>,
    listener: (action: ContextMenuAction) -> Unit
) = requireContext().showContextMenu(fm, itemList, listener)

fun List<ContextMenuItem>.show(
    fm: FragmentManager,
    listener: (action: ContextMenuAction) -> Unit
) = ContextMenuDialog(this, listener).show(fm, "CONTEXT_MENU")

// DSL
class ContextMenuBuilder {
    val list: MutableList<ContextMenuItem> = mutableListOf()

    fun click(
        text: String,
        @DrawableRes iconRes: Int? = null,
        isEnabled: Boolean = true,
        key: String? = null
    ) = list.add(ClickMenuItem(text.stringPackRaw(), iconRes?.drawablePackRes(), isEnabled, key))

    fun select(
        text: String,
        isSelected: Boolean = false,
        @DrawableRes iconRes: Int? = null,
        isRadio: Boolean = false,
        isEnabled: Boolean = true,
        key: String? = null
    ) = list.add(SelectMenuItem(isSelected, text.stringPackRaw(), iconRes?.drawablePackRes(), isRadio, isEnabled, key))

    fun header(
        text: String,
        isBold: Boolean = false,
        isEnabled: Boolean = true,
        key: String? = null
    ) = list.add(HeaderMenuItem(text.stringPackRaw(), isBold, isEnabled, key))

    fun imageText(
        text: String,
        imageUrl: String,
        isEnabled: Boolean = true,
        key: String? = null
    ) = list.add(ImageTextMenuItem(text.stringPackRaw(), imageUrl, isEnabled, key))

    fun divider() = list.add(DividerMenuItem())
}

fun contextMenu(init: ContextMenuBuilder.() -> Unit): List<ContextMenuItem> {
    val builder = ContextMenuBuilder()
    builder.init()

    return builder.list
}