package domain.images

import domain.breeds.BreedEntity
import kotlinx.coroutines.flow.Flow

interface ImagesRepository {
    fun getImagesByBreed(breedEntity: BreedEntity): Flow<List<DogImage>>
}