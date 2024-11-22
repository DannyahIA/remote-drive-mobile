package remote.lunar.remotedrive.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "file_items")
data class FileItem(
    @PrimaryKey val ItemId: String,
    val OwnerId: String,
    val ItemName: String,
    val ItemPath: String,
    val Type: String,
    val Size: String,
    val UpdateAt: String,
    val CreatedAt: String
)







