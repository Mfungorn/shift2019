package com.example.app.core.modules;

import android.content.SharedPreferences;
import com.example.app.core.BasicAuthInterceptor;
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public class ApiModule {
    private static final String BASE_URL = "http://shiftbanquet.herokuapp.com/";

    public ApiModule() {
    }

    @Singleton
    @Provides
    Retrofit provideApiModule(SharedPreferences prefs) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(createClient(prefs))
                .build();
    }

    private OkHttpClient createClient(SharedPreferences prefs) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor(prefs);

        builder.addInterceptor(authInterceptor);
        builder.addNetworkInterceptor(logInterceptor);

        return builder.build();


    }
}
