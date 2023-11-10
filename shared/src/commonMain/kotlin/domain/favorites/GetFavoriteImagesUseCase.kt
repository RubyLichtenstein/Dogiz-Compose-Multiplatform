package domain.favorites

import domain.favorites.FavoritesRepository
import domain.images.DogImage
import kotlinx.coroutines.flow.Flow

class GetFavoriteImagesUseCase constructor(
    private val favoritesRepository: FavoritesRepository
) {
    operator fun invoke(): Flow<List<DogImage>> = favoritesRepository.favoriteImagesFlow
}

