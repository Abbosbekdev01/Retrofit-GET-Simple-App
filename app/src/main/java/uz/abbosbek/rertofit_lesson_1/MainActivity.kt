package uz.abbosbek.rertofit_lesson_1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.abbosbek.rertofit_lesson_1.databinding.ActivityMainBinding
import uz.abbosbek.rertofit_lesson_1.retrofit.ProductApiClient

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val productApi = ProductApiClient.productApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


//        val BASE_URL = "https://dummyjson.com"
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val productApi = retrofit.create(ProductApi::class.java)


        binding.apply {

            btnGet.setOnClickListener {
                val temp = edtId.text.toString()
                var id = -1
                if (temp.isNotEmpty()) {
                    id = temp.toInt()
                    CoroutineScope(Dispatchers.IO).launch {
                        val product = productApi.getProductsById(id)

                        runOnUiThread {
                            tv1.text = product.title
                            tv2.text = product.brand
//                            Picasso.get().load(product.images[1]).into(image)
                            if (product.images.isNotEmpty()) {
                                Glide.with(this@MainActivity).load(product.images[random()])
                                    .placeholder(R.drawable.ic_launcher_background).into(image)
                            } else {
                                image.setImageResource(R.drawable.baseline_broken_image_24)
                            }
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity, "id kiriting", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun random(): Int {
        return (1..5).random()
    }
}