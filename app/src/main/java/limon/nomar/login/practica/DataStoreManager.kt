package limon.nomar.login.practica

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore("app_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val CARRITO_IDS = stringPreferencesKey("carrito_ids")
    }

    val carritoFlow: Flow<List<Int>> =
        context.dataStore.data.map {
            val data = it[CARRITO_IDS] ?: ""
            if (data.isEmpty()) emptyList()
            else data.split(",").map { id -> id.toInt() }
        }

    suspend fun guardarCarrito(ids: List<Int>) {
        context.dataStore.edit {
            it[CARRITO_IDS] = ids.joinToString(",")
        }
    }

    suspend fun limpiarCarrito() {
        context.dataStore.edit {
            it.remove(CARRITO_IDS)
        }
    }
}