package com.appcompose.domain.repository

import com.appcompose.domain.entities.Merchant
import com.appcompose.domain.entities.Types
import com.google.android.libraries.maps.model.LatLng

class Repository : IRepository{
    //todo:add API things
    override fun getMerchantList(): List<Merchant> {
        val merchantList = mutableListOf<Merchant>()
        var locationList = listOf(LatLng(37.787926120907294, -122.43135528364044),
            LatLng(37.798371299840774, -122.40886764356485), LatLng(37.75527571252098, -122.439004836663884),
            LatLng(37.75863455257095, -122.4388971038711), LatLng(37.76093318999071, -122.43433734860767),)
        for (i in 1..5) {
            val newMerchant = Merchant(i, locationList[i], "Merchant$i", Types.RESTAURANT, "")
            merchantList.add(i, newMerchant)
        }
        return merchantList
    }
}