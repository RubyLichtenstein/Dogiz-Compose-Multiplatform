package domain.images

import domain.breeds.BreedEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class GetBreedImagesUseCase constructor(
    private val imagesRepository: ImagesRepository
) {
    operator fun invoke(breedEntity: BreedEntity): Flow<List<DogImage>> {
        return imagesRepository.getImagesByBreed(breedEntity).distinctUntilChanged()
    }
}
