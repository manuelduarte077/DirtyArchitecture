package dev.donmanuel.kotlinandroidtemplate.utils

class NoNetworkException(message: String = "No network connection available") : Exception(message)

class RemoteDataSourceException(message: String) : Exception(message)