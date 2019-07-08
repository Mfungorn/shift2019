package com.example.app.core.modules;

import com.example.app.core.BasicAuthInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public class ApiModule {
    private static final String BASE_URL = "http://127.0.0.1:8080/";

    public ApiModule() {
    }

    @Singleton
    @Provides
    Retrofit provideApiModule() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(createClient())
                .build();
    }

    private OkHttpClient createClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor();

        builder.addInterceptor(authInterceptor);
        builder.addInterceptor(logInterceptor);

        return builder.build();


    }
}
