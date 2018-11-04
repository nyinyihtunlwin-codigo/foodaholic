package com.nyinyihtunlwin.projects.foodaholic.di

import com.nyinyihtunlwin.projects.foodaholic.mvvm.viewmodels.CategoryViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(categoryViewModel: CategoryViewModel)
}