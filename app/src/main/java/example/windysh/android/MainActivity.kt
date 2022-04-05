package example.windysh.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



       setContentView(R.layout.activity_main)

        val windyLiveData: LiveData<List<WebcamItem>> = WindyFetcher().fetchContents()
        windyLiveData.observe(
            this,
            Observer { webcamItems ->
                Log.d("WindyResponseInMainActivity", "Response received: $webcamItems")
            })
    }


}