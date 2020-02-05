package com.parthdesai1208.triviaapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    fun saveName(name: String) {
        _userName.value = name
    }

    private val _cricketer = MutableLiveData<String>()
    val cricketer: LiveData<String>
        get() = _cricketer

    fun saveCricketer(name: String) {
        _cricketer.value = name
    }

    private val _flagColor = MutableLiveData<String>()
    val flagColor: LiveData<String>
        get() = _flagColor

    fun saveFlagColor(name: String) {
        _flagColor.value = name
    }
}