package limon.nomar.login.practica

import limon.nomar.login.R


object ProductoLista {

    val productos = listOf(
        Producto(1, "Tracer Figura", 368f, R.drawable.tracer, "Figura de Tracer"),
        Producto(2, "Genji Skin", 150f, R.drawable.genji, "Skin legendaria"),
        Producto(3, "D.Va Funko", 894f, R.drawable.dva, "Figura Funko"),
        Producto(4, "Reaper Hoodie", 1250f, R.drawable.reaper, "Sudadera"),
        Producto(5, "Mercy Alas", 750f, R.drawable.mercy, "Accesorio alas")
    )

    fun filtrarPorNombre(query: String): List<Producto> {
        return if (query.isEmpty()) productos
        else productos.filter { it.nombre.contains(query, true) }
    }

    fun obtenerPorId(id: Int): Producto? {
        return productos.find { it.id == id }
    }
}