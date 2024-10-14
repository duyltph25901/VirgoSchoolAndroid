package com.duylt.virgo.school.ui.screen.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duylt.virgo.school.R
import com.duylt.virgo.school.model.OnboardingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class OnboardingViewModel: ViewModel() {

    private val _introList = MutableLiveData<MutableList<OnboardingModel>>()

    val introList: LiveData<MutableList<OnboardingModel>> = _introList

    fun fetchDataIntro() = viewModelScope.launch(Dispatchers.IO) {
        _introList.postValue(mutableListOf<OnboardingModel>().apply {
            add(OnboardingModel(R.drawable.ob_1, R.string.ob_title_1, R.string.ob_des_1))
            add(OnboardingModel(R.drawable.ob_2, R.string.ob_title_2, R.string.ob_des_2))
            add(OnboardingModel(R.drawable.ob_3, R.string.ob_title_3, R.string.ob_des_3))
        })
        cancel("Completed fetch all onboarding")
    }


}