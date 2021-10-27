package com.example.productlisting.data.repository.products.di

import com.example.productlisting.data.repository.products.remote.service.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ProductsModule {

    @Provides
    fun provideCountryService(retrofit: Retrofit): ProductsService =
        retrofit.create(ProductsService::class.java)

}