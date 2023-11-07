package data.images

import domain.breeds.buildDisplayNameFromKey
import domain.images.DogImage
import domain.images.ImagesRepository
import kotlinx.coroutines.flow.Flow

class ImagesRepositoryImpl constructor(
    private val dogBreedApiService: BreedImagesApi,
//    private val imagesDataStore: DogImageDao
) : ImagesRepository {
    override fun getImagesByBreed(breedKey: String): Flow<List<DogImage>> = TODO()
//        getImagesByBreedFromLocal(breedKey)
//            .onStart { fetchAndSave(breedKey) }
//            .distinctUntilChanged()

    private suspend fun fetchAndSave(breedKey: String) {
        val remoteData = getRemoteBreedImages(breedKey)
//        imagesDataStore.insertAll(remoteData.map { it.fromDogImageEntity() })
    }

    private suspend fun getRemoteBreedImages(breedKey: String): List<DogImage> {
        return dogBreedApiService.getBreedImages(breedKey).map { url ->
            DogImage(
                breedName = buildDisplayNameFromKey(breedKey),
                isFavorite = false,
                url = url,
                breedKey = breedKey
            )
        }
    }

//    private fun getImagesByBreedFromLocal(breedKey: String): Flow<List<DogImage>> {
//        return imagesDataStore.getDogImagesByBreedKey(breedKey).map {
//            it.map { entity ->
//                entity.toDogImageEntity()
//            }
//        }
//    }
}



