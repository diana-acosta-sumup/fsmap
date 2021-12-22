package com.appcompose.domain.entities

import com.google.android.libraries.maps.model.LatLng

data class Merchant(val id: Int, val location:LatLng, val name:String, val type:String, val address:String)
