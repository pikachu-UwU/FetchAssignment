/**
 * DATA LIST ADAPTER CLASS
 * Author: Yelyzaveta Andryushchenko
 *
 * The class contains a custom extension of RecyclerView
 * Adapter class specifically for DataEntry objects that
 * will be shown inside the cards of the main list. Includes
 * override of standard Adapter methods
 */

package com.yelyzavetaandryu.fetchinterview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.data_view.view.*

class DataListAdapter(
    private var dataList: ArrayList<DataEntry>
) : RecyclerView.Adapter<DataListAdapter.DataViewHolder>() {

    class DataViewHolder(DataView: View) : RecyclerView.ViewHolder(DataView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.data_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataEntry: DataEntry = dataList[position]

        // update data entry in the list:
        holder.itemView.apply {
            data_name.text = dataEntry.name
            data_id.text = "id: ${dataEntry.id.toString()}"
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}