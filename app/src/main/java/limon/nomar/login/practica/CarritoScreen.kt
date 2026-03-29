package limon.nomar.login.practica



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.Context


@Composable
fun CarritoScreen(context: Context) {

    val carrito = CarritoManager(context)
    val ids = carrito.obtenerCarrito()

    val productos = ids.mapNotNull {
        ProductoLista.obtenerPorId(it)
    }

    val total = productos.sumOf { it.precio.toDouble() }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) {
                Text("${it.nombre} - $${it.precio}")
            }
        }

        Text("Productos: ${productos.size}")
        Text("Total: $${total}")

        Button(onClick = { carrito.limpiarCarrito() }) {
            Text("Vaciar carrito")
        }
    }
}