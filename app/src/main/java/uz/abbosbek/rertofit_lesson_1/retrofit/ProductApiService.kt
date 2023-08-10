package uz.abbosbek.rertofit_lesson_1.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {

    //todo: Api dan ma'lumotni id orqali olib kelish
    @GET("products/{id}")
    suspend fun getProductsById(@Path("id") id: Int): Product


}