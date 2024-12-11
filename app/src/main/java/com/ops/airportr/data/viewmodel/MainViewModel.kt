package com.ops.airportr.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ops.airportr.route.sidenavigationscreens.SideNavigationScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    ViewModel() {
    private val _currentScreen =
        MutableLiveData<SideNavigationScreens>(SideNavigationScreens.SideDrawerScreens.Home)
    val currentScreen: LiveData<SideNavigationScreens> = _currentScreen

    fun setCurrentScreen(screen: SideNavigationScreens) {
        _currentScreen.value = screen
    }

    private val _clickCount = MutableLiveData(0)
    val clickCount: LiveData<Int> = _clickCount

    fun updateClick(value: Int) {
        _clickCount.value = value
    }
}