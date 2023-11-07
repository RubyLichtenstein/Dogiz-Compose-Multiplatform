package domain.images

import domain.images.DogImage
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    fun getImagesByBreed(breedKey: String): Flow<List<DogImage>>
}