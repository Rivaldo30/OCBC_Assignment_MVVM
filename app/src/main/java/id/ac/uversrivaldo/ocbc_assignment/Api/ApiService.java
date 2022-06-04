package id.ac.uversrivaldo.ocbc_assignment.Api;

import id.ac.uversrivaldo.ocbc_assignment.Model.BalanceResponse;
import id.ac.uversrivaldo.ocbc_assignment.Model.LoginResponse;
import id.ac.uversrivaldo.ocbc_assignment.Model.TransactionsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    @POST("login/")
    Call<LoginResponse> userLogin(@Body LoginResponse loginResponse);

    @GET("balance")
    Call<BalanceResponse> balance();

    @GET("transactions")
    Call<TransactionsResponse> transactions();

}
