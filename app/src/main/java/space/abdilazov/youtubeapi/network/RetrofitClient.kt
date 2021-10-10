package space.abdilazov.youtubeapi.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.abdilazov.youtubeapi.BuildConfig.BASE_URL
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object{
        fun create(): YoutubeAPI {
            val Interceptor = HttpLoggingInterceptor()
            Interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(Interceptor)
                .build()


            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
            return retrofit.create(YoutubeAPI:: class.java)
        }
    }

}