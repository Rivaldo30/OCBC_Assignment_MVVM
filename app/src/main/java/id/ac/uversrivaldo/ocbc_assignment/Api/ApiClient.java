package id.ac.uversrivaldo.ocbc_assignment.Api;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String SHARED_PREF_NAME = "my_shared_pref";

    private static Retrofit getRetrofitNoAuth(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://green-thumb-64168.uc.r.appspot.com/")
                .client(okHttpClient)
                .build();

        return retrofit;

    }

    private static Retrofit getRetrofit(Context ctx){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("Authorization", sharedPreferences.getString("token", null))
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://green-thumb-64168.uc.r.appspot.com/")
                .client(client)
                .build();

        return retrofit;

    }

    public static ApiService getApiServiceNoAuth(){
        ApiService apiService = getRetrofitNoAuth().create(ApiService.class);
        return apiService;
    }

    public static ApiService getApiService(Context ctx){
        ApiService apiService = getRetrofit(ctx).create(ApiService.class);
        return apiService;
    }


}