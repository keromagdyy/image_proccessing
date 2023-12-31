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

    fun getCharacters(offset:String) :LiveData<CharactersModel>{

    viewModelScope.launch(Dispatchers.IO) {

        val characters = getCharactersUseCase.execute(offset)


        if (characters != null){
            getCharactersMutableLiveData.postValue(characters)
        }else{
            if (isNetworkAvailable(app)){
                val characters = getCharactersUseCase.execute(offset)
                getCharactersMutableLiveData.postValue(characters)

            }else{
                Log.d("ahmed viewModel", "getCharacters:no internet")

            }
        }
    }
        return getCharactersMutableLiveData
    }
    fun getCurrentCharacters(Characters_ID: String) = viewModelScope.launch(Dispatchers.IO) {
        val characters = getCharactersFromDbUseCase.execute(Characters_ID)

        characters.let {
            getCurrentCharactersMutableLiveData.postValue(it)
        }
    }

    fun updateCharacters(Characters_ID:String)=  viewModelScope.launch(Dispatchers.IO) {
        if (isNetworkAvailable(app)){
//          val characters = saveCharactersUseCase.execute(Characters_ID)
//          saveCharactersMutableLiveData.value = characters
        }

    }

}