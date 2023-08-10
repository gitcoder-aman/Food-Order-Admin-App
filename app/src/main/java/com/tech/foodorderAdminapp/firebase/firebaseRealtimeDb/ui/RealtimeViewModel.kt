package com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.RealtimeModelResponse
import com.tech.foodorderAdminapp.firebase.firebaseRealtimeDb.repository.RealtimeRepository
import com.tech.foodorderAdminapp.firebase.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//contact this code in 2nd and first is UI screen
@HiltViewModel
class RealtimeViewModel @Inject constructor(
    private val repo: RealtimeRepository
) : ViewModel() {

    private val _res: MutableState<ItemState> = mutableStateOf(ItemState())
    val res: State<ItemState> = _res  //observe in ui for taking res

    //insert data
    fun insert(items: RealtimeModelResponse.RealtimeItems) = repo.insert(items)

    private val _updateRes : MutableState<RealtimeModelResponse> = mutableStateOf(
        RealtimeModelResponse(items = RealtimeModelResponse.RealtimeItems())
    )
    val updateRes : State<RealtimeModelResponse> = _updateRes //observe in ui

    fun setData(data : RealtimeModelResponse){
        _updateRes.value = data
    }

    //get data
    init {
        viewModelScope.launch {
            repo.getItems().collect {
                when (it) {

                    is ResultState.Success -> {
                        _res.value = ItemState(
                            item = it.data
                        )
                    }

                    is ResultState.Failure -> {
                        _res.value = ItemState(
                            error = it.msg.toString()
                        )
                    }

                    is ResultState.Loading -> {
                        _res.value = ItemState(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    //delete data
    fun delete(key : String) =  repo.delete(key)

    //update data
    fun update(item : RealtimeModelResponse) = repo.update(item)
}

data class ItemState(
    val item: List<RealtimeModelResponse> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)