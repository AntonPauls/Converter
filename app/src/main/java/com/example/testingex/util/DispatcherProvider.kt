package com.example.testingex.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/* Это нам помогает в том что для нашего реального приложения мы можем передать основной диспечер для перечисленных ниже
    и для нашего тестирования модель представления, поэтому для моделей которые у нас есть в наших тестовых
    случаях, мы можем передать диспечер тестов для всех из них, чтобы мы могли быть уверенны, что модель тестов
    выбирает диспечер тестов для всех тестовых случаев и олько обычные диспечеры для нашего реального приложения*/
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

}