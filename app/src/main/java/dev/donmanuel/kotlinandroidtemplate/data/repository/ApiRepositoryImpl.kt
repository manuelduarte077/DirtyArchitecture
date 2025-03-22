package dev.donmanuel.kotlinandroidtemplate.data.repository

import dev.donmanuel.kotlinandroidtemplate.domain.repository.ApiRepository
import io.ktor.client.HttpClient

class ApiRepositoryImpl(
    private val httpClient: HttpClient
) : ApiRepository {

    companion object {
        const val baseUrl = "https://api.example.com"
    }

}