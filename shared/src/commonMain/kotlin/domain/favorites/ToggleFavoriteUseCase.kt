package domain.favorites

import domain.images.DogImage

class ToggleFavoriteUseCase constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(breedImage: DogImage) {
        favoritesRepository.updateFavoriteStatus(
            breedImage.url, !breedImage.isFavorite
        )
    }
}