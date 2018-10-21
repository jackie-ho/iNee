package hackathon.money2020.com.inee

import io.reactivex.Observable
import retrofit2.http.POST

/**
 * Created by JH on 10/20/18.
 */

const val BASE = "ubvfy6spi.execute-api.us-east-1.amazonaws.com/"

interface Api {

    @POST("alpha/one-ring")
    fun submit(): Observable<Submit>
}