package hr.soljic.mashdiary.feature.explore.data.repository

import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.explore.domain.model.FeaturedModel
import kotlinx.coroutines.flow.Flow

interface ExploreRepository {

    suspend fun getFeaturedItems(): Flow<Response<List<FeaturedModel>>>
}