package remote.lunar.remotedrive.data.remote

import okhttp3.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import remote.lunar.remotedrive.data.model.FileItem
import java.io.IOException

val client = OkHttpClient()
var url = "http://lunar-remote.ddns.net:8080"

suspend fun fetchRootFiles(): List<FileItem> {
    return withContext(Dispatchers.IO) {
        // Definindo a URL para o endpoint do servidor
        val request = Request.Builder()
            .url("$url/file-manager/list-root-folders")
            .get()
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    parseFiles(it)
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

suspend fun fetchBackupFiles(): List<FileItem> {
    return withContext(Dispatchers.IO) {
        // Definindo a URL para o endpoint do servidor
        val request = Request.Builder()
            .url("$url/file-manager/list-backups")
            .get()
            .addHeader("Content-Type", "application/json")
            .build()

        try {
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                responseBody?.let {
                    parseFiles(it)
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
            Name = jsonObject.getString("name"),
            Path = jsonObject.getString("path"),
            IsFolder = jsonObject.getBoolean("is_folder"),
            Size = jsonObject.getString("size"),
            Extension = jsonObject.optString("extension"),
            DateModified = jsonObject.getString("data_modified")
        )
        fileList.add(fileItem)
    }
    return fileList
}