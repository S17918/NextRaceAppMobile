package nextrace.app.api

import nextrace.app.models.Category
import nextrace.app.models.Race
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RaceApi {
    @GET("/api/races")
    fun getRaces(): Call<List<Race>>

    @GET("/api/categories")
    fun getCategories(): Call<List<Category>>

    @GET("/api/races/category/Formula 1")
    fun getRacesFormula1(): Call<List<Race>>

    @GET("/api/races/category/Formula 2")
    fun getRacesFormula2(): Call<List<Race>>

    @GET("/api/races/category/Formula 3")
    fun getRacesFormula3(): Call<List<Race>>

    @GET("/api/races/category/Formula E")
    fun getRacesFormulaE(): Call<List<Race>>

    @GET("/api/races/category/Deutsche Tourenwagen Masters")
    fun getRacesDTM(): Call<List<Race>>

    @GET("/api/races/category/{category}")
    fun getRacesByCategoryName(@Path(value = "category", encoded = true) value: String): Call<List<Race>>

    @GET("/api/races/country/{country}")
    fun getRacesByCountryName(@Path(value = "country", encoded = true) value: String): Call<List<Race>>

    @GET("/api/races/track/{name}")
    fun getRacesByTrackName(@Path(value = "name", encoded = true) value: String): Call<List<Race>>

    @GET("/api/races/{year}/{month}")
    fun getRacesByDate(@Path(value = "year", encoded = true) year: String, @Path(value = "month", encoded = true) month: String): Call<List<Race>>
}