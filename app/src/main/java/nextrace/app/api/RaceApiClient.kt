package nextrace.app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RaceApiClient {
    private val baseURL: String = "http://nextraceapp.com:8888/"
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}