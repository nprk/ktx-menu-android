package nprk.ktxmenu.adapter

import android.graphics.Typeface
import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import nprk.ktxmenu.HeaderMenuItem
import nprk.ktxmenu.R
import nprk.ktxmenu.databinding.ItemContextHeaderMenuBinding

internal class ContextHeaderMenuItem(
    index: Long,
    private val itemMenu: HeaderMenuItem,
) : BindableItem<ItemContextHeaderMenuBinding>(index) {
    override fun bind(viewBinding: ItemContextHeaderMenuBinding, position: Int) {
        val context = viewBinding.root.context

        with(viewBinding) {
            text.text = itemMenu.text.toString(context)
            item.isClickable = itemMenu.isEnabled
            if (itemMenu.isBold) {
                text.setTypeface(null, Typeface.BOLD)
            }
        }
    }

    override fun getLayout(): Int = R.layout.item_context_header_menu
    override fun initializeViewBinding(view: View): ItemContextHeaderMenuBinding =
        ItemContextHeaderMenuBinding.bind(view)
}