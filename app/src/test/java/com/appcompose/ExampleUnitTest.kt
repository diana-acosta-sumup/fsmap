package com.appcompose

import com.appcompose.domain.entities.Merchant
import com.appcompose.domain.entities.Types
import com.appcompose.domain.repository.Repository
import com.appcompose.domain.viewmodel.MapViewModel
import com.google.android.libraries.maps.model.LatLng
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    lateinit var viewModel: MapViewModel
    @Mock
    lateinit var repository : Repository
    @Mock
    lateinit var merchantItem : Merchant

    @Test
    fun getSomeMerchants() {
        viewModel = MapViewModel()
        assertTrue(viewModel.merchants.isNotEmpty())
    }

    @Test
    fun getNoneMerchants() {
        viewModel = MapViewModel()
        assertTrue(viewModel.merchants.isEmpty())
    }


    @Test
    fun testRepo() {
        repository = Repository()
        val item = repository.getMerchantList()[0]
        merchantItem = Merchant(1, LatLng(37.663333332,122.4100528),"Diana Rest", Types.RESTAURANT, "")
        assertNotEquals(item, merchantItem)

    }
}