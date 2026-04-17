package limon.nomar.login.practica



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import android.content.Context
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


@Composable
fun DetalleScreen(id: Int, context: Context) {

    val producto = ProductoLista.obtenerPorId(id)
    val dataStore = DataStoreManager(context)

    producto?.let {

        Column(modifier = Modifier.padding(16.dp)) {

            Image(
                painter = painterResource(it.imagen),
                contentDescription = it.nombre,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )

            Text(it.nombre, style = MaterialTheme.typography.headlineMedium)
            Text("$${it.precio}")
            Text(it.descripcion)

            Button(onClick = {

                kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {

                    dataStore.carritoFlow.collect { carritoActual ->

                        val nuevo = carritoActual.toMutableList()
                        nuevo.add(it.id)

                        dataStore.guardarCarrito(nuevo)

                        this.cancel()
                    }
                }

            }) {
                Text("Agregar al carrito")
            }
        }
    }
}