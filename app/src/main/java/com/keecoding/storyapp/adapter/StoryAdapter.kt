package com.keecoding.storyapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StoryAdapter: RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(): RecyclerView.ViewHolder() {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder()
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}