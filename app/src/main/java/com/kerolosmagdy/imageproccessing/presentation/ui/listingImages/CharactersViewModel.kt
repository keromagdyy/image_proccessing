package com.kerolosmagdy.imageproccessing.presentation.ui.listingImages

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.data.util.Common
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.GetCharactersFromDbUseCase
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.GetCharactersUseCase
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.SaveCharactersUseCase
import com.kerolosmagdy.imageproccessing.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersViewModel(
    val app: Application,
    private val getCharactersUseCase: GetCharactersUseCase,
    private val saveCharactersUseCase: SaveCharactersUseCase,
    private val getCharactersFromDbUseCase: GetCharactersFromDbUseCase
) : BaseViewModel() {
    val getCharactersMutableLiveData: MutableLiveData<CharactersModel> = MutableLiveData()
    val getCurrentCharactersMutableLiveData: MutableLiveData<CharactersModel> = MutableLiveData()

    fun getCharacters(offset: String): LiveData<CharactersModel> {

        viewModelScope.launch(Dispatchers.IO) {

            var characters = getCharactersFromDbUseCase.execute(offset)

            Log.d(Common.KeroDebug, "getCharacters: 0 ${characters}")

            if (characters == null) {
                saveCharactersUseCase.execute(offset)
                characters = getCharactersUseCase.execute(offset)
                Log.d(Common.KeroDebug, "getCharacters: 1 ${characters}")
            } else {
                if (isNetworkAvailable(app)) {
                    saveCharactersUseCase.execute(offset)
                    characters = getCharactersUseCase.execute(offset)
                    Log.d(Common.KeroDebug, "getCharacters: 2 ${characters}")
                } else {
                    characters = getCharactersFromDbUseCase.execute(offset)

                    Log.d(Common.KeroDebug, "getCharacters: 3 ${characters}")
                }
            }
            Log.d(Common.KeroDebug, "getCharacters: 4 ${characters}")
            getCharactersMutableLiveData.postValue(characters)
        }
        return getCharactersMutableLiveData
    }

    fun getCurrentCharacters(Characters_ID: String) = viewModelScope.launch(Dispatchers.IO) {
        val characters = getCharactersFromDbUseCase.execute(Characters_ID)

        characters.let {
            getCurrentCharactersMutableLiveData.postValue(it)
        }
    }

}