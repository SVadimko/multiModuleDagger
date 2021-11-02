package com.example.multi.details

import androidx.lifecycle.*
import com.example.multi.retrofitapi.MainRemoteData
import com.example.multi.retrofitapi.MainService
import com.example.multi.retrofitapi.ResponsePost
import com.example.multi.retrofitapi.ResponsePostItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Provider

internal class PostsViewModel(
    private val mainService: MainService,
    private val mainRemoteData: MainRemoteData
) : ViewModel() {

/*    val newDetailsComponent:DetailsComponent by lazy{
DaggerDetailsComponent.builder()
    .deps()
    .build()
    }*/

/*    val post = flow<List<ResponsePostItem>> {
       val response = mainService.getPosts()
        performList(response)
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())*/


    /* private fun performList(responsePost: ResponsePost): List<ResponsePostItem> {
         val list: MutableList<ResponsePostItem> = mutableListOf()
         responsePost.forEach {
             list.add(it)
         }
         return list
     }*/

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val mainService: Provider<MainService>,
        private val mainRemoteData: Provider<MainRemoteData>
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == PostsViewModel::class.java)
            return PostsViewModel(mainService.get(), mainRemoteData.get()) as T
        }

    }

    fun getPosts(): LiveData<List<ResponsePostItem>> {
        if (livedata.value.isNullOrEmpty())
            updatePostLD()
        return livedata
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    private fun updatePostLD() {
        viewModelScope.launch {
            mainRemoteData.create()
            var list: MutableList<ResponsePostItem> = mutableListOf()
            withContext(Dispatchers.IO) {
                list = mainService.getPosts() as MutableList<ResponsePostItem>
                //list = mainRemoteData.getPosts() as MutableList<ResponsePostItem>
                /* val response = mainService.getPosts()
                 if(response.isSuccessful){
                     list= (response.body() as MutableList<ResponsePostItem>?)!!
                 }*/
                livedata.postValue(list)
            }
        }
    }

    /*fun parseResponsePost(response: Response<ResponsePost>): List<ResponsePostItem> {
        val parsedResponse:MutableList<ResponsePostItem> = mutableListOf<ResponsePostItem>()
        response.body()?.forEach {
            parsedResponse.add(it)
        }
        return parsedResponse
    }*/

    companion object {
        var livedata = MutableLiveData<List<ResponsePostItem>>()
    }
}