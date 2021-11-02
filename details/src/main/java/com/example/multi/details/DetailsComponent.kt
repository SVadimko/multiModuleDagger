package com.example.multi.details

import android.app.Application
import android.content.Context
import com.example.multi.retrofitapi.MainRemoteData
import com.example.multi.retrofitapi.MainService
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Scope
internal annotation class DetailsScope

@[DetailsScope Component(dependencies = [DetailsDep::class], modules = [DetailsModule::class])]
interface DetailsComponent {
    fun inject(fragment: PostsFragment)

    @Component.Builder
    interface Builder {

        fun deps(detailsDep: DetailsDep):Builder

        fun build():DetailsComponent
    }

}

@Module
internal class DetailsModule{

}

interface DetailsDepProvider{

    val dep:DetailsDep
}

interface DetailsDep {
    val mainService: MainService
    val mainRemoteData: MainRemoteData
}

val Context.detailsDepProvider:DetailsDepProvider
get() = when(this){
    is DetailsDepProvider -> this
    is Application -> error("Application must implements DetailsDepProvider")
    else -> applicationContext.detailsDepProvider
}
