package com.example.feelings

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Feeling::class),version = 1)
public abstract class Feelingdatabase : RoomDatabase(){
    //Create an instance of DAO
    abstract fun feelingDao(): FeelingDao

    companion object{
        //Ensure only one instance of database is created
        @Volatile
        private var INSTANCE : Feelingdatabase? = null

        //Funtion to obtain or create the database
        fun getDatabase(context: Context):Feelingdatabase{
            var tempDB = INSTANCE
            if(tempDB != null){
                return tempDB
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,Feelingdatabase::class.java,
                    "feeling_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}