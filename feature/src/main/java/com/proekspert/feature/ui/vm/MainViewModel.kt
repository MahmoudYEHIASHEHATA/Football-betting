package com.proekspert.feature.ui.vm

import androidx.lifecycle.ViewModel
import com.proekspert.base.AppPreference
import com.proekspert.base.getValue
import com.proekspert.feature.core.SharedPreferencesConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val sharedPreference: AppPreference) : ViewModel() {

    fun isCurrentIsLastPresentScreen(navId: Int?): Boolean {
        return if (sharedPreference.isSaved(SharedPreferencesConstants.lastNavId)) {
            sharedPreference.sharedPreferences.getValue(
                SharedPreferencesConstants.lastNavId,
                -1
            ) == navId || sharedPreference.sharedPreferences.getValue(
                SharedPreferencesConstants.lastNavId,
                -1
            ) == -1
        } else true
    }

    fun getLastDestination(): Int {
        return sharedPreference.sharedPreferences.getValue(
            SharedPreferencesConstants.lastNavId,
            -1
        )
    }

    fun saveLastDestination(navId: Int?) {
        sharedPreference.save(
            SharedPreferencesConstants.lastNavId,
            navId ?: -1
        )
    }

}