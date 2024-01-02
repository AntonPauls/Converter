package com.example.testingex.di

import androidx.fragment.app.DialogFragment
import com.example.testingex.data.CurrencyApi
import com.example.testingex.main.DefaultMainRepository
import com.example.testingex.main.MainRepository
import com.example.testingex.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// В этот обьекте мы поместим функцию которые создают зависимости, которые мы хотим внедрить
// И этот модуль будет содержать одиночные елементы нашего приложения
// Поэтому те зависимости которые существуют до тех пор пока работает наше приложение

private const val BASE_URL = "https://developers.paysera.com/"

@Module
// Чтобы сообщить Doggerhild, что это модуль
@InstallIn(SingletonComponent::class)
// Что мы ххотим установить этот модуль в компонент приложения
// И мы говорим зависимости живут до тех пор пока приложение
object AppModule : DialogFragment() {
    // Пишем функцию которая создает наше API
    // Это означает что у нас есть только один екземпляр на протяжении всего срока службы нашего приложения
    @Singleton
    // Мы предоставляем этот екземпляр чтобы мы могли его внедрить
    @Provides
    // будет вовращать валюты, поэтому наш интерфейс можем установить его равным Retrofit.Builder()
    // который нам нужен, чтобы установить базовый URL
    fun provideCurrencyApi(
    ): CurrencyApi {
        return Retrofit.Builder()
        // Создание константы BASE_URL, для того чтобы мы могли создать отдельный файл
            .baseUrl(BASE_URL)
            // Затем нужно добавить addConverterFactory() которая будет отвечать за преобразования ответа json в наши классы данных
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyApi::class.java)
    }


    @Singleton
    @Provides
    fun provideMainRepository(api: CurrencyApi): MainRepository {
        return DefaultMainRepository(api)
    }

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider {
        return object : DispatcherProvider {
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
            override val unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined
        }
    }
}
