package nprk.ktxmenu.ext

import android.view.View

internal fun View.show() {
    visibility = View.VISIBLE
}

internal fun View.hide() {
    visibility = View.GONE
}

internal fun View.invisible() {
    visibility = View.INVISIBLE
}

internal inline fun View.showIf(condition: View.() -> Boolean) {
    if (condition()) {
        show()
    } else {
        hide()
    }
}

internal inline fun View.visibleIf(condition: View.() -> Boolean) {
    if (condition()) {
        show()
    } else {
        invisible()
    }
}

internal inline fun View.hideIf(condition: View.() -> Boolean) {
    if (condition()) {
        hide()
    } else {
        show()
    }
}