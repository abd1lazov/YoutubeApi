package space.abdilazov.youtubeapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import space.abdilazov.youtubeapi.BuildConfig.API_KEY
import space.abdilazov.youtubeapi.`object`.Constant
import space.abdilazov.youtubeapi.`object`.Constant.MAX_RESULT
import space.abdilazov.youtubeapi.model.PlayList
import space.abdilazov.youtubeapi.network.RetrofitClient
import space.abdilazov.youtubeapi.network.YoutubeAPI

class MainViewModel: ViewModel() {

    private var youtubeApi: YoutubeAPI = RetrofitClient.create()

    fun getPlayList(): MutableLiveData<PlayList?> {
        return createCall()
    }

    private fun createCall(): MutableLiveData<PlayList?> {
        val data = MutableLiveData<PlayList?>()

        RetrofitClient.create().getPlayList(Constant.PART, Constant.CHANNEL_ID, API_KEY, MAX_RESULT)
            .enqueue(object: Callback<PlayList> {
                override fun onResponse(call: Call<PlayList>, response: Response<PlayList>) {

                    Log.d("oleo","success:" + response.body())

                    if (response.isSuccessful) data.value = response.body()
                        else{

                        Log.d("tag", "error: " + response.code().toString())
                    }

                }

                override fun onFailure(call: Call<PlayList>, t: Throwable) {
                    Log.d("tag", "failure ${t.localizedMessage}")
//                    404 - не найдено .. 403 - токен истек .. 401 - нет доступа
                }
            })
        return data
    }
}