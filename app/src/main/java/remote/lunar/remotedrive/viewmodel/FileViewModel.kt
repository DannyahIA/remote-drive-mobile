package remote.lunar.remotedrive.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FileViewModel : ViewModel() {
    var fileItems = listOf<String>() // Apenas um exemplo

    fun loadFiles() {
        viewModelScope.launch {
            // Lógica para carregar arquivos
        }
    }
}
