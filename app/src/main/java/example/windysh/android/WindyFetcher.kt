package example.windysh.android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class WindyFetcher {

    private val windyApi: WindyApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.windy.com/api/webcams/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        windyApi = retrofit.create(WindyApi::class.java)
    }

    fun fetchContents(): LiveData<List<WebcamItem>> {
        var responseLiveData: MutableLiveData<List<WebcamItem>> = MutableLiveData()
        val windyRequest: Call<WindyResponse> = windyApi.fetchContents()
        windyRequest.enqueue(object : Callback<WindyResponse> {

            override fun onFailure(call: Call<WindyResponse>, t: Throwable) {
                Log.e("Windy", "Failed to fetch photos", t)
            }

            override fun onResponse(
                call: Call<WindyResponse>,
                response: Response<WindyResponse>
            ) {
                Log.d("Windy", "Response received")
                val windyResponse: WindyResponse? = response.body()
                val webcamResponse: WebcamResponse? = windyResponse?.result
                var webcamItems: List<WebcamItem> = (webcamResponse?.webcamItems
                    ?: mutableListOf<WebcamItem>()) as List<WebcamItem>
                          //  ?: mutableListOf()
                responseLiveData.value = webcamItems
            }
              //  Log.d("WindyResponse",responseLiveData.value.toString())

        })

        return responseLiveData
    }
}

