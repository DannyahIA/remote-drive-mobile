package remote.lunar.remotedrive.data.model

data class FileItem(
    val ItemId: String,
    val OwnerId: String,
    val ItemName: String,
    val ItemPath: String,
    val Type: String,
    val Size: String,
    val UpdateAt: String,
    val CreatedAt: String
)

data class TrashItem(
    val TrashId: String,
    val ItemId: String,
    val UserId: String,
    val Path: String,
    val OldPath: String,
    val CreatedAt: String
)

data class StarredItem(
    val StarredId: String,
    val ItemId: String,
    val UserId: String,
    val MarkedAt: String
)

data class BackupItem(
    val BackupId: String,
    val UserId: String,
    val Name: String,
    val Path: String,
    val CreatedAt: String
)

data class RecentItem(
    val RecentId: String,
    val ItemId: String,
    val UserId: String,
    val AcessedAt: String
)
