package com.example.searchsuggestions.ui.address

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchsuggestions.R
import com.example.searchsuggestions.data.response.Address
import com.example.searchsuggestions.ui.address.AddressAdapter.CustomViewHolder

class AddressAdapter(
    private val context: Context,
    list: List<Address>
) : RecyclerView.Adapter<CustomViewHolder>() {
    var mList: List<Address>
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.item_address, parent, false)
        return CustomViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.textViewCity.text = mList[position].city
        holder.textViewId.text = mList[position].id
        holder.textViewAddress.text = mList[position].addressString
        holder.textViewLabel.text = mList[position].label
        holder.textViewLat.text = "Lattitude : " + mList[position].latitude.toString()
        holder.textViewLong.text = "Longitude : " + mList[position].longitude.toString()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class CustomViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var imageViewIcon: ImageView
        var textViewCity: TextView
        var textViewId: TextView
        var textViewAddress: TextView
        var textViewLabel: TextView
        var textViewLat: TextView
        var textViewLong: TextView

        init {
            imageViewIcon =
                itemView.findViewById<View>(R.id.imageView_icon) as ImageView
            textViewCity = itemView.findViewById<View>(R.id.textView_city) as TextView
            textViewId = itemView.findViewById<View>(R.id.textView_id) as TextView
            textViewAddress =
                itemView.findViewById<View>(R.id.textView_address) as TextView
            textViewLabel =
                itemView.findViewById<View>(R.id.textView_label) as TextView
            textViewLat = itemView.findViewById<View>(R.id.textView_lat) as TextView
            textViewLong = itemView.findViewById<View>(R.id.textView_long) as TextView
        }
    }

    init {
        mList = list
    }

    fun setData(list: List<Address>) {
        mList = list
        notifyDataSetChanged()
    }
}