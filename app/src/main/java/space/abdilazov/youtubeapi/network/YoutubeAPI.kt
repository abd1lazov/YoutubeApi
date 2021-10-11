package space.abdilazov.youtubeapi.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import space.abdilazov.youtubeapi.model.PlayList

interface YoutubeAPI {

    @GET("playlists")
    fun getPlayList(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") API_KEY: String,
        @Query("maxResults") maxResult: String
    ): Call<PlayList>
}