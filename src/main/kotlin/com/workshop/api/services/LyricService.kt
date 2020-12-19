package com.workshop.api.service

import org.springframework.stereotype.Service
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory

import com.workshop.api.dto.lyric.LyricTranslateRequestDto
import com.workshop.api.dto.lyric.LyricTranslateResponseDto
import com.workshop.api.dto.translate.TranslateRequestDto
import com.workshop.api.dto.translate.TranslateResponseDto

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable
import java.util.*

@FeignClient(name = "translateFeignClient", url="https://api-b2b.backenster.com/b1/api/v3/translate")
interface LyricService {
    @PostMapping(
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun translate(@RequestBody translateRequestDto : TranslateRequestDto,
                  @RequestHeader(value="Authorization") authorization: String) : TranslateResponseDto
}
