package hr.soljic.mashdiary.feature.explore.domain.model

import com.google.gson.annotations.SerializedName
import hr.soljic.mashdiary.feature.home.domain.model.ItemType

//vidit sto cemo s ovim, nezz jel ovo pametno samo ovdje drzat ovako
data class FeaturedResponse(
    @SerializedName("featured") val featured: List<FeaturedModel>
)

data class FeaturedModel(
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: ItemType,
    @SerializedName("data") val data: List<ExploreItemModel>
)


data class ExploreItemModel(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("dateOfRelease") val dateOfRelease: String,
    @SerializedName("coverPhoto") val coverPhoto: String,
    @SerializedName("id") val id: String,

    )

