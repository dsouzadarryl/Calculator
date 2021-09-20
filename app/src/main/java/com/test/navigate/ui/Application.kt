package com.test.navigate.ui

import android.app.Application
import com.test.navigate.ui.viewModels.CalculatorViewModel
import com.test.navigate.ui.viewModels.Mx51ViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Application : Application(){
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@Application)
            modules(
                module {
                    viewModel { CalculatorViewModel() }
                    viewModel { Mx51ViewModel() }
                }
            )
        }
    }
}