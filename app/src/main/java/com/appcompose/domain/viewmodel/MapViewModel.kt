package com.appcompose.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcompose.domain.entities.Merchant
import com.appcompose.domain.repository.Repository
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {
    val repository = Repository()
    private var merchantList : List<Merchant> by mutableStateOf(listOf())
    val merchants: List<Merchant>
        get() = merchantList
    var errorMessage: String by mutableStateOf("")

    @JvmName("getMerchantList1")
    fun getMerchantList(): List<Merchant> {
        viewModelScope.launch {
            try {
                val repoCall = repository.getMerchantList()
                 merchantList = repoCall
                return@launch
            } catch (e: Exception) {
                merchantList = emptyList()
                errorMessage = e.message.toString()
            }
            return@launch
        }
        return merchantList
    }

    fun getNoneMerchantList(): List<Merchant> {
        return emptyList()
    }
}