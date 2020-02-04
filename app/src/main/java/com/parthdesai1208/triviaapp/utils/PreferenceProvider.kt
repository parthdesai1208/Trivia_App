package com.parthdesai1208.triviaapp.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.parthdesai1208.triviaapp.utils.CRICKETER_KEY
import com.parthdesai1208.triviaapp.utils.FLAG_COLOR_KEY
import com.parthdesai1208.triviaapp.utils.IS_FINISH_REACH_key
import com.parthdesai1208.triviaapp.utils.NAME_KEY

class PreferenceProvider(context: Context) {
    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun saveName(name: String) {
        preference.edit().putString(
            NAME_KEY,
            name
        ).apply()
    }

    fun getName(): String? {
        return preference.getString(NAME_KEY, "...")
    }

    fun saveCricketer(cricketer: String) {
        preference.edit().putString(
            CRICKETER_KEY,
            cricketer
        ).apply()
    }

    fun getCricketer(): String? {
        return preference.getString(CRICKETER_KEY, "...")
    }

    fun saveFlagColor(cricketer: String) {
        preference.edit().putString(
            FLAG_COLOR_KEY,
            cricketer
        ).apply()
    }

    fun getFlagColor(): String? {
        return preference.getString(FLAG_COLOR_KEY, "...")
    }

    fun saveIsFinishReach(b: Boolean) {
        preference.edit().putBoolean(
            IS_FINISH_REACH_key,
            b
        ).apply()
    }

    fun getIsFinishReach(): Boolean? {
        return preference.getBoolean(IS_FINISH_REACH_key, false)
    }

}