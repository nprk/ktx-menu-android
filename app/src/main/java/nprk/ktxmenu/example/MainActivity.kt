package nprk.ktxmenu.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nprk.ktxmenu.example.ui.theme.RateplzexampleTheme
import nprk.ktxmenu.contextMenu
import nprk.ktxmenu.show

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateplzexampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        SimpleButton("Show Context Menu") {
                            showContextMenu()
                        }
                    }
                }
            }
        }
    }

    private fun showContextMenu() = contextMenu {
        header(
            text = "Header",
            isBold = true,
            isEnabled = false
        )

        click(
            text = "Click",
            R.drawable.ic_android_black_24dp,
            key = "simple_click"
        )

        click(
            text = "Disabled click",
            R.drawable.ic_android_black_24dp,
            isEnabled = false,
            key = "disabled_click"
        )

        divider()

        select(
            text = "Checkbox #1",
            isSelected = true
        )

        select(
            text = "Checkbox #2"
        )

        divider()

        select(
            isRadio = true,
            text = "Radio #1"
        )

        select(
            isRadio = true,
            text = "Radio #2",
            isSelected = true
        )

        divider()

        imageText(
            text = "Item with image",
            imageUrl = "https://pickaface.net/gallery/avatar/unr_test_180821_0925_9k0pgs.png"
        )
    }.show(supportFragmentManager) { action ->
        when (action.key) {
            "simple_click" -> Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun SimpleButton(name: String, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = onClick
        ) {
            Text(name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RateplzexampleTheme {
        SimpleButton("Show Dialog") {

        }
    }
}