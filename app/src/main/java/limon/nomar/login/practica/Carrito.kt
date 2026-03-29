package limon.nomar.login.practica

import android.content.Context

class CarritoManager(context: Context) {

    private val prefs = context.getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)

    fun guardarCarrito(ids: List<Int>) {
        prefs.edit().putString("carrito_ids", ids.joinToString(",")).apply()
    }

    fun obtenerCarrito(): MutableList<Int> {
        val data = prefs.getString("carrito_ids", "") ?: ""
        return if (data.isEmpty()) mutableListOf()
        else data.split(",").map { it.toInt() }.toMutableList()
    }

    fun agregarProducto(id: Int) {
        val carrito = obtenerCarrito()
        carrito.add(id)
        guardarCarrito(carrito)
    }

    fun limpiarCarrito() {
        prefs.edit().clear().apply()
    }
}