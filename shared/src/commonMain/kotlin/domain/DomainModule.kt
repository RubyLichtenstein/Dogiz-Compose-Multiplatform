package domain

import domain.breeds.GetBreedsUseCase
import domain.images.GetBreedImagesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetBreedsUseCase(get()) }
    single { GetBreedImagesUseCase(get()) }
}