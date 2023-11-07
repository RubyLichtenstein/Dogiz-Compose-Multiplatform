package data.images

import domain.breeds.BreedEntity
import domain.breeds.buildDisplayNameFromKey
import domain.images.DogImage
import domain.images.ImagesRepository
import domain.images.buildBreedKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ImagesRepositoryImpl constructor(
    private val dogBreedApiService: BreedImagesApi,
) : ImagesRepository {
    override fun getImagesByBreed(breedEntity: BreedEntity): Flow<List<DogImage>> = flow {
        val breedKey = buildBreedKey(breedEntity.subBreed, breedEntity.breed)
        val value = getRemoteBreedImages(breedKey)
        emit(value)
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
}



