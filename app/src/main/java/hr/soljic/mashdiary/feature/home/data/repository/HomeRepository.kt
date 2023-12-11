package hr.soljic.mashdiary.feature.home.data.repository

import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.home.domain.model.DateNightSuggestion
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getDateNightRequests(): Flow<Response<DateNightSuggestion>>
}