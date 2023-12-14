package hr.soljic.mashdiary.feature.explore.domain.use_case

import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.explore.data.repository.ExploreRepository
import hr.soljic.mashdiary.feature.explore.domain.model.FeaturedModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFeaturedItemsUseCase @Inject constructor(
    val repository: ExploreRepository
) {

    suspend fun getFeaturedItems(): Flow<Response<List<FeaturedModel>>> {
        val result = repository.getFeaturedItems()

        return result.map { Response.Success(data = it) }

    }
}