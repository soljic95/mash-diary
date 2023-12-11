package hr.soljic.mashdiary.feature.explore.data.data_source.network

import hr.soljic.mashdiary.feature.explore.domain.model.FeaturedResponse
import retrofit2.Response
import retrofit2.http.GET

interface ItemsService {

    @GET("getItems")
    fun getFeaturedItems(): Response<FeaturedResponse>
}