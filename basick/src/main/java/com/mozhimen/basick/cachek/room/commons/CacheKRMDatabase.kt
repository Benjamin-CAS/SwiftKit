package com.mozhimen.basick.cachek.room.commons

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mozhimen.basick.cachek.room.mos.MCacheKRM
import com.mozhimen.basick.utilk.android.app.UtilKApplication

/**
 * @ClassName CacheDatabase
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2021/12/20 14:59
 * @Version 1.0
 */
@Database(entities = [MCacheKRM::class], version = 1, exportSchema = false)
abstract class CacheKRMDatabase : RoomDatabase() {
    abstract val cacheKDao: ICacheKRMDao

    companion object {
        private val _db: CacheKRMDatabase by lazy {
            Room.databaseBuilder(UtilKApplication.instance.applicationContext, CacheKRMDatabase::class.java, "basick_cachek_rm").enableMultiInstanceInvalidation().allowMainThreadQueries().build()
        }

        @JvmStatic
        fun get(): CacheKRMDatabase =
            _db
    }
}