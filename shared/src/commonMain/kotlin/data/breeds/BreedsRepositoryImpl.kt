package data.breeds

import domain.breeds.data.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BreedsRepositoryImpl constructor(
    private val breedsApi: BreedsApi,
) : BreedsRepository {

    override val breedsFlow: Flow<List<BreedInfoImpl>> = flow {
        val remoteBreeds = breedsApi.getAllBreeds()
        val breeds = BreedInfoImpl.fromMap(remoteBreeds)
        emit(breeds)
    }
}


