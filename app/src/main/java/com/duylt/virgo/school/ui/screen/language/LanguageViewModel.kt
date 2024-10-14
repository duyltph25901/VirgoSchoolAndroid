package com.duylt.virgo.school.ui.screen.language

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duylt.virgo.school.R
import com.duylt.virgo.school.model.LanguageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LanguageViewModel: ViewModel() {

    private val _languages = MutableLiveData<MutableList<LanguageModel>>()

    val languages: LiveData<MutableList<LanguageModel>> = _languages

    fun fetchLanguage() = viewModelScope.launch(Dispatchers.IO) {
        _languages.postValue(mutableListOf(
            LanguageModel("English", "en",  R.drawable.ic_english),
            LanguageModel("Portuguese", "pt",  R.drawable.ic_portugal),
            LanguageModel("French", "fr",  R.drawable.ic_france),
            LanguageModel("Spanish", "es",  R.drawable.ic_spanish),
            LanguageModel("Hindi", "hi",  R.drawable.ic_india),
        ))
        cancel()
    }

}