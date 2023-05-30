/**
 * CUSTOM JSON PARSER CLASS
 * Author: Yelyzaveta Andryushchenko
 *
 * The class contains methods specifically designed
 * to parse the given type of JSON file
 * ("https://fetch-hiring.s3.amazonaws.com/hiring.json")
 * into a nested array of CardItems and DataEntries.
 * The original (empty) list is passed by reference
 * to be updated in the methods of CustomJSONParser
 * class
 */


package com.yelyzavetaandryu.fetchinterview

import android.util.Log
import org.json.JSONArray
import org.json.JSONTokener

class CustomJSONParser {

    private fun buildList(cardList: ArrayList<CardItem>, content: String) {
        cardList.clear()

        // parse json data and group objects by listId
        val jsonArray = JSONTokener(content).nextValue() as JSONArray
        for(i in 0 until jsonArray.length()) {

            // get info
            val id: Int = jsonArray.getJSONObject(i).getInt("id")
            val name: String? = jsonArray.getJSONObject(i).getString("name")
            val listId: Int = jsonArray.getJSONObject(i).getInt("listId")

            // check if name is valid:
            if(!name.isNullOrEmpty() && name != "null") {
                Log.d("PARSER", "valid name \'$name\'")
                // find respective card
                var index = cardList.indexOfFirst { cardItem -> cardItem.listId == listId  }
                // insert if card does not exist
                if(index == -1) {
                    cardList.add(CardItem("ListID $listId", listId, ArrayList<DataEntry>()))
                    index = cardList.size -1
                }
                // add new data entry to the card
                cardList[index].dataList.add(DataEntry("name: \'${name}\'", listId, id))
            }
        }

    }

    fun parseData(cardList: ArrayList<CardItem>, content: String) {
        // build list of data
        buildList(cardList, content)

        // sort by id and name
        for(card in cardList) {
            card.dataList.sortWith( compareBy { it.name } )
        }
        cardList.sortWith(compareBy { it.listId })
    }
}