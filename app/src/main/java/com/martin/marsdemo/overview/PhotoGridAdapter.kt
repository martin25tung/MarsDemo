package com.martin.marsdemo.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.martin.marsdemo.databinding.GridViewItemBinding
import com.martin.marsdemo.network.MarsProperty

class PhotoGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<MarsProperty, PhotoGridAdapter.MarsPropertyViewHolder>(object : DiffUtil.ItemCallback<MarsProperty>(){
    override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
        return oldItem.id == newItem.id
    }

}){

    class MarsPropertyViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(property: MarsProperty) {
            binding.property = property
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarsPropertyViewHolder {
        return MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    class OnClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
        fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
    }

}