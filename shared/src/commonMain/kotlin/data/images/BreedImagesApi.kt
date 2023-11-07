package data.images

import data.KtorHttpClient
import io.ktor.client.request.get

const val BASE_URL = "https://dog.ceo/api/"

interface BreedImagesApi {
    suspend fun getBreedImages(breed: String): List<String>
}

class BreedImagesApiImpl constructor(
    private val client: KtorHttpClient
) : BreedImagesApi {

    /**
     * Fetches a list of image URLs for a given dog breed or sub-breed.
     *
     * @param breed The name of the dog breed or sub-breed in the format "breed/subBreed".
     *              For example, for a sub-breed like "Australian Shepherd", you would use "shepherd/australian".
     *              For a main breed like "bulldog", just use "bulldog".
     *
     * @return ApiResponse<List<String>> A response object containing either a list of image URLs or an error.
     *
     * Usage:
     * 1. To get images for a main breed:
     *      getBreedImages("bulldog")
     * 2. To get images for a sub-breed:
     *      getBreedImages("shepherd/australian")
     */
    override suspend fun getBreedImages(breed: String): List<String> {
        return client.safeApiCall {
            get("${BASE_URL}breed/$breed/images")
        }
    }
}

