package com.example.multi.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

internal class PostDetailsComponentViewModel(application: Application): AndroidViewModel(application) {
    val postDetailsComponent:DetailsComponent by lazy{
        DaggerDetailsComponent.builder()
            .deps(application.detailsDepProvider.dep)
            .build()
    }

}