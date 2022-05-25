package id.ac.uversrivaldo.ocbc_assignment.Api;

import id.ac.uversrivaldo.ocbc_assignment.Response.BalanceResponse;
import id.ac.uversrivaldo.ocbc_assignment.LoginRequest;
import id.ac.uversrivaldo.ocbc_assignment.Response.LoginResponse;
import id.ac.uversrivaldo.ocbc_assignment.Response.TransactionsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    @POST("login/")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @GET("balance")
    Call<BalanceResponse> balance();

    @GET("transactions")
    Call<TransactionsResponse> transactions();

}
