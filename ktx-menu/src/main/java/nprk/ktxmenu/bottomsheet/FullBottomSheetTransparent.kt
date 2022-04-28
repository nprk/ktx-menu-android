package nprk.ktxmenu.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import nprk.ktxmenu.R

abstract class FullBottomSheetTransparent<VB : ViewBinding>(
    themeRes: Int = R.style.BottomSheetDialogTransparent
) : BottomSheetTransparent<VB>(themeRes) {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener {
            val bottomSheet: FrameLayout? = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                BottomSheetBehavior.from(it).apply {
                    skipCollapsed = true
                    state = BottomSheetBehavior.STATE_EXPANDED
                    peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
                }
            }
        }
        return dialog
    }
}