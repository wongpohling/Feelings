package com.example.feelings

import androidx.lifecycle.LiveData

class FeelingRepository(private val feelingDao: FeelingDao) {
    //Room execute all queries on a separate thread.

    val allFeelings: LiveData<List<Feeling>> = feelingDao.getFeelings()

    //suspend means do in the background
    suspend fun insert(feeling: Feeling){
        feelingDao.insertFeeling(feeling)
    }
}