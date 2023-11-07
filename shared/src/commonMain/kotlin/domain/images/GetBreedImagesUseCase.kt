package domain.images

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class GetBreedImagesUseCase constructor(
    private val imagesRepository: ImagesRepository
) {
    operator fun invoke(breedKey: String): Flow<List<DogImage>> {
        return imagesRepository.getImagesByBreed(breedKey).distinctUntilChanged()
    }
}
