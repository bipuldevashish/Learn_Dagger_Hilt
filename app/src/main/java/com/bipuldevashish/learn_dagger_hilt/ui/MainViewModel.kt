package com.bipuldevashish.learn_dagger_hilt.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bipuldevashish.learn_dagger_hilt.model.Blog
import com.bipuldevashish.learn_dagger_hilt.repository.MainRepository
import com.bipuldevashish.learn_dagger_hilt.utils.DataState
import kotlinx.coroutines.launch
import javax.sql.StatementEvent

class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle : SavedStateHandle
): ViewModel(){

    private val _dataState: MutableLiveData<DataState<List<Blog>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Blog>>>
    get() = _dataState

    fun setStateEvent(mainStatementEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStatementEvent){
                is MainStateEvent.GetBlogsEvent ->{
                    mainRepository.getBlogs()
                        .onEach{dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                MainStateEvent.None ->{
                    //optional
                }
            }
        }
    }
}

sealed class MainStateEvent{
    object GetBlogsEvent: MainStateEvent()

    object None: MainStateEvent()
}