/**
 * CARD LIST ADAPTER CLASS
 * Author: Yelyzaveta Andryushchenko
 *
 * The class contains the extension of a recyclerView adapter
 * created for main CardList of the app. Overrides standard
 * adapter methods
 */

package com.yelyzavetaandryu.fetchinterview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view.view.*
import android.util.Log

class CardListAdapter(
    private var CardList: ArrayList<CardItem>
) : RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {

    class CardListViewHolder(cardView: View) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        // initialize main viewholder
        var holder = CardListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card_view,
                parent,
                false
            )
        )

        // for each card, initialize its dataListAdapter
        for(card in CardList) {
            holder.itemView.apply {
                titleSection.text = card.title
                var dataAdapter = DataListAdapter(card.dataList)
                listOfElements.adapter = dataAdapter
                listOfElements.layoutManager = LinearLayoutManager(parent.context)
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        // update the card w/ underlying dataListAdapter
        val card: CardItem = CardList[position]
        holder.itemView.apply {
            titleSection.text = card.title
            var dataAdapter = DataListAdapter(card.dataList)
            listOfElements.adapter = dataAdapter
        }
    }

    override fun getItemCount(): Int {
        return CardList.size
    }


}