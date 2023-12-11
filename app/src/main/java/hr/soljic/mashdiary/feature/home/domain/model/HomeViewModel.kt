package hr.soljic.mashdiary.feature.home.domain.model

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.soljic.mashdiary.core.Constants.TAG
import hr.soljic.mashdiary.feature.home.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: HomeRepository
) : ViewModel() {

    init {
        getDateNightRequests()
    }

    private fun getDateNightRequests() {
        viewModelScope.launch {
            val result = repository.getDateNightRequests()
        }
    }


}