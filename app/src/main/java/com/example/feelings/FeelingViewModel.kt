package com.example.feelings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FeelingViewModel(application: Application): AndroidViewModel(application) {

    private val repository: FeelingRepository
    val allFeelings: LiveData<List<Feeling>>

    init {
        //Get a reference to the DAO
        val feelingDao = Feelingdatabase.getDatabase(application).feelingDao()

        repository = FeelingRepository(feelingDao)
        allFeelings = repository.allFeelings
    }

    //ViewModel to use coroutine to perform long process
    fun insert(feeling: Feeling) = viewModelScope.launch{
        repository.insert(feeling)
    }

}