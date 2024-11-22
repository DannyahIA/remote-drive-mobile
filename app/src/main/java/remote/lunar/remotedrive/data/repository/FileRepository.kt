package remote.lunar.remotedrive.data.repository

import kotlinx.coroutines.flow.Flow
import remote.lunar.remotedrive.data.local.dao.FileItemDao
import remote.lunar.remotedrive.data.model.FileItem
import remote.lunar.remotedrive.data.remote.RemoteDriveApi.fetchFiles

class FileRepository(private val dao: FileItemDao) {

    fun getAllFiles(): Flow<List<FileItem>> = dao.getAllFileItems()

    suspend fun insertFiles(files: List<FileItem>) = dao.insertsFileItems(files)

    suspend fun deleteFile(file: FileItem) = dao.removeFileItem(file)

    suspend fun syncWithServer() {
        val remoteFiles = fetchFiles()
        dao.clearFileItems()
        dao.insertsFileItems(remoteFiles)
    }
}