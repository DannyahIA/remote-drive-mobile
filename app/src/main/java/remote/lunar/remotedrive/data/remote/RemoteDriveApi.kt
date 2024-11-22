package remote.lunar.remotedrive.data.remote

import android.util.Log
import remote.lunar.remotedrive.data.model.FileItem
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

// Definir o Retrofit interface
interface RemoteDriveApiService {

    @POST("items/upload")
    suspend fun uploadFile(@Body fileItem: FileItem): Response<FileItem>

    @GET("items")
    suspend fun fetchFiles(): Response<List<FileItem>>

    @PUT("items/{id}")
    suspend fun updateFile(@Path("id") fileId: String, @Body fileItem: FileItem): Response<FileItem>

    @DELETE("items/{id}")
    suspend fun deleteFile(@Path("id") fileId: String): Response<Void>
}

object RemoteDriveApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://eclipsia.ddns.net:8080/api/")  // Substitua com a URL da sua API
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(RemoteDriveApiService::class.java)

    // Função para criar um arquivo
    suspend fun uploadFile(fileItem: FileItem): FileItem? {
        return try {
            val response = service.uploadFile(fileItem)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("RemoteDriveApi", "Erro ao enviar o arquivo: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("RemoteDriveApi", "Erro: ${e.message}")
            null
        }
    }

    // Função para obter a lista de arquivos
    suspend fun fetchFiles(): List<FileItem> {
        return try {
            val response = service.fetchFiles()
            if (response.isSuccessful) {
                response.body() ?: emptyList()  // Retorna lista vazia caso o corpo seja null
            } else {
                Log.e("RemoteDriveApi", "Erro ao buscar arquivos: ${response.code()}")
                emptyList()  // Retorna uma lista vazia em caso de erro
            }
        } catch (e: Exception) {
            Log.e("RemoteDriveApi", "Erro: ${e.message}")
            emptyList()  // Retorna uma lista vazia em caso de exceção
        }
    }

    // Função para atualizar um arquivo
    suspend fun updateFile(fileId: String, fileItem: FileItem): FileItem? {
        return try {
            val response = service.updateFile(fileId, fileItem)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("RemoteDriveApi", "Erro ao atualizar o arquivo: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("RemoteDriveApi", "Erro: ${e.message}")
            null
        }
    }

    // Função para excluir um arquivo
    suspend fun deleteFile(fileId: String): Boolean {
        return try {
            val response = service.deleteFile(fileId)
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("RemoteDriveApi", "Erro: ${e.message}")
            false
        }
    }
}
