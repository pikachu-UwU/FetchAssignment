/**
 * CARD ITEM CLASS
 * Author: Yelyzaveta Andryushchenko
 *
 * This class contains the information displayed
 * on CardView object of the main screen. Includes
 * listID as well as a list of DataEntry objects
 * each representing a different entry in the original
 * JSON list
 */


package com.yelyzavetaandryu.fetchinterview

class CardItem {
    var title: String
    var listId: Int
    var dataList: ArrayList<DataEntry> = ArrayList<DataEntry>()

    constructor(T: String, id: Int, D: ArrayList<DataEntry>) {
        this.title = T
        this.listId = id
        this.dataList.addAll(D)
    }
}