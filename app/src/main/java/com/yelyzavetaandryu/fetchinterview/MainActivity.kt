/**
 * MAIN ACTIVITY CLASS
 * Author: Yelyzaveta Andryushchenko
 *
 * The class contains main logic behind URL fetching
 * and screen update for the received information. Uses
 * multithreading to offload the cost of network request
 * onto a different thread (does not overload UI thread)
 * as information is received, updates the main screen
 * and displays the list of data parsed from received JSON
 * file
 */


package com.yelyzavetaandryu.fetchinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONTokener
import java.net.URL
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var cardAdapter: CardListAdapter
    private lateinit var cardList: ArrayList<CardItem>
    private var isPressed: Boolean = false
    private var handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        //
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingCircle.visibility = View.GONE

        // setup empty list and adapter for future use
        cardList = ArrayList()
        cardAdapter = CardListAdapter(cardList)
        mainCardList.adapter = cardAdapter
        mainCardList.layoutManager = LinearLayoutManager(this)


        // fetch data button code for retrieving the data
        button.setOnClickListener {
            Log.d("BUTTON", "entered the listener code")

            // create thread to wait for info:
            if(!isPressed) {
                // remove the fetch button
                button.visibility = View.GONE
                isPressed = true

                // start network request
                thread {
                    // display loading circle
                    handler.post { loadingCircle.visibility = View.VISIBLE }

                    // fetch data
                    val url = URL("https://fetch-hiring.s3.amazonaws.com/hiring.json")
                    val content = url.readText()
                    CustomJSONParser().parseData(cardList, content)

                    // show list and hide load circle
                    handler.post {
                        loadingCircle.visibility = View.GONE
                        cardAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}