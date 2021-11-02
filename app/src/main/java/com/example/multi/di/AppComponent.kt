package com.example.multi.di

import android.app.Application
import com.example.multi.details.DetailsDep
import com.example.multi.retrofitapi.MainService
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent: DetailsDep {

    override val mainService:MainService

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(app: Application):Builder

        fun build():AppComponent
    }
}

@Module
class AppModule{

    @Provides
    @AppScope
    fun providesMainService():MainService{
        return MainService()
    }

}

@Scope
internal annotation class AppScope