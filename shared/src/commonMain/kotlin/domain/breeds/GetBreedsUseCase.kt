package domain.breeds

import domain.breeds.data.BreedInfo
import domain.breeds.data.BreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import utils.capitalizeWords

class GetBreedsUseCase constructor(
    private val breedsRepository: BreedsRepository
) {

    operator fun invoke(): Flow<List<BreedEntity>> {
        return breedsRepository.breedsFlow.map(::mapInfoToItems)
    }

    private fun mapInfoToItems(
        breedInfoList: List<BreedInfo>
    ): List<BreedEntity> {
        return breedInfoList.flatMap { breed ->
            val subBreedItems = breed.subBreedsNames.map { subBreedName ->
                BreedEntity(
                    breed = breed.name,
                    subBreed = subBreedName,
                )
            }

            listOf(
                BreedEntity(
                    breed = breed.name,
                    subBreed = null,
                )
            ) + subBreedItems
        }
    }
}


fun buildDisplayNameFromKey(breedName: String): String {
    val parts = breedName.split('/')
    val breed = parts[0].capitalizeWords()
    val subBreed = parts.getOrNull(1)?.capitalizeWords()

    return BreedEntity(breed = breed, subBreed = subBreed).displayName()
}
