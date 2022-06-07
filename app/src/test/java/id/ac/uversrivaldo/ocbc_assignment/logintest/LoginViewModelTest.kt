package id.ac.uversrivaldo.ocbc_assignment.logintest

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.ac.uversrivaldo.ocbc_assignment.Model.LoginResponse
import id.ac.uversrivaldo.ocbc_assignment.ViewModels.LoginViewModel
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user: LoginResponse

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        loginViewModel =
            LoginViewModel()
        user = Mockito.mock(LoginResponse::class.java)
    }

    @Test
    fun getUserLogin() { // testing value on liveData
        val userDummy = LoginResponse()
         userDummy.loginResponse("rivaldo","123","test","123123","000","Nothing",900)
        loginViewModel.setDataDummy(userDummy)
        val data = LiveDataTestUtil.getValue(loginViewModel.dataLogin)
        Assert.assertNotNull(data)
        assertEquals("data null", userDummy, data)
        println("dataDummyLogin-accNo: ${data.username}")
        println("dataDummyLogin-username: ${data.password}")
        println("dataDummyLogin-password: ${data.status}")
        println("dataDummyLogin-status: ${data.accountNo}")
        println("dataDummyLogin-token: ${data.token}")
        println("dataDummyLogin-balance: ${data.balance}")
    }
}