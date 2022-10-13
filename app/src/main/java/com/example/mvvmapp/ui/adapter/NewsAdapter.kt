package com.example.mvvmapp.ui.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmapp.R
import com.example.mvvmapp.ui.Article

// working on recyclerViews with diffUtil
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val articleImage : ImageView = itemView.findViewById(R.id.ivArticleImage)
        val source: TextView = itemView.findViewById(R.id.tvSource)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val publishAt: TextView = itemView.findViewById(R.id.tvPublishedAt)
        val description: TextView = itemView.findViewById(R.id.tvDescription)

    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    //Async list Differ
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
       val article = differ.currentList[position]
               holder.itemView.apply {
                   Glide.with(this).load(article.urlToImage).into(holder.articleImage)
                   holder.source.text = article.source.name
                   holder.title.text = article.title
                   holder.publishAt.text = article.publishedAt
                   holder.description.text = article.description
               }
        setOnItemClickListener {
            onItemClickListener?.let { it(article) }
        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    // for the webView of the application onclickListener
    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }
}