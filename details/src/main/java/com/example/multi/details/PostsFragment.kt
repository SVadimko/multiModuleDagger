package com.example.multi.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.multi.details.databinding.FragmentPostsListBinding
import dagger.Lazy
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class PostsFragment : Fragment(R.layout.fragment_posts_list) {

    @Inject
    internal lateinit var postViewModelFactory: Lazy<PostsViewModel.Factory>

    private val postViewModel: PostsViewModel by viewModels {
        postViewModelFactory.get()
    }
    private val detailsComponentViewModel: PostDetailsComponentViewModel by viewModels()
    private var adapter: PostsRecyclerViewAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        detailsComponentViewModel.postDetailsComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Log.wtf("onViewCreated", "onViewCreated")
        val postAdapter = PostsRecyclerViewAdapter()
        this.adapter = postAdapter


        /* lifecycleScope.launchWhenStarted {
             postViewModel.post.collect {
                 adapter?.submitList(it)
             }
         }*/

        val binding = FragmentPostsListBinding.bind(view)
        binding.list.adapter = postAdapter
        binding.list.layoutManager = LinearLayoutManager(context)
        /* with(binding.list) {
             layoutManager = LinearLayoutManager(context)
             this.adapter = postAdapter
         }*/
        postViewModel.getPosts().observe(viewLifecycleOwner, { list ->
            /*  val binding = FragmentPostsListBinding.bind(view)
              with(binding.list) {
                  layoutManager = LinearLayoutManager(context)
                  this.adapter = postAdapter
              }*/
            adapter?.apply {
                submitList(list)
               // notifyDataSetChanged()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }


}