/**
 * DATA ENTRY CLASS
 * Author: Yelyzaveta Andryushchenko
 *
 * The class contains information about a single data
 * entry from the JSON file, its fields such as name,
 * id, and listId
 */

package com.yelyzavetaandryu.fetchinterview

class DataEntry(Name: String, ListID: Int, ID: Int) {
    var name: String = Name
    private var listId: Int = ListID
    var id: Int = ID
}