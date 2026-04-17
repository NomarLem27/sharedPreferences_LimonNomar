package limon.nomar.login.practica



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch


@Composable
fun CarritoScreen(context: Context) {

    val dataStore = DataStoreManager(context)

    val ids by dataStore.carritoFlow.collectAsState(initial = emptyList())

    val productos = ids.mapNotNull {
        ProductoLista.obtenerPorId(it)
    }

    val total = productos.sumOf { it.precio.toDouble() }

    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) {
                Text("${it.nombre} - $${it.precio}")
            }
        }

        Text("Productos: ${productos.size}")
        Text("Total: $${total}")

        Button(
            onClick = {
                scope.launch {
                    dataStore.limpiarCarrito()
                }
            }
        ) {
            Text("Vaciar carrito")
        }
    }
}