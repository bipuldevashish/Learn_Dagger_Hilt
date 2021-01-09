package com.bipuldevashish.learn_dagger_hilt.di

import com.bipuldevashish.learn_dagger_hilt.api.BlogRetrofit
import com.bipuldevashish.learn_dagger_hilt.api.NetworkMapper
import com.bipuldevashish.learn_dagger_hilt.repository.MainRepository
import com.bipuldevashish.learn_dagger_hilt.room.BlogDao
import com.bipuldevashish.learn_dagger_hilt.room.CacheMapper
import dagger.Provides
import javax.inject.Singleton

object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao : BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ) : MainRepository{
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
    }
}