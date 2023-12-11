package hr.soljic.mashdiary.feature.explore.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.soljic.mashdiary.core.Response
import hr.soljic.mashdiary.feature.explore.data.repository.ExploreRepository
import hr.soljic.mashdiary.feature.explore.domain.model.FeaturedModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    val repository: ExploreRepository
) : ViewModel() {

    init {
        getFeaturedItems()
    }

    private val _featuredItems = MutableStateFlow<Response<List<FeaturedModel>>>(Response.Loading())
    val featuredResponse = _featuredItems.asStateFlow()


    private fun getFeaturedItems() {
        viewModelScope.launch {
            val result = repository.getFeaturedItems()
            result.collectLatest {
                _featuredItems.value = it
            }
        }
    }
}