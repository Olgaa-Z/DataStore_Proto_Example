package binar.andlima.datastoreproto2

import android.app.Person
import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoRepository (context : Context) {

    private val dataStore : DataStore<UserPreferences> = context.createDataStore(
        "userData",
        serializer = UserSerializer()
    )
    val readProto : Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException){
                emit(UserPreferences.getDefaultInstance())
            }else{
                throw exception
            }
        }

    suspend fun updateValue(nama : String){
        dataStore.updateData { pref->
            pref.toBuilder().setName(nama).build()
        }
    }
    suspend fun hapus(){
        dataStore.updateData { pref->
            pref.toBuilder().clearName().build()
        }
    }
}