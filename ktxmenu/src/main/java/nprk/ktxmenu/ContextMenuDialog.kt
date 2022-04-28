package nprk.ktxmenu

import androidx.recyclerview.widget.LinearLayoutManager
import nprk.ktxmenu.bottomsheet.FullBottomSheetTransparent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem
import nprk.ktxmenu.adapter.*
import nprk.ktxmenu.adapter.ContextClickMenuItem
import nprk.ktxmenu.adapter.ContextDividerMenuItem
import nprk.ktxmenu.adapter.ContextHeaderMenuItem
import nprk.ktxmenu.adapter.ContextImageTextMenuItem
import nprk.ktxmenu.databinding.FragmentContextMenuDialogBinding

class ContextMenuDialog(
    itemList: List<ContextMenuItem>,
    private val listener: (action: ContextMenuAction) -> Unit
) : FullBottomSheetTransparent<FragmentContextMenuDialogBinding>() {
    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentContextMenuDialogBinding.inflate(inflater, container, false)

    private var list: List<ContextMenuItem> = itemList

    private val bindingList: List<BindableItem<*>>
        get() = list.mapIndexedNotNull { index, item ->
            val position: Int = index + 1

            when (item) {
                is ClickMenuItem -> ContextClickMenuItem(index.toLong(), item) {
                    dismiss()

                    listener.invoke(
                        ContextMenuAction(
                            this@ContextMenuDialog,
                            position,
                            item.key
                        )
                    )
                }
                is SelectMenuItem -> ContextSelectMenuItem(index.toLong(), item) {
                    val isSelected: Boolean = when (item.isRadio) {
                        true -> true
                        false -> !item.isSelected
                    }

                    listener.invoke(
                        ContextMenuAction(
                            this@ContextMenuDialog,
                            position,
                            item.key,
                            isSelected
                        )
                    )

                    list = list.map {
                        if (it == item) {
                            item.copy(isSelected = isSelected)
                        } else {
                            it
                        }
                    }

                    updateBindingList()
                }
                is HeaderMenuItem -> ContextHeaderMenuItem(index.toLong(), item)

                is ImageTextMenuItem -> ContextImageTextMenuItem(index.toLong(), item) {
                    dismiss()

                    listener.invoke(
                        ContextMenuAction(
                            this@ContextMenuDialog,
                            position,
                            item.key
                        )
                    )
                }

                is DividerMenuItem -> ContextDividerMenuItem(index.toLong())
                else -> null
            }
        }

    override fun bind(binding: FragmentContextMenuDialogBinding) {
        binding.list.apply {
            adapter = GroupieAdapter()
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            itemAnimator = null
        }

        updateBindingList()
    }

    fun deselectByKey(keyList: List<String>) {
        val newMenuItemList: MutableList<ContextMenuItem> = mutableListOf()

        list.forEach { item ->
            if (item.key != null && keyList.contains(item.key)) {
                if (item is SelectMenuItem) {
                    newMenuItemList.add(
                        item.copy(isSelected = false)
                    )
                } else {
                    newMenuItemList.add(item)
                }
            } else {
                newMenuItemList.add(item)
            }
        }

        list = newMenuItemList
        updateBindingList()
    }

    fun deselectAll(except: List<String> = listOf()) {
        val newMenuItemList: MutableList<ContextMenuItem> = mutableListOf()
        list.forEach { item ->
            if (item is SelectMenuItem) {
                if (item.key != null && except.contains(item.key)) {
                    newMenuItemList.add(item)
                } else {
                    newMenuItemList.add(
                        item.copy(isSelected = false)
                    )
                }
            } else {
                newMenuItemList.add(item)
            }
        }

        list = newMenuItemList
        updateBindingList()
    }

    private fun updateBindingList() = (binding.list.adapter as GroupieAdapter).update(bindingList)
}

data class ContextMenuAction(
    val dialog: ContextMenuDialog,
    val position: Int,
    val key: String?,
    val selectedState: Boolean? = null
)