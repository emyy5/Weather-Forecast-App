package com.eman.weathetapplication.features.setting.viewmodel

import androidx.lifecycle.ViewModel
import com.eman.weathetapplication.data.model.Settings
import com.eman.weathetapplication.data.repository.RepositoryInterface


class SettingViewModel (private val repo: RepositoryInterface): ViewModel(){
    fun setSettingsSharedPrefs(settings: Settings){
        repo.addSettingsToSharedPreferences(settings)
    }
    fun getStoredSettings(): Settings?{
        return repo.getSettingsSharedPreferences()
    }
}