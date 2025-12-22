package com.example.fragmentbestpractice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsContentFragment:Fragment(R.layout.news_content_frag)
{
    private lateinit var contentLayout: View
    private lateinit var newsTitle: TextView
    private lateinit var newsContent: TextView

    // 允许 refresh() 早于 onViewCreated() 被调用：先缓存，等 view 就绪后再刷新
    private var pendingTitle: String? = null
    private var pendingContent: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contentLayout = view.findViewById(R.id.contentLayout)
        newsTitle = view.findViewById(R.id.newsTitle)
        newsContent = view.findViewById(R.id.newsContent)
        applyPendingIfAny()
    }

    fun refresh(title: String, content: String) {
        pendingTitle = title
        pendingContent = content

        // 如果 view 还没初始化（lateinit 未赋值），先不动，等 onViewCreated 再刷新
        if (!::contentLayout.isInitialized) return
        applyPendingIfAny()
    }

    private fun applyPendingIfAny() {
        val title = pendingTitle ?: return
        val content = pendingContent ?: return
        contentLayout.visibility = View.VISIBLE
        newsTitle.text = title
        newsContent.text = content
    }
}