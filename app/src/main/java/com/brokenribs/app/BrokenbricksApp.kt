package com.brokenribs.app
import android.app.Application
import com.brokenribs.app.data.db.AppDatabase
import com.brokenribs.app.data.network.BrokenAPI
import com.brokenribs.app.data.network.NetworkConnectionInterceptor
import com.brokenribs.app.data.repositories.UserRepository
import com.brokenribs.app.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class BrokenbricksApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@BrokenbricksApp))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }

        bind() from singleton { BrokenAPI(instance()) }

        bind() from singleton { AppDatabase(instance()) }

        bind() from singleton { UserRepository(instance(), instance()) }

        bind() from provider { AuthViewModelFactory(instance()) }
    }


}