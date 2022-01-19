package com.barros9.beerappkmm.di

import com.barros9.beerappkmm.data.network.BeerRemoteDataSourceImpl
import com.barros9.beerappkmm.data.repository.BeerRepositoryImpl
import com.barros9.beerappkmm.domain.usecase.GetBeersUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.kodein.di.with

//val KodeinContainer = DI {
//    DI.Module("NetworkModule") {
//        bind<HttpClient>() with instance(HttpClient(CIO) {
//            install(HttpTimeout) {
//                connectTimeoutMillis = 30_000
//                socketTimeoutMillis = 30_000
//            }
//            install(JsonFeature) {
//                serializer = KotlinxSerializer(
//                    Json {
//                        ignoreUnknownKeys = true
//                        isLenient = true
//                        encodeDefaults = false
//                    }
//                )
//            }
//        })
//        constant("remote-api-uri") with "https://api.punkapi.com/v2/"
//
//        bind { singleton { BeerRemoteDataSourceImpl(instance(), instance("remote-api-uri")) } }
//    }
//
//    DI.Module("RepositoryModule") {
//        bind { singleton { BeerRepositoryImpl(instance()) } }
//    }
//
//    // Use cases
//    bind<GetBeersUseCase>() with singleton { GetBeersUseCase(instance()) }
//}

val networkModule = DI.Module("NetworkModule") {
    bind<HttpClient>() with instance(HttpClient(CIO) {
        install(HttpTimeout) {
            connectTimeoutMillis = 30_000
            socketTimeoutMillis = 30_000
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = false
                }
            )
        }
    })
    constant("remote-api-uri") with "https://api.punkapi.com/v2/"

    bind { singleton { BeerRemoteDataSourceImpl(instance(), instance("remote-api-uri")) } }
}

val repositoryModule = DI.Module("RepositoryModule") {
    bind { singleton { BeerRepositoryImpl(instance()) } }
}

val useCasesModule = DI.Module("UseCasesModule") {
    bind<GetBeersUseCase>() with singleton { GetBeersUseCase(instance()) }
}