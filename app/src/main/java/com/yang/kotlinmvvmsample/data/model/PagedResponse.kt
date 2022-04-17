package com.yang.kotlinmvvmsample.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PagedResponse<T>(
    @SerializedName("info") val pageInfo: PageInfo,
    val results: List<T> = listOf()
)

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

/**
 * list data
 */
@Parcelize
data class Character (
    val id: Long,
    val name: String,
    val status: Status,
    val species: Species,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
) : Parcelable

enum class Status(val status: String) {
    @SerializedName(value = "Alive", alternate = ["alive"])
    ALIVE("Alive"),

    @SerializedName(value = "Dead", alternate = ["dead"])
    DEAD("Dead"),

    @SerializedName(value = "unknown", alternate = ["Unknown"])
    UNKNOWN("Unknown");

    override fun toString() = status
}

enum class Species(val species: String) {
    @SerializedName(value = "Alien", alternate = ["alien"])
    Alien("Alien"),

    @SerializedName(value = "Human", alternate = ["human"])
    Human("Human");

    override fun toString() = species
}

enum class Gender(val gender: String) {
    @SerializedName(value = "Female", alternate = ["female"])
    FEMALE("Female"),

    @SerializedName(value = "Male", alternate = ["male"])
    MALE("Male"),

    @SerializedName(value = "unknown", alternate = ["Unknown"])
    UNKNOWN("Unknown");

    override fun toString() = gender
}

@Parcelize
data class Location (
    val name: String,
    val url: String
) : Parcelable
