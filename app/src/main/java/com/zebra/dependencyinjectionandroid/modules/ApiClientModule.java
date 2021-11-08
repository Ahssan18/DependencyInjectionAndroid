package com.zebra.dependencyinjectionandroid.modules;

import com.zebra.dependencyinjectionandroid.BuildConfig;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okio.Timeout;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiClientModule {
    String BASE_URL;

//    public ApiClientModule(String BASE_URL) {
//        this.BASE_URL = BASE_URL;
//    }

    @Singleton
    Interceptor getInterceptor()
    {
       return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request().newBuilder()
                        .addHeader("file","hy")
                        .build();
                Response response=chain.proceed(request);
                return response;
            }
        };
    }

    OkHttpClient okHttpClient=new OkHttpClient.Builder().
            callTimeout(3000, TimeUnit.MILLISECONDS)
            .addInterceptor(getInterceptor()).build();

    @Singleton
    @Provides
    Retrofit getClient()
    {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
//                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
