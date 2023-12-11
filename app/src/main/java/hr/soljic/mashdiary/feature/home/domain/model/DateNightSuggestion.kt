package hr.soljic.mashdiary.feature.home.domain.model

import com.google.gson.annotations.SerializedName

data class DateNightSuggestion(
    @SerializedName("itemId") val id: String,
    @SerializedName("itemTitle") val title: String,
    @SerializedName("itemCoverPhoto") val coverPhoto: String,
    @SerializedName("dateTime") val dateTime: String
)
