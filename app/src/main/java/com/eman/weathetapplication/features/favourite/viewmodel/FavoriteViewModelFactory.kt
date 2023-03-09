package com.eman.weathetapplication.features.favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eman.weathetapplication.data.repository.RepositoryInterface

class FavoriteViewModelFactory(val repo: RepositoryInterface): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(repo) as T
    }
}