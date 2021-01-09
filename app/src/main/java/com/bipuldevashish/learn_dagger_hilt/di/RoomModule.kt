package com.bipuldevashish.learn_dagger_hilt.di

import android.content.Context
import androidx.room.Room
import com.bipuldevashish.learn_dagger_hilt.room.BlogDao
import com.bipuldevashish.learn_dagger_hilt.room.BlogDatabase
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogdb(@ApplicationContext context: Context): BlogDatabase{
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDao(blogDatabase: BlogDatabase) : BlogDao{
        return blogDatabase.blogDao()
    }
}