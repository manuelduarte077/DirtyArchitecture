package dev.donmanuel.weatherapp.data.mapper

import dev.donmanuel.weatherapp.data.model.entity.FavoriteCityEntity
import dev.donmanuel.weatherapp.domain.model.FavoriteCity

fun List<FavoriteCityEntity>.toFavoriteCityList() = map { it.toFavoriteCity() }

fun FavoriteCityEntity.toFavoriteCity() = FavoriteCity(
    city = city,
    country = country
)

fun List<FavoriteCity>.toFavoriteCityEntityList() = map { it.toFavoriteCityEntity() }

fun FavoriteCity.toFavoriteCityEntity() = FavoriteCityEntity(
    city = city,
    country = country
)
