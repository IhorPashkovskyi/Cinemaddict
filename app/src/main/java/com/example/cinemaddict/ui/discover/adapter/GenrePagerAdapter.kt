package com.example.cinemaddict.ui.discover.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cinemaddict.R
import com.example.cinemaddict.component.GradientTextView
import com.example.cinemaddict.domain.entity.GenreData
import com.example.cinemaddict.ui.discover.film.FilmFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class GenrePagerAdapter @Inject constructor(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle), TabLayoutMediator.TabConfigurationStrategy {

    private val genres: MutableList<GenreData> = mutableListOf()

    fun setGenres(genresList: List<GenreData>) {
        genres.clear()
        genres.addAll(genresList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun createFragment(position: Int): Fragment {
        return FilmFragment.newInstance(genres[position].name)
    }

    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        val customView = LayoutInflater.from(tab.view.context).inflate(
            R.layout.custome_tab_item,
            tab.view as ViewGroup,
            false
        ) as GradientTextView
        customView.text = genres[position].name
        tab.customView = customView
    }
}