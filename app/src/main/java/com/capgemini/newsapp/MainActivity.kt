package com.capgemini.newsapp
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var rView:RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView=findViewById(R.id.rview)
        rView.layoutManager=LinearLayoutManager(this)


        CoroutineScope(Dispatchers.Default).launch {
            val result= NewsInterface.getInstance().getTopHeadlines(
                "in",
                "b8c9f9a4af6441598582c3598f1df5e4")

            withContext(Dispatchers.Main){
                rView.adapter=NewAdapter(result.articles){

                    if(result.articles[it].url!=null){
                        val webIntent=Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(result.articles[it].url)
                        )
                        startActivity(webIntent)
                    }

                }
            }
            Log.d("Main Activity","No. of Articles: ${result.articles.size}")

        }
    }
}