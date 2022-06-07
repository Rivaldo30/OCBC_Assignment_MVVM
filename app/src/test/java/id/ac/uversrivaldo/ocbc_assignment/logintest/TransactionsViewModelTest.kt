package id.ac.uversrivaldo.ocbc_assignment.logintest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.ac.uversrivaldo.ocbc_assignment.Model.BalanceResponse
import id.ac.uversrivaldo.ocbc_assignment.Model.Transaction
import id.ac.uversrivaldo.ocbc_assignment.Model.TransactionsResponse
import id.ac.uversrivaldo.ocbc_assignment.ViewModels.BalanceViewModel
import id.ac.uversrivaldo.ocbc_assignment.ViewModels.TransactionsViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TransactionsViewModelTest {
    private lateinit var transactionViewModel: TransactionsViewModel
    private lateinit var user: Transaction

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        transactionViewModel =
            TransactionsViewModel()
        user = Mockito.mock(Transaction::class.java)
    }

    @Test
    fun getTransactions() { // testing value on liveData
        val userDummy = Transaction()
        userDummy.transactions("sukses", 10.0, "qwdq", "www")
        transactionViewModel.setDataDummy(userDummy)
        val data = LiveDataTestUtil.getValue(transactionViewModel.getmTransaction())
        Assert.assertNotNull(data)
        assertEquals("data null", userDummy, data)
        println("dataDummyTransaction-transactionId: ${data.status}")
        println("dataDummyTransaction-transactionId: ${data.data}")
    }
}