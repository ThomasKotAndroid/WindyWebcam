package example.windysh.android

import retrofit2.Call
import retrofit2.http.GET

interface WindyApi {

    @GET(
        "list/" +
                "?key=your_api_key_here"
    )

    fun fetchContents(): Call<WindyResponse>

}