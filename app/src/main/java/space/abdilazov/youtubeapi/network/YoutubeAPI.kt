package space.abdilazov.youtubeapi.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import space.abdilazov.youtubeapi.model.PlayList

interface YoutubeAPI {

    @GET("playLists")
    fun getPlayList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") API_KEY: String,
        MAX_RESULT: Int
    ): Call<PlayList>
}