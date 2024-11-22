package remote.lunar.remotedrive.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import remote.lunar.remotedrive.data.local.AppDatabase
import remote.lunar.remotedrive.data.repository.FileRepository

class SyncWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        // Obtenha instâncias do banco de dados e repositórios
        val database = AppDatabase.getInstance(applicationContext)
        val fileRepository = FileRepository(database.fileItemDao())

        return try {
            // Sincronize arquivos
            fileRepository.syncWithServer()

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }
}