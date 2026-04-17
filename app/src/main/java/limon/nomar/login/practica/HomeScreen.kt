package limon.nomar.login.practica

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.remote.creation.first
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    context: Context,
    onCarrito: () -> Unit,
    onDetalle: (Int) -> Unit
) {

    var search by remember { mutableStateOf("") }
    var lista by remember { mutableStateOf(ProductoLista.productos) }

    val dataStore = DataStoreManager(context)
    val scope = rememberCoroutineScope()

    val naranjaOW = Color(0xFFFA9C1D)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                    lista = ProductoLista.filtrarPorNombre(it)
                },
                modifier = Modifier.weight(1f),
                label = { Text("Buscar") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = naranjaOW,
                    cursorColor = naranjaOW,
                    focusedLabelColor = naranjaOW
                )
            )

            Button(
                onClick = onCarrito,
                colors = ButtonDefaults.buttonColors(
                    containerColor = naranjaOW,
                    contentColor = Color.White
                )
            ) {
                Text("🛒")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(lista) { producto ->

                ProductoItem(
                    producto = producto,

                    onAgregar = {

                        scope.launch {

                            val carritoActual = dataStore.carritoFlow.first()

                            val nuevo = carritoActual.toMutableList()
                            nuevo.add(producto.id)

                            dataStore.guardarCarrito(nuevo)
                        }

                        Toast.makeText(
                            context,
                            "Se agregó ${producto.nombre} al carrito",
                            Toast.LENGTH_SHORT
                        ).show()
                    },

                    onClick = { onDetalle(producto.id) }
                )
            }
        }
    }
}