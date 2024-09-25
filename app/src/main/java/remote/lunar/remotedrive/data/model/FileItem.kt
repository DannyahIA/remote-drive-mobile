package remote.lunar.remotedrive.data.model

data class FileItem(
    val Name: String,
    val Path: String,
    val IsFolder: Boolean,
    val Size: String,
    val Extension: String?,
    val DateModified: String
)
