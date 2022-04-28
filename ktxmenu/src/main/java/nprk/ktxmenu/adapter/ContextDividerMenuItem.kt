package nprk.ktxmenu.adapter

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import nprk.ktxmenu.R
import nprk.ktxmenu.databinding.ItemContextDividerMenuBinding

internal class ContextDividerMenuItem(
    index: Long,
) : BindableItem<ItemContextDividerMenuBinding>(index) {
    override fun bind(viewBinding: ItemContextDividerMenuBinding, position: Int) {}

    override fun getLayout(): Int = R.layout.item_context_divider_menu
    override fun initializeViewBinding(view: View): ItemContextDividerMenuBinding =
        ItemContextDividerMenuBinding.bind(view)
}