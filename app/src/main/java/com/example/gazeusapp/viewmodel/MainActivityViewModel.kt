package com.example.gazeusapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gazeusapp.usecase.RepoUserUseCase
import com.example.gazeuslibrary.models.UserRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel(
    private val repoUserUseCase: RepoUserUseCase,
    private val dispatcher : CoroutineContext = Dispatchers.Main + SupervisorJob()
) : ViewModel() {

    private val homeMutable = MutableLiveData<Home>()
    val homeLiveData: LiveData<Home> = homeMutable


    fun getRepoNameUser(nameUser : String) {

         viewModelScope.launch(dispatcher) {

             repoUserUseCase.invoke(nameUser) { success , data ->

                 if(success) {
                     Home.Success(data).run()
                 } else {
                     Home.Error.run()
                 }
             }
         }
    }

    private fun Home.run() {
        homeMutable.postValue(this)
    }

    sealed class Home {
        class Success(val items: List<UserRepos>?) : Home()
        object Error : Home()
    }
}