package com.workshop.api.dto.translate

data class TranslateResponseDto( val err : String,
                                val result : String,
                                val cacheUse : Int,
                                val source : String,
                                val from : String,
                                val sourceTransliteration : String,
                                val targetTransliteration : String)