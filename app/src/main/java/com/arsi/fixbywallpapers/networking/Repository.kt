package com.arsi.fixbywallpapers.networking

import com.arsi.fixbywallpapers.model.BatmanApiResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class Repository @Inject constructor(val remoteDataSource: DataSource) : BaseApiResponse() {

    suspend fun getBatmanImages(): Flow<NetworkResult<BatmanApiResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall { remoteDataSource.getBatmanImages() })
        }.flowOn(Dispatchers.IO)
    }


}