package nprk.ktxmenu

import nprk.ktxmenu.ext.DrawablePack
import nprk.ktxmenu.ext.StringPack
import nprk.ktxmenu.ext.stringPackRaw

interface ContextMenuItem {
    val text: StringPack
    val isEnabled: Boolean
    val key: String?
}

data class ClickMenuItem(
    override val text: StringPack,
    val iconRes: DrawablePack? = null,
    override val isEnabled: Boolean = true,
    override val key: String? = null
) : ContextMenuItem

data class SelectMenuItem(
    val isSelected: Boolean,
    override val text: StringPack,
    val iconRes: DrawablePack? = null,
    val isRadio: Boolean = false,
    override val isEnabled: Boolean = true,
    override val key: String? = null
) : ContextMenuItem

data class HeaderMenuItem(
    override val text: StringPack,
    val isBold: Boolean = false,
    override val isEnabled: Boolean = true,
    override val key: String? = null
) : ContextMenuItem

data class ImageTextMenuItem(
    override val text: StringPack,
    val imageUrl: String,
    override val isEnabled: Boolean = true,
    override val key: String? = null
) : ContextMenuItem

data class DividerMenuItem(
    override val text: StringPack = "".stringPackRaw(),
    override val isEnabled: Boolean = true,
    override val key: String? = null
) : ContextMenuItem