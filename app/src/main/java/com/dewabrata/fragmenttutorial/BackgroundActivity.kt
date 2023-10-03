package com.dewabrata.fragmenttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.dewabrata.fragmenttutorial.model.ResponseProduct
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import java.util.concurrent.Executors

class BackgroundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())


        val client = AsyncHttpClient()
        val url = "https://fakestoreapi.com/products/1"

        findViewById<Button>(R.id.btnConnect).setOnClickListener(View.OnClickListener {

            client.get(url,object : TextHttpResponseHandler() {
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?
                ) {

                   responseString.let{
                       val response = Gson().fromJson(it,ResponseProduct::class.java)

                      print( response.title)
                   }
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {

                }


            })


        })


        findViewById<Button>(R.id.btnStart).setOnClickListener(View.OnClickListener {
            executor.execute(Runnable {
                try {
                    for (i in 0..10) {
                        Thread.sleep(1000)
                        handler.post(Runnable {
                            findViewById<TextView>(R.id.txtStatus).setText(i.toString())
                        })
                    }
                }catch (e: InterruptedException){
                    e.printStackTrace()
                }
            })


        })
    }
}