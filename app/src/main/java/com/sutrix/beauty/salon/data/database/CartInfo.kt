package com.sutrix.beauty.salon.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userInfo")
data class CartInfo(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var nid: String? = null,
    var name: String? = null,
    var price: String? = null,
    var userName: String? = null,
    var userImage: String? = null
    )
