package data.breeds

import data.KtorHttpClient
import io.ktor.client.request.get

const val BASE_URL = "https://dog.ceo/api/"

class BreedsRemoteApi constructor(private val client: KtorHttpClient) {

    suspend fun getAllBreeds(): Map<String, List<String>> {
        return client.safeApiCall {
            get("${BASE_URL}breeds/list/all")
        }
    }
}