package binar.andlima.datastoreproto2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(application : Application): AndroidViewModel(application) {

    private val repository = ProtoRepository(application)

    val data = repository.readProto.asLiveData()

    fun update(name : String) = viewModelScope.launch {
        repository.updateValue(name)
    }

    fun delet() = viewModelScope.launch {
        repository.hapus()
    }
}