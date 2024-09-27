package remote.lunar.remotedrive.data.remote

import okhttp3.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import remote.lunar.remotedrive.data.model.BackupItem
import remote.lunar.remotedrive.data.model.FileItem
import java.io.IOException

val client = OkHttpClient()
var url = "http://lunar-remote.ddns.net:8080"

suspend fun fetchRootFiles(): List<FileItem> {
    return withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("$url/file-manager/list-files") // Correspondente ao seu `ListItemsHandler`
            .get()
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    parseFiles(it) // Função para transformar a resposta JSON em `List<FileItem>`
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}

suspend fun fetchBackupFiles(): List<BackupItem> {
    return withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("$url/file-manager/list-backups") // Correspondente ao seu `ListBackupItemsHandler`
            .get()
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    parseBackupFiles(it) // Função para transformar a resposta JSON em `List<BackupItem>`
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}

suspend fun fetchStarredFiles(): List<FileItem> {
    return withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("$url/file-manager/list-starred") // Endpoint para listar arquivos favoritos
            .get()
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    parseFiles(it) // Reutilizando a mesma função de parse para FileItem
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}

suspend fun fetchTrashFiles(): List<FileItem> {
    return withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("$url/file-manager/list-trash") // Endpoint para listar arquivos na lixeira
            .get()
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    parseFiles(it) // Reutilizando a função para FileItem
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}

suspend fun fetchRecentFiles(): List<FileItem> {
    return withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("$url/file-manager/list-recents") // Endpoint para listar arquivos recentes
            .get()
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    parseFiles(it) // Reutilizando a função de parsing de FileItem
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}

suspend fun fetchSharedFiles(): List<FileItem> {
    return withContext(Dispatchers.IO) {
        val request = Request.Builder()
            .url("$url/file-manager/list-shared") // Endpoint para listar arquivos compartilhados
            .get()
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    parseFiles(it) // Reutilizando o parsing para FileItem
                } ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}

fun parseFiles(responseBody: String): List<FileItem> {
    val jsonArray = JSONArray(responseBody)
    val fileList = mutableListOf<FileItem>()

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val fileItem = FileItem(
            ItemId = jsonObject.getString("item_id"),
            OwnerId = jsonObject.getString("owner_id"),
            ItemName = jsonObject.getString("item_name"),
            ItemPath = jsonObject.getString("item_path"),
            Type = jsonObject.getString("type"),
            Size = jsonObject.getString("size"),
            UpdateAt = jsonObject.getString("updated_at"),
            CreatedAt = jsonObject.getString("created_at"),
        )
        fileList.add(fileItem)
    }
    return fileList
}

fun parseBackupFiles(responseBody: String): List<BackupItem> {
    val jsonArray = JSONArray(responseBody)
    val backupList = mutableListOf<BackupItem>()

    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val backupItem = BackupItem(
            BackupId = jsonObject.getString("backup_id"),
            UserId = jsonObject.getString("user_id"),
            Name = jsonObject.getString("name"),
            Path = jsonObject.getString("path"),
            CreatedAt = jsonObject.getString("created_at")
        )
        backupList.add(backupItem)
    }
    return backupList
}
