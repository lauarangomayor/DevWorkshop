package com.workshop.api.dto.translate

data class TranslateRequestDto( val from : String,
                                val to : String,
                                val data : String,
                                val platform : String)