package nprk.ktxmenu.adapter

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import nprk.ktxmenu.R
import nprk.ktxmenu.databinding.ItemContextClickMenuBinding
import nprk.ktxmenu.ClickMenuItem

internal class ContextClickMenuItem(
    index: Long,
    private val itemMenu: ClickMenuItem,
    private val clickListener: () -> Unit
) : BindableItem<ItemContextClickMenuBinding>(index) {
    override fun bind(viewBinding: ItemContextClickMenuBinding, position: Int) {
        val context = viewBinding.root.context

        with(viewBinding) {
            item.setOnClickListener { clickListener.invoke() }
            item.isClickable = itemMenu.isEnabled

            text.text = itemMenu.text.toString(context)

            val alpha = if (itemMenu.isEnabled) {
                1.0f
            } else {
                0.5f
            }

            icon.setImageDrawable(itemMenu.iconRes?.toDrawable(context))
            icon.alpha = alpha
            text.alpha = alpha
        }
    }

    override fun getLayout(): Int = R.layout.item_context_click_menu
    override fun initializeViewBinding(view: View): ItemContextClickMenuBinding =
        ItemContextClickMenuBinding.bind(view)
}