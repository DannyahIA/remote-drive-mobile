package remote.lunar.remotedrive.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import remote.lunar.remotedrive.data.model.FileItem

@Dao
interface FileItemDao {
    @Insert
    suspend fun insertsFileItems(items: List<FileItem>)

    @Query("SELECT * FROM file_items")
    fun getAllFileItems(): Flow<List<FileItem>>

    @Query("SELECT * FROM file_items WHERE ItemId = :itemId")
    suspend fun getFileItemById(itemId: String): FileItem?

    @Query("DELETE FROM file_items")
    suspend fun clearFileItems()

    @Delete
    suspend fun removeFileItem(item: FileItem)

    @Update
    suspend fun updateFileItem(item: FileItem)
}