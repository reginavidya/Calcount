package org.d3if2126.calcount.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if2126.calcount.model.Diskon
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "reginavidya/calcount.json/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DiskonApiService {
    @GET("static-api.json")
    suspend fun getDiskon(): List<Diskon>
}

object DiskonApi {
    val service: DiskonApiService by lazy {
        retrofit.create(DiskonApiService::class.java)
    }
    fun getDiskonUrl(nama: String): String {
        return "$BASE_URL$nama.jpg"
    }
    enum class ApiStatus { LOADING, SUCCESS, FAILED }
}