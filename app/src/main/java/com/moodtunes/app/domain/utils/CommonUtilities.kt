package com.moodtunes.app.domain.utils

import com.moodtunes.app.domain.local.StaticData
import com.moodtunes.app.domain.local.StaticData.countries
import com.moodtunes.app.domain.model.CountryOption
import com.moodtunes.app.domain.model.GenderOption

object CommonUtilities {

    fun String.toGenderDisplay(): String {
        return StaticData.genderOptions.find { it.apiValue == this }?.display ?: this
    }

    // Get display name from code — used when receiving from API
    // e.g. "MY" → "🇲🇾 Malaysia"
    fun getDisplayByCode(code: String): String {
        val country = countries.find { it.code == code }
        return if (country != null) "${country.flag} ${country.name}" else code
    }

    // Get code from name — used when sending to API
    // e.g. "Malaysia" → "MY"
    fun getCodeByName(name: String): String? {
        return countries.find { it.name == name }?.code
    }

    // Get CountryOption by code
    fun getByCode(code: String): CountryOption? {
        return countries.find { it.code == code }
    }
}