package nprk.ktxmenu.adapter

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import nprk.ktxmenu.R
import nprk.ktxmenu.SelectMenuItem
import nprk.ktxmenu.databinding.ItemContextSelectMenuBinding
import nprk.ktxmenu.ext.hideIf
import nprk.ktxmenu.ext.showIf

internal class ContextSelectMenuItem(
    index: Long,
    private val itemMenu: SelectMenuItem,
    private val clickListener: () -> Unit
) : BindableItem<ItemContextSelectMenuBinding>(index) {
    override fun bind(viewBinding: ItemContextSelectMenuBinding, position: Int) {
        val context = viewBinding.root.context

        with(viewBinding) {
            radio.showIf { itemMenu.isRadio }
            checkbox.hideIf { itemMenu.isRadio }

            radio.isChecked = itemMenu.isSelected
            checkbox.isChecked = itemMenu.isSelected

            text.text = itemMenu.text.toString(context)

            item.setOnClickListener { clickListener.invoke() }
            item.isClickable = itemMenu.isEnabled
            item.alpha = if (itemMenu.isEnabled) 1.0f else 0.5f

            icon.setImageDrawable(itemMenu.iconRes?.toDrawable(context))
        }
    }

    override fun getLayout(): Int = R.layout.item_context_select_menu
    override fun initializeViewBinding(view: View): ItemContextSelectMenuBinding =
        ItemContextSelectMenuBinding.bind(view)
}