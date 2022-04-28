package nprk.ktxmenu.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import nprk.ktxmenu.R

abstract class BottomSheetTransparent<VB : ViewBinding>(
    private val themeRes: Int = R.style.BottomSheetDialogTransparent
) : BottomSheetDialogFragment() {
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!
    protected abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    protected abstract fun bind(binding: VB)

    protected open val bottomSheetListener: BottomSheetListener? = null

    private var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>? = null

    protected fun changeBehavior(bsb: BottomSheetBehavior<FrameLayout>.() -> Unit) {
        bottomSheetBehavior?.let {
            bsb.invoke(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, themeRes)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = viewBinding(inflater, container)
        bind(binding)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog

            val bottomSheet = d.findViewById<FrameLayout>(
                com.google.android.material.R.id.design_bottom_sheet
            ) ?: return@setOnShowListener

            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

            bottomSheetBehavior?.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    bottomSheetListener?.onSlide(bottomSheet, slideOffset)
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    bottomSheetListener?.onStateChanged(bottomSheet, newState)
                }
            })
        }
        return dialog
    }
}

interface BottomSheetListener {
    fun onSlide(bottomSheet: View, slideOffset: Float)
    fun onStateChanged(bottomSheet: View, @BottomSheetBehavior.State newState: Int)
}