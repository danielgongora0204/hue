package com.gig.playground

import kotlin.Exception

fun main() {
    getResult(Repository.loadStatus)
    Repository.startFetch()
    getResult(Repository.loadStatus)
    Repository.finishFetch()
    getResult(Repository.loadStatus)
}

fun getResult(result: Result){
    return when(result){
        is Error -> {
            println(result.exception.toString())
        }
        is Success -> println(result.dataFetched?: "Ensure you start the fetch function first")
        is Loading -> println("Is Loading")
        else -> println("Idle")
    }
}

abstract class Result

data class Success(val dataFetched: String?): Result()
data class Error(val exception: Exception): Result()
object Loading: Result()
object Idle: Result()


object Repository {

    private var _loadStatus: Result = Idle
    private var _dataFetched: String? = null

    val loadStatus get() = _loadStatus

    fun startFetch(){
        _loadStatus = Loading
        _dataFetched = "Hello"
    }

    fun finishFetch() {
        _loadStatus = Success(_dataFetched)
        _dataFetched = null
    }

    fun error(){
        _loadStatus = Error(exception = Exception("Exception"))
    }

}