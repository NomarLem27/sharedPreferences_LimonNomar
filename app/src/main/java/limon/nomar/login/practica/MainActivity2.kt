package limon.nomar.login.practica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*


class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var screen by remember { mutableStateOf("HOME") }
            var selectedId by remember { mutableStateOf(0) }

            when (screen) {

                "HOME" -> HomeScreen(
                    context = this,
                    onCarrito = { screen = "CARRITO" },
                    onDetalle = {
                        selectedId = it
                        screen = "DETALLE"
                    }
                )

                "DETALLE" -> DetalleScreen(
                    id = selectedId,
                    context = this
                )

                "CARRITO" -> CarritoScreen(
                    context = this
                )
            }
        }
    }
}