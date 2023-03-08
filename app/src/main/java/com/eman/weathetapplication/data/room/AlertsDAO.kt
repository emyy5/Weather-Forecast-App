package com.eman.weathetapplication.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eman.weathetapplication.data.model.AlertData

@Dao
interface AlertsDAO {
    @Query("SELECT * FROM alerts")
    fun storedAllAlert(): LiveData<List<AlertData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlert(alert: AlertData)

    @Delete
    fun deleteAlert(alert: AlertData)
}