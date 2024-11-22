package remote.lunar.remotedrive.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import remote.lunar.remotedrive.data.model.FileItem
import remote.lunar.remotedrive.data.repository.FileRepository

class FileViewModel(private val repository: FileRepository) : ViewModel() {

    val fileList: StateFlow<List<FileItem>> = repository.getAllFiles()
        .map { it.sortedByDescending { file -> file.UpdateAt } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addFiles(files: List<FileItem>) {
        viewModelScope.launch {
            repository.insertFiles(files)
        }
    }

    fun deleteFile(file: FileItem) {
        viewModelScope.launch {
            repository.deleteFile(file)
        }
    }
}

