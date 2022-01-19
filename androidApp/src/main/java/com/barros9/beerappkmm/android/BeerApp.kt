package com.barros9.beerappkmm.android

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import com.barros9.beerappkmm.di.networkModule
import com.barros9.beerappkmm.di.repositoryModule
import com.barros9.beerappkmm.di.useCasesModule

class BeerApp: Application(), DIAware {
    override val di by DI.lazy {
        import(androidXModule(this@BeerApp))
        importAll(networkModule, repositoryModule, useCasesModule)
    }
}