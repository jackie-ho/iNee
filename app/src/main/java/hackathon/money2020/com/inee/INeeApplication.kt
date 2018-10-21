package hackathon.money2020.com.inee

import android.app.Application
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by JH on 10/20/18.
 */
class INeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/HelveticaNeue.ttf")

    }

    companion object {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor {
                val request = it.request()
                val newRequest = request.newBuilder()
                    .addHeader("x-api-key", "wKOI1ioZn52ZPO8PqRFCH2th8j2LTNgF8onmgN0L")
                    .build()
                return@Interceptor it.proceed(newRequest)
            })
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE)
            .client(httpClient)
            .build()
            .create(Api::class.java)

    }
}