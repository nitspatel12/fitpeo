package com.fitpeo.assignment.di

import com.fitpeo.assignment.view.DetailFragment
import com.fitpeo.assignment.view.HomeFragment
import com.fitpeo.assignment.view.MainActivity
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(homeFragment: HomeFragment)

    fun inject(detailFragment: DetailFragment)
}