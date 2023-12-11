package hr.soljic.mashdiary.feature.home.data.repository

import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.home.domain.model.DateNightSuggestion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl: HomeRepository {
    override suspend fun getDateNightRequests(): Flow<Response<DateNightSuggestion>> {
        return flow {

        }
    }

}