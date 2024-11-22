package remote.lunar.remotedrive.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import remote.lunar.remotedrive.data.repository.FileRepository

class FileViewModelFactory(private val repository: FileRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FileViewModel::class.java)) {
            return FileViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido")
    }
}
