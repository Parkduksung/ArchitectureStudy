package com.exam.elevenstreet.view.product


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.elevenstreet.R
import com.exam.elevenstreet.data.source.local.ProductLocalDataSource
import com.exam.elevenstreet.data.source.remote.ProductRemoteDataSource
import com.exam.elevenstreet.view.product.adapter.ProductAdapter
import com.example.elevenstreet.ProductResponse
import kotlinx.android.synthetic.main.activity_main.*


class ProductActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ProductLocalDataSource()
            .getProductlist("ElevenStreetOpenApiService.xml"){ productList ->

            recyclerview_product.run {
                var adapter =
                    ProductAdapter(productList as ArrayList<ProductResponse>)
                recyclerview_product.adapter = adapter
                layoutManager = LinearLayoutManager(this@ProductActivity)

            }

        }

        search_button.setOnClickListener {

            var keyword  = "${search_text.text}"
            ProductRemoteDataSource()
                .getProductlist(keyword){ productList ->
                runOnUiThread {
                    recyclerview_product.run {
                        var adapter =
                            ProductAdapter(productList as ArrayList<ProductResponse>)
                        this.adapter = adapter
                        layoutManager = LinearLayoutManager(this@ProductActivity)
                    }
                }

            }

        }


    }

    companion object {
//        private const val TAG = "ProductActivity"

    }
}
