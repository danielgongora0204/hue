package com.gig.hue.view_models

import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.lifecycle.viewModelScope
import com.gig.hue.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(): BaseViewModel() {

    //Public
    val toolbarTittle by lazy { _toolbarTittle.asStateFlow() }
    val currentFragment by lazy { _currentFragment.asStateFlow() }
    val fabButtonVisible by lazy { _fabButtonVisible.asStateFlow() }

    //PrivateFlow
    private val _toolbarTittle = MutableStateFlow(R.string.search_rent_toolbar_tittle)
    private val _currentFragment = MutableStateFlow<MenuItem?>(null)
    private val _fabButtonVisible = MutableStateFlow(false)

    fun onBottomMenuClicked(@NonNull item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.main_unassigned_search -> {
                viewModelScope.launch {
                    _toolbarTittle.emit(R.string.search_rent_toolbar_tittle)
                    _currentFragment.emit(item)
                    _fabButtonVisible.emit(false)
                }
                true
            }
            R.id.main_unassigned_inbox -> {
                viewModelScope.launch {
                    _toolbarTittle.emit(R.string.inbox_toolbar_tittle)
                    _currentFragment.emit(item)
                    _fabButtonVisible.emit(true)
                }
                true
            }
            R.id.main_unassigned_profile -> {
                viewModelScope.launch {
                    _toolbarTittle.emit(R.string.profile_toolbar_tittle)
                    _currentFragment.emit(item)
                    _fabButtonVisible.emit(false)
                }
                true
            }
            else -> false
        }


}