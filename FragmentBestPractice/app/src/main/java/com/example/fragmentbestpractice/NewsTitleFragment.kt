package com.example.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsTitleFragment: Fragment(R.layout.news_title_frag)
{
    private var isTwoPane=false;
    private var contentFragment:NewsContentFragment?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        isTwoPane=requireActivity().findViewById<View?>(R.id.newsContentLayout)!=null
        if(isTwoPane)
        {
            contentFragment=requireActivity().supportFragmentManager.findFragmentById(R.id.newsContentFrag)as?NewsContentFragment
        }
        val recyclerView=view.findViewById<RecyclerView>(R.id.newsTitleRecyclerView)
        recyclerView.adapter=NewsAdapter(getNews())
        recyclerView.layoutManager=LinearLayoutManager(requireContext())
    }
    inner class NewsAdapter(private val newsList: List<News>):RecyclerView.Adapter<NewsAdapter.ViewHolder>()
    {
        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view)
        {
            val newTitle:TextView=view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        {
            val view=LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int)
        {
            val news=newsList[position]
            holder.newTitle.text=news.title
            holder.itemView.setOnClickListener {
                val pos=holder.bindingAdapterPosition
                if(pos==RecyclerView.NO_POSITION)return@setOnClickListener
                val clicked=newsList[pos]
                if(isTwoPane)
                {
                    (contentFragment
                        ?:requireActivity().supportFragmentManager
                            .findFragmentById(R.id.newsContentFrag)as?NewsContentFragment)?.refresh(clicked.title,clicked.content)
                }
                else
                {
                    NewsContentActivity.actionStart(
                        requireContext(),clicked.title,clicked.content
                    )
                }
            }
        }

        override fun getItemCount(): Int
        {
            return newsList.size
        }
    }
    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50){
            val news = News("This is news title $i", getRandomLengthString("This is news content $i. "))
            newsList.add(news)
        }
        return newsList
    }
    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}