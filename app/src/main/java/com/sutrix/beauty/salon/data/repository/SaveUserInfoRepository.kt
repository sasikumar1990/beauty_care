package com.sutrix.beauty.salon.data.repository

import android.content.Context
import com.sutrix.beauty.salon.data.database.AppDatabase
import com.sutrix.beauty.salon.data.database.CartInfo
import com.sutrix.beauty.salon.data.database.CartInfoDao


class SaveCartInfoRepository(
    context: Context
) {
    var db: CartInfoDao = AppDatabase.getInstance(context)?.cartInfoDao()!!

    fun insertUserInfo(data: CartInfo) {
        db.insertUserInfo(data)
    }

    fun getAllUserInfo(): List<CartInfo> {
        return db.getAllData()
    }

    fun updateName(
        id: String,
        name: String,
        user: String
    ) {
        return db.updateName(id, name, user)
    }

    fun getDataByNameAndUser(
        name: String,
        user: String
    ): List<CartInfo> {
        return db.getDataByNameAndUser(name, user)
    }

    // get data by username
    fun getDataByUserName(
        name: String
    ): List<CartInfo> {
        return db.getDataByUserName(name)
    }

}