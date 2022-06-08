package id.ac.uversrivaldo.ocbc_assignment.Api;

import id.ac.uversrivaldo.ocbc_assignment.Model.BalanceResponse;
import id.ac.uversrivaldo.ocbc_assignment.Model.LoginResponse;
import id.ac.uversrivaldo.ocbc_assignment.Model.TransactionsResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    @POST("login/")
    Call<LoginResponse> userLogin(@Body LoginResponse loginResponse);

    @GET("balance")
    Call<BalanceResponse> balance();

    @GET("transactions")
    Call<TransactionsResponse> transactions();

    @FormUrlEncoded
    @POST("createuser")
    Call<ResponseBody> createUser(
            @Field("username") String username,
            @Field("password") String password
    );

}
