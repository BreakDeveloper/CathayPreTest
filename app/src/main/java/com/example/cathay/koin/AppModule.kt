package com.example.cathay.koin

import androidx.room.Room
import com.example.cathay.data.respository.PlantApiRepository
import com.example.cathay.data.respository.RegionDataRepository
import com.example.cathay.data.room.RegionDatabase
import com.example.cathay.network.PlantApi
import com.example.cathay.network.UnsafeSSLConnection
import com.example.cathay.ui.detail.DetailViewModel
import com.example.cathay.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}

inline fun <reified T> provideApi(client: OkHttpClient, url: String): T {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(T::class.java)
}

const val URL_TAIPEI = "https://data.taipei"

val networkModule = module {
    // OkHttpClient
    single {
        val builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
        UnsafeSSLConnection.setSslSocketFactory(builder)
        builder.build()
    }

    single { provideApi<PlantApi>(get(), URL_TAIPEI) }
}

val repositoryModule = module {
    factory { PlantApiRepository(get()) }
    factory { RegionDataRepository(get()) }
}

val dbModule = module {
    single {
        Room.databaseBuilder(get(), RegionDatabase::class.java, "RegionTable.db").build()
    }
    factory { get<RegionDatabase>().regionDao() }
}

val appModule = listOf(dbModule, repositoryModule, networkModule, viewModelModule)