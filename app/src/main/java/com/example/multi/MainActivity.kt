package com.example.multi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.multi.details.PostsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.wtf("TAG", "TAG")
        val fragmentManager = supportFragmentManager
        if(fragmentManager.findFragmentById(R.id.fragment_view)==null){

            fragmentManager.commit {
                add<PostsFragment>(R.id.fragment_view, FRAGMENT_POSTS)
            }
        }

    }
    private companion object{
        private const val FRAGMENT_POSTS = "posts"
    }
    }
