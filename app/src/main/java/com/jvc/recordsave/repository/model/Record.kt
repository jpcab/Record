package com.jvc.recordsave.repository.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Jp Cabrera on 3/12/2021.
 */

@Parcelize
@Entity(tableName = "record_table")
data class Record(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String,
    var address: String
) : Parcelable