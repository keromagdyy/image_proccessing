package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.GetCharactersFromDbUseCase
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.GetCharactersUseCase
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.SaveCharactersUseCase

class CharactersViewModelFactory(
    val app: Application,
    val getCharactersUseCase: GetCharactersUseCase,
    val saveCharactersUseCase: SaveCharactersUseCase,
    private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase

) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(app,getCharactersUseCase, saveCharactersUseCase,getCharactersFromDbUseCase) as T
    }

}