package com.deepaliverma.mvvm.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.deepaliverma.mvvm.data.models.Users
import com.deepaliverma.mvvm.data.repos.GithubRepository
import kotlinx.coroutines.*

class GithubViewModel : ViewModel() {

    val users = MutableLiveData<List<Users>>()

    //VIEW MODEL : HAVE INBUILD SUPPORT FOR COROROUTINES
    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = withContext(Dispatchers.IO) { GithubRepository.getUsers() }
            if (response.isSuccessful) {
                response.body()?.let {
                    //for writing in main thread we use post
                    users.postValue(it)
                }
            }
        }
        /*runIO {

        }*/
    }

    fun SearchUsers(name: String) = liveData(Dispatchers.IO) {

        val response = withContext(Dispatchers.IO) { GithubRepository.searchUsers(name) }
        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.items)
            }
        }

    }


}






//Extension Function for ViewModel scope then we can directly use runIO{}
fun ViewModel.runIO(
    dispatchers: CoroutineDispatcher = Dispatchers.IO,
    function: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(dispatchers) {
        function()
    }
}
