package com.android.wipro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.wipro.viewmodel.FeedViewModel
import com.android.wipro.adapter.FeedAdapter
import com.android.wipro.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.failure_case_layout.view.*

val GIT_URL: String = "url"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: FeedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.loadGithubData("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json")
        init()
        addObservar()
    }

    private fun init() {
        binding.errorLayout.btnRetry.setOnClickListener {
            viewModel.loadGithubData("https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json")
        }
        with(recyclerView) {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = FeedAdapter(emptyList())
        }
    }

    private fun addObservar() {
        viewModel.feedData.observe(this, Observer {
            val adapter = recyclerView?.adapter as FeedAdapter
            adapter.updateData(it)
        })
        viewModel.getTitle.observe(this, Observer {
            title = it
        })
    }

}
