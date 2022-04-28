package nprk.ktxmenu.adapter

import android.view.View
import coil.load
import com.xwray.groupie.viewbinding.BindableItem
import nprk.ktxmenu.ImageTextMenuItem
import nprk.ktxmenu.R
import nprk.ktxmenu.databinding.ItemContextImageTextMenuBinding

internal class ContextImageTextMenuItem(
    index: Long,
    private val itemMenu: ImageTextMenuItem,
    private val clickListener: () -> Unit
) : BindableItem<ItemContextImageTextMenuBinding>(index) {
    override fun bind(viewBinding: ItemContextImageTextMenuBinding, position: Int) {
        val context = viewBinding.root.context

        with(viewBinding) {
            text.text = itemMenu.text.toString(context)

            item.setOnClickListener { clickListener.invoke() }
            item.isClickable = itemMenu.isEnabled
            item.alpha = if (itemMenu.isEnabled) 1.0f else 0.5f

            image.load(itemMenu.imageUrl)
        }
    }

    override fun getLayout(): Int = R.layout.item_context_image_text_menu
    override fun initializeViewBinding(view: View): ItemContextImageTextMenuBinding =
        ItemContextImageTextMenuBinding.bind(view)
}