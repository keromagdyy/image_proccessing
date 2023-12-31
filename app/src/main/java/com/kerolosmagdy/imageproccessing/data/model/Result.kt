package com.kerolosmagdy.imageproccessing.data.model

import com.kerolosmagdy.imageproccessing.domain.model.Character
import java.io.Serializable

data class Result(
//    val comics: Comics,
    val description: String,
//    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
//    val series: Series,
//    val stories: Stories,
//    val thumbnail: Thumbnail,
//    val urls: List<Url>
): Serializable {
//    fun toCharacter(): Character {
//        return Character(
//            id = id,
//            name = name,
//            description = description,
//            thumbnail = thumbnail.path,
//            thumbnailExt = thumbnail.extension,
//            comics = comics.items.map {
//                it.name
//            }
//        )
//    }
}