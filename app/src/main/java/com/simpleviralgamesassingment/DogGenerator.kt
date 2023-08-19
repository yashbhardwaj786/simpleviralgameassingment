package com.simpleviralgamesassingment

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import android.os.Bundle
import com.simpleviralgamesassingment.data.preferences.PreferenceProvider
import com.simpleviralgamesassingment.repository.GenerateDogRepository
import com.simpleviralgamesassingment.ui.viewmodelfactory.MainViewModelFactory
import com.simpleviralgamesassingment.network.NetworkConnectionInterceptor
import com.simpleviralgamesassingment.network.NetworkModule
import com.simpleviralgamesassingment.ui.viewmodelfactory.GenerateDogImageModelFactory
import com.simpleviralgamesassingment.ui.viewmodelfactory.ViewGeneratedImageModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class DogGenerator :
    Application(),
    KodeinAware,
    Application.ActivityLifecycleCallbacks,
    ComponentCallbacks2 {

    operator fun get(context: Context): DogGenerator {
        return context.applicationContext as DogGenerator
    }

    companion object {

        private lateinit var application: DogGenerator
        @JvmStatic
        fun getInstance(): DogGenerator {
            return application
        }

    }

    override fun onCreate() {
        super.onCreate()
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@DogGenerator))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from provider { GenerateDogRepository(NetworkModule.formService) }
        bind() from provider { MainViewModelFactory() }
        bind() from provider { GenerateDogImageModelFactory(instance()) }
        bind() from provider { ViewGeneratedImageModelFactory(instance()) }
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onActivityStarted(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityResumed(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityPaused(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivityStopped(p0: Activity) {
        TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(p0: Activity) {
        TODO("Not yet implemented")
    }
}