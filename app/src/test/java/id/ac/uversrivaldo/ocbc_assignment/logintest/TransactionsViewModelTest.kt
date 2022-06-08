package id.ac.uversrivaldo.ocbc_assignment.logintest

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.ac.uversrivaldo.ocbc_assignment.Model.Transaction
import id.ac.uversrivaldo.ocbc_assignment.Model.TransactionsResponse
import id.ac.uversrivaldo.ocbc_assignment.ViewModels.TransactionsViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class TransactionsViewModelTest {
    private lateinit var transactionViewModel: TransactionsViewModel
    private lateinit var user: TransactionsResponse

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        transactionViewModel =
            TransactionsViewModel()
        user = Mockito.mock(TransactionsResponse::class.java)
    }

    @Test
    fun getTransactions() { // testing value on liveDat
        val data = ArrayList<Transaction>()
        val dataDummy = Transaction()
        val userDummy = TransactionsResponse()
        dataDummy.transactions("test",100.0,"test","ok")
        data.add(dataDummy)
        userDummy.transactionsResponse("success",data)
        transactionViewModel.setDataDummy(userDummy)
        val liveData = LiveDataTestUtil.getValue(transactionViewModel.getmTransaction())
        Assert.assertNotNull(liveData)
        assertEquals("data null", userDummy, liveData)
        println("dataDummyTransaction-status: ${liveData.status}")
        println("dataDummyTransaction-data: ${liveData.data}")
    }
}