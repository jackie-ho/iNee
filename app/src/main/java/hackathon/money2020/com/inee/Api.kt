package hackathon.money2020.com.inee

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by JH on 10/20/18.
 */

const val BASE = "https://cubvfy6spi.execute-api.us-east-1.amazonaws.com/"

interface Api {

    @POST("alpha/one-ring/")
    fun submit(): Observable<Submit>

    @GET("alpha/one-ring/")
    fun balances(@Query("q") balance: String): Observable<Balance>
}