package com.moodtunes.app.domain.local

import com.moodtunes.app.domain.model.CountryOption
import com.moodtunes.app.domain.model.GenderOption
import com.moodtunes.app.domain.model.Genre

object StaticData {


    val genderOptions = listOf(
        GenderOption("Male", "male"),
        GenderOption("Female", "female"),
        GenderOption("Other", "other"),
        GenderOption("Prefer not to say", "prefer_not_to_say"),
    )


    val BirthDayOptions = (1..31).map { it.toString() }

    val BirthMonthOptions = listOf(
        "January", "February", "March",
        "April", "May", "June",
        "July", "August", "September",
        "October", "November", "December",
    )

    val countries = listOf(
        CountryOption("Afghanistan", "AF", "🇦🇫"),
        CountryOption("Albania", "AL", "🇦🇱"),
        CountryOption("Algeria", "DZ", "🇩🇿"),
        CountryOption("Argentina", "AR", "🇦🇷"),
        CountryOption("Australia", "AU", "🇦🇺"),
        CountryOption("Austria", "AT", "🇦🇹"),
        CountryOption("Azerbaijan", "AZ", "🇦🇿"),
        CountryOption("Bahrain", "BH", "🇧🇭"),
        CountryOption("Bangladesh", "BD", "🇧🇩"),
        CountryOption("Belgium", "BE", "🇧🇪"),
        CountryOption("Brazil", "BR", "🇧🇷"),
        CountryOption("Canada", "CA", "🇨🇦"),
        CountryOption("Chile", "CL", "🇨🇱"),
        CountryOption("China", "CN", "🇨🇳"),
        CountryOption("Colombia", "CO", "🇨🇴"),
        CountryOption("Croatia", "HR", "🇭🇷"),
        CountryOption("Czech Republic", "CZ", "🇨🇿"),
        CountryOption("Denmark", "DK", "🇩🇰"),
        CountryOption("Egypt", "EG", "🇪🇬"),
        CountryOption("Ethiopia", "ET", "🇪🇹"),
        CountryOption("Finland", "FI", "🇫🇮"),
        CountryOption("France", "FR", "🇫🇷"),
        CountryOption("Germany", "DE", "🇩🇪"),
        CountryOption("Ghana", "GH", "🇬🇭"),
        CountryOption("Greece", "GR", "🇬🇷"),
        CountryOption("Hong Kong", "HK", "🇭🇰"),
        CountryOption("Hungary", "HU", "🇭🇺"),
        CountryOption("India", "IN", "🇮🇳"),
        CountryOption("Indonesia", "ID", "🇮🇩"),
        CountryOption("Iran", "IR", "🇮🇷"),
        CountryOption("Iraq", "IQ", "🇮🇶"),
        CountryOption("Ireland", "IE", "🇮🇪"),
        CountryOption("Israel", "IL", "🇮🇱"),
        CountryOption("Italy", "IT", "🇮🇹"),
        CountryOption("Japan", "JP", "🇯🇵"),
        CountryOption("Jordan", "JO", "🇯🇴"),
        CountryOption("Kenya", "KE", "🇰🇪"),
        CountryOption("Kuwait", "KW", "🇰🇼"),
        CountryOption("Lebanon", "LB", "🇱🇧"),
        CountryOption("Libya", "LY", "🇱🇾"),
        CountryOption("Malaysia", "MY", "🇲🇾"),
        CountryOption("Mexico", "MX", "🇲🇽"),
        CountryOption("Morocco", "MA", "🇲🇦"),
        CountryOption("Netherlands", "NL", "🇳🇱"),
        CountryOption("New Zealand", "NZ", "🇳🇿"),
        CountryOption("Nigeria", "NG", "🇳🇬"),
        CountryOption("Norway", "NO", "🇳🇴"),
        CountryOption("Oman", "OM", "🇴🇲"),
        CountryOption("Pakistan", "PK", "🇵🇰"),
        CountryOption("Palestine", "PS", "🇵🇸"),
        CountryOption("Philippines", "PH", "🇵🇭"),
        CountryOption("Poland", "PL", "🇵🇱"),
        CountryOption("Portugal", "PT", "🇵🇹"),
        CountryOption("Qatar", "QA", "🇶🇦"),
        CountryOption("Romania", "RO", "🇷🇴"),
        CountryOption("Russia", "RU", "🇷🇺"),
        CountryOption("Saudi Arabia", "SA", "🇸🇦"),
        CountryOption("Serbia", "RS", "🇷🇸"),
        CountryOption("Singapore", "SG", "🇸🇬"),
        CountryOption("South Africa", "ZA", "🇿🇦"),
        CountryOption("South Korea", "KR", "🇰🇷"),
        CountryOption("Spain", "ES", "🇪🇸"),
        CountryOption("Sri Lanka", "LK", "🇱🇰"),
        CountryOption("Sweden", "SE", "🇸🇪"),
        CountryOption("Switzerland", "CH", "🇨🇭"),
        CountryOption("Syria", "SY", "🇸🇾"),
        CountryOption("Taiwan", "TW", "🇹🇼"),
        CountryOption("Tanzania", "TZ", "🇹🇿"),
        CountryOption("Thailand", "TH", "🇹🇭"),
        CountryOption("Tunisia", "TN", "🇹🇳"),
        CountryOption("Turkey", "TR", "🇹🇷"),
        CountryOption("Ukraine", "UA", "🇺🇦"),
        CountryOption("United Arab Emirates", "AE", "🇦🇪"),
        CountryOption("United Kingdom", "GB", "🇬🇧"),
        CountryOption("United States", "US", "🇺🇸"),
        CountryOption("Venezuela", "VE", "🇻🇪"),
        CountryOption("Vietnam", "VN", "🇻🇳"),
        CountryOption("Yemen", "YE", "🇾🇪"),
        CountryOption("Zimbabwe", "ZW", "🇿🇼")
    )

    val genreList = listOf(
        Genre(id = "101", name = "K-Pop", emoji = "", description = ""),
        Genre(id = "102", name = "Romantic", emoji = "", description = ""),
        Genre(id = "103", name = "Classical", emoji = "", description = ""),
        Genre(
            id = "104", name = "Hollywood", emoji = "", description = ""
        )
    )
}