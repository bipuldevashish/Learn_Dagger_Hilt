package com.bipuldevashish.learn_dagger_hilt.repository

import com.bipuldevashish.learn_dagger_hilt.api.BlogRetrofit
import com.bipuldevashish.learn_dagger_hilt.api.NetworkMapper
import com.bipuldevashish.learn_dagger_hilt.model.Blog
import com.bipuldevashish.learn_dagger_hilt.room.BlogDao
import com.bipuldevashish.learn_dagger_hilt.room.CacheMapper
import com.bipuldevashish.learn_dagger_hilt.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.concurrent.Flow

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
    ){
        suspend fun getBlogs() = flow {
                emit(DataState.Loading)
                delay(1000)
                try {
                    val networkBlogs = blogRetrofit.get()
                    val blogs = networkMapper.mapFromEntityList(networkBlogs)
                    for (blog in blogs){
                        blogDao.insert(cacheMapper.mapToEntity(blog))
                    }
                    val cachedBlog = blogDao.get()
                    emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlog)))
                }catch (e : Exception){
                    emit(DataState.Error(e))
                }
        }
}