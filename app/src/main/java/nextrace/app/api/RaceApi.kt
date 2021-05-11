package nextrace.app.api

import nextrace.app.models.Race
import retrofit2.Call
import retrofit2.http.GET

interface RaceApi {
    @GET("/api/races")
    fun getRaces(): Call<List<Race>>
}