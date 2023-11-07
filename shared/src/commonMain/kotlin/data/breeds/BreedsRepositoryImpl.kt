package data.breeds

import domain.breeds.data.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BreedsRepositoryImpl constructor(
    private val breedsApi: BreedsRemoteApi,
//    private val breedsDataStore: BreedsDataStore
) : BreedsRepository {

    override val breedsFlow: Flow<List<BreedInfoImpl>> = flow {
        // Try fetching from local first
//        val localBreeds = getBreedsFromLocal()
//        if (localBreeds != null) {
//            emit(localBreeds)
//        }

        // Then always fetch from remote
        try {
            val remoteBreeds = breedsApi.getAllBreeds()
            val breeds = BreedInfoImpl.fromMap(remoteBreeds)
//            breedsDataStore.save(breeds)
            emit(breeds)
        } catch (exception: Exception) {
//            if (localBreeds == null) {
//                throw exception
        }
    }
}

//    private suspend fun getBreedsFromLocal(): List<BreedInfoImpl>? {
//        return breedsDataStore.get.firstOrNull()
//    }
//}


