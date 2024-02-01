package com.capgemini.newsapp

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.joinAll

class NewAdapter(val newsList: List<News>, val onSelection:(Int)->Unit):RecyclerView.Adapter<NewAdapter.NewHolder>() {

    inner class NewHolder(inflated: View):RecyclerView.ViewHolder(inflated){
        val img:ImageView=inflated.findViewById(R.id.img)
        val titleTextView:TextView=inflated.findViewById(R.id.txtTitle)
        val dateTextView:TextView=inflated.findViewById(R.id.txtDate)
        val authorTextView:TextView=inflated.findViewById(R.id.txtAuthor)
        val descTextView:TextView=inflated.findViewById(R.id.txtDescription)

        init {
            itemView.setOnClickListener{
                onSelection(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        //inflate item xml and return viewholder
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)

        return NewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        //bind

        val news=newsList[position]

        holder.authorTextView.text=news.author
        holder.dateTextView.text=news.publishedAt
        holder.descTextView.text=news.description
        holder.titleTextView.text=news.title

        news.urlToImage?.let {
            Glide.with(holder.itemView).load(it).into(holder.img)
        }

    }
}