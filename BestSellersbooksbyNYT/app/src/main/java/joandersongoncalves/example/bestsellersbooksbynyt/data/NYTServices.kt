package joandersongoncalves.example.bestsellersbooksbynyt.data

import joandersongoncalves.example.bestsellersbooksbynyt.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTServices {
    @GET("lists.json")
    fun getBooks(
        @Query("api-key") apiKey: String = "YpL4dehuDKPUCmQn3iCqmvLoOv7Iux1G",
        @Query("list") list: String = "hardcover-fiction"
    ): Call<BookBodyResponse>
}