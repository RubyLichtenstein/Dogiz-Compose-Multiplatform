//package com.rubylichtenstein.data.images
//
//import android.content.Context
//import androidx.room.ColumnInfo
//import androidx.room.Database
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import domain.images.DogImage
//
//@Entity(tableName = "dog_images")
//data class DogImageDataEntity(
//    @PrimaryKey
//    @ColumnInfo(name = "url") val url: String,
//    @ColumnInfo(name = "breed_name") val breedName: String,
//    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
//    @ColumnInfo(name = "breed_key") val breedKey: String
//) {
//    companion object {
//        fun DogImage.fromDogImageEntity(): DogImageDataEntity {
//            return DogImageDataEntity(
//                url = url,
//                breedName = breedName,
//                isFavorite = isFavorite,
//                breedKey = breedKey
//            )
//        }
//
//        fun DogImageDataEntity.toDogImageEntity(): DogImage {
//            return DogImage(
//                url = url,
//                breedName = breedName,
//                isFavorite = isFavorite,
//                breedKey = breedKey
//            )
//        }
//    }
//}
//
//
//@Database(entities = [DogImageDataEntity::class], version = 1, exportSchema = false)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun dogImageDao(): DogImageDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "app_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}