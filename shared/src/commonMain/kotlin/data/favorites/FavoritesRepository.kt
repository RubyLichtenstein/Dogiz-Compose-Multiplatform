package data.favorites

import domain.favorites.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

//class FavoritesRepositoryImpl constructor(
//    private val imagesDao: DogImageDao
//) : FavoritesRepository {
//
//    override val favoriteImagesFlow: Flow<List<DogImage>> =
//        imagesDao.getFavoriteDogImages().map { it.map { it.toDogImageEntity() } }
//
//    override suspend fun updateFavoriteStatus(url: String, isFavorite: Boolean) {
//        withContext(Dispatchers.IO) {
//            imagesDao.updateFavoriteStatus(
//                url = url,
//                isFavorite = isFavorite
//            )
//        }
//    }
//}


