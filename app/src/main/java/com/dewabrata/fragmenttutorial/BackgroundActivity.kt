package com.dewabrata.fragmenttutorial

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.dewabrata.fragmenttutorial.model.ResponseProduct
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.gson.Gson
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import java.util.concurrent.Executors

class BackgroundActivity : AppCompatActivity() {

    lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)
        //GPS FusedLocation

        fusedLocationProviderClient = FusedLocationProviderClient(this)

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) !== android.content.pm.PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),100)
        }else{
            getLocation()
        }





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


    @SuppressLint("MissingPermission")
    fun getLocation(){
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            if(it != null){
                findViewById<TextView>(R.id.txtStatus).setText(it.latitude.toString())
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode ==100 && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED){
            getLocation()
        }
    }
}