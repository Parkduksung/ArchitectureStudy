package com.exam.elevenstreet.data.source.remote

import com.exam.elevenstreet.network.RetrofitInstance
import com.exam.elevenstreet.network.api.ElevenStreetApi
import com.example.elevenstreet.ProductResponse
import com.example.elevenstreet.ProductXmlPullParserHandler
import java.net.URL

class ProductRemoteDataSource {

    private var elevenStreetApi: ElevenStreetApi? = null

    fun getProductlist(Keyword: String, callback: (productList: List<ProductResponse>) -> Unit) {

        elevenStreetApi =
            RetrofitInstance.getInstance<ElevenStreetApi>("https://openapi.11st.co.kr/openapi/")
        val call = elevenStreetApi?.getProductList(
            API_KEY,
            API_CODE,
            Keyword,
            1
        )

        val url: String? = "${call?.request()?.url()}"



        Thread(Runnable {
            val targetURL = URL(url)
            val inputStream = targetURL.openStream()
            ProductXmlPullParserHandler().parse(inputStream) { productList ->
                callback(productList)
            }

        }).start()


    }


companion object {

    const val API_CODE = "ProductSearch"
    const val API_KEY = "1419942f67713eca9cfbb9a752cac395"
}


}

//
//
//private fun htmlParser(url1: String) {
//
//    Thread(Runnable {
//        val url = URL(url1)
//
//        val inputStream = url.openStream()
//        val productList = ProductXmlPullParserHandler().parse(inputStream)
//
//        Log.d("asdfasdf", "${productList.size}")
//
//
//        runOnUiThread {
//            recyclerview_product.run {
//                var adapter =
//                    ProductAdapter(productList as ArrayList<ProductResponse>)
//                this.adapter = adapter
//                layoutManager = LinearLayoutManager(this@ProductActivity)
//            }
//        }
//
//
//    }).start()
//}