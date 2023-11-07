package data

import data.breeds.BreedsApi
import data.breeds.BreedsRepositoryImpl
import data.images.ImagesApi
import data.images.ImagesApiImpl
import data.images.ImagesRepositoryImpl
import domain.breeds.data.BreedsRepository
import domain.images.ImagesRepository
import org.koin.dsl.module

val dataModule = module {
    single { KtorHttpClient() }
    single<ImagesApi> { ImagesApiImpl(get()) }
    single { BreedsApi(get()) }
    single<ImagesRepository> { ImagesRepositoryImpl(get()) }
    single<BreedsRepository> { BreedsRepositoryImpl(get()) }
}