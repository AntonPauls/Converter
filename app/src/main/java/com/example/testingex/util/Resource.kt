package com.example.testingex.util

    // Общий класс который принимает данные типа T со значение NULL, либо в случаии ошибки просто прекрепить сообщение
    // В случаии если у нас есть успешный ресурс мы просто прекрепляем данные без соощения, в случаии ошибки прикрепляем сообщение но нет данных.
    // И этот класс запечатаный потому что здесь у нас будет класс успеха еоторый принимает данные не допускающий значение  NULL
    /* Выгода от этого сейчас состоит в том, что если мы вернемся в наш MainRepository там мы сможем использовать класс Resource о обернуть его вокруг
        CurrencyResponse этого ответа валют чтобы таким образом мы могли проверить модель представления бил ли ответ валют успешным или нет.
         И мы также можем определить что пошло не так с нашем сетевым запросом полезно имень класс Resource */
sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T): Resource<T>(data, null)
        class Error<T>(message: String): Resource<T>(null, message)
}