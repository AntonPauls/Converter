package com.example.testingex.main

import com.example.testingex.data.CurrencyApi
import com.example.testingex.data.models.CurrencyResponse
import com.example.testingex.util.Resource
import javax.inject.Inject

/*  И здесь в основном репезитории по умолчанию нам нужно экземпляр нашего валютного API
    который мы получаем из нашего модуля приложения AppModule c помощью функции CurrencyApi и создаем экземпляр
    мы можем просто внедрить его с помощью рукоятки кинжала*/
class DefaultMainRepository @Inject constructor(
    // тогда рукоять кинжала увидит что мы хотим внедрить сюда обьект типа CurrencyApi
    private val api: CurrencyApi
): MainRepository{
    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        /* В try блоке мы хотим сделать наш фактический запрос сейчас поэтому мы сохраняем ответ в переменной запроса response
            котрая представляет собой api.getRates в которую мы передаем базу */
        return try {
            val response = api.getRates(base)
            /* И результатом этого ответа явкляеться то, что мы это также можем сохранить в переменной -
                это тело, которое мы можем видеть, которое возвращает ответ валюты, которое имее значение NULL
                Результат содержит все интересующие нас ресурсы*/
            val result = response.body()
            if (response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }
        }catch (e : Exception) {
            Resource.Error(e.message ?: "An error occured")
        }
    }
}