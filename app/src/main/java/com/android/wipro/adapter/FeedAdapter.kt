package com.android.wipro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.wipro.R
import com.android.wipro.databinding.FeedRowBinding
import com.android.wipro.model.Row
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FeedAdapter(var dataList: List<Row>) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FeedRowBinding.inflate(inflater)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    fun updateData(list: List<Row>) {
        dataList = list
        notifyDataSetChanged()
    }

    class ViewHolder(val b: FeedRowBinding) : RecyclerView.ViewHolder(b.root) {

        fun bind(data: Row) {
            b.viewModel = data
            b.tvDescription.text = data.description
            b.tvTitle.text = data.title
            val url = data.imageHref?.replace("http", "https", true)
            Glide
                .with(b.root.context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.dummy)
                .into(b.ivCoverImage)
        }
    }

}