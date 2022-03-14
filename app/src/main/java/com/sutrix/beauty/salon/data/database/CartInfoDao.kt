package com.sutrix.beauty.salon.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CartInfoDao {
    @Insert
    fun insertUserInfo(data: CartInfo)

    @Query(value = "Select * from userInfo")
    fun getAllData(): List<CartInfo>

    @Query(value = "Select * from userInfo where userName= :name")
    fun getDataByUserName(
        name: String
    ): List<CartInfo>

    @Query("UPDATE userInfo SET name= :name WHERE nid = :id and userName= :user")
    fun updateName(
        id: String,
        name: String,
        user: String
    )

    @Query(value = "Select * from userInfo where name= :name and userName= :userName")
    fun getDataByNameAndUser(
        name: String,
        userName: String
    ): List<CartInfo>
 }