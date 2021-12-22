package com.appcompose.domain.repository

import com.appcompose.domain.entities.Merchant

interface IRepository {
    fun getMerchantList() : List<Merchant>
}