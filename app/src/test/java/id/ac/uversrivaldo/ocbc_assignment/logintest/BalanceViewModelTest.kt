package id.ac.uversrivaldo.ocbc_assignment.logintest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.ac.uversrivaldo.ocbc_assignment.Model.BalanceResponse
import id.ac.uversrivaldo.ocbc_assignment.ViewModels.BalanceViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class BalanceViewModelTest {
    private lateinit var balanceViewModel: BalanceViewModel
    private lateinit var user: BalanceResponse

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        balanceViewModel =
            BalanceViewModel()
        user = Mockito.mock(BalanceResponse::class.java)
    }

    @Test
    fun getBalance() { // testing value on liveData

        val userDummy = BalanceResponse()
        userDummy.balanceResponse("sukses", "12312", 100.0)
        balanceViewModel.setDataDummy(userDummy)
        val data = LiveDataTestUtil.getValue(balanceViewModel.getmBalance())
        Assert.assertNotNull(data)
        assertEquals("data null", userDummy, data)
        println("dataDummyBalance-status: ${data.status}")
        println("dataDummyBalance-accNo: ${data.accountNo}")
        println("dataDummyBalance-balance: ${data.balance}")
    }
}