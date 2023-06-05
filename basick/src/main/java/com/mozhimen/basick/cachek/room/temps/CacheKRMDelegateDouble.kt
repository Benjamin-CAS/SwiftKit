package com.mozhimen.basick.cachek.room.temps

import com.mozhimen.basick.cachek.bases.BaseCacheKDelegateDouble
import com.mozhimen.basick.cachek.datastore.helpers.CacheKDSProvider
import com.mozhimen.basick.cachek.room.CacheKRM


/**
 * @ClassName CacheKSPDelegate
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/3/13 15:17
 * @Version 1.0
 */
class CacheKRMDelegateDouble(
    cacheKRMProvider: CacheKRM, key: String, default: Double = 0.0
) : BaseCacheKDelegateDouble<CacheKRM>(cacheKRMProvider, key, default)