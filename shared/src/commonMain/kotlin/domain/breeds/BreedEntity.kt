package domain.breeds

import utils.capitalizeWords

data class BreedEntity(
    val breed: String,
    val subBreed: String?,
) {
    fun displayName(): String {
        val capitalizedBreed = breed.capitalizeWords()
        val capitalizedSubBreed = subBreed?.capitalizeWords()

        return if (capitalizedSubBreed != null) {
            "$capitalizedBreed ($capitalizedSubBreed)"
        } else {
            capitalizedBreed
        }
    }
}