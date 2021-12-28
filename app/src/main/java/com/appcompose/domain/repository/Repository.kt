package com.appcompose.domain.repository

import com.appcompose.domain.entities.Merchant

class Repository : IRepository{
    //todo:add API things
    override fun getMerchantList(): List<Merchant> {
        return emptyList()
    }
}