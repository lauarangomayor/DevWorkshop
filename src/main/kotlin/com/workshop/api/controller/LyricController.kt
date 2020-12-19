package com.workshop.api.controller

import com.workshop.api.service.LyricService
import com.workshop.api.dto.lyric.LyricTranslateRequestDto
import com.workshop.api.dto.lyric.LyricTranslateResponseDto
import com.workshop.api.dto.lyric.LyricStatsRequestDto
import com.workshop.api.dto.lyric.LyricStatsResponseDto
import com.workshop.api.dto.translate.TranslateRequestDto
import com.workshop.api.dto.translate.TranslateResponseDto

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest
import java.security.Principal
import java.util.Date
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CrossOrigin
@RestController
class LyricController (private val lyricService: LyricService) {
    companion object {
        private val LOG = LoggerFactory.getLogger(LyricController::class.java)
    }

    @PostMapping(
        value = ["/api/translate"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )    
    fun translate(@RequestBody lyricTranslateRequestDto: LyricTranslateRequestDto): ResponseEntity<LyricTranslateResponseDto>{
        val apiToken = "a_ItVQVpy8n2JpN7SO382k7W0vMPV3O27WE9QQ0PXkAAAPDX9HEHRS5VJmcPuOPGzZoH2U0PglLUVbv2W0"
        val translateResponseDto = lyricService.translate(TranslateRequestDto("en_GB", 
                                                                                "es_CO", 
                                                                                lyricTranslateRequestDto.lyrics,
                                                                                "api"), apiToken)
        if (translateResponseDto.err != null){
            LOG.info("Successful translation")
            return ResponseEntity<LyricTranslateResponseDto>(LyricTranslateResponseDto(translateResponseDto.sourceTransliteration,
                                                                                       translateResponseDto.targetTransliteration),
                                                             HttpStatus.OK)
        }
        else{
            val message = "Translation failed" 
            LOG.error(message)
            return ResponseEntity<LyricTranslateResponseDto>(null,
                                            HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping(
        value = ["/api/getstats"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )    
    fun getStats(@RequestBody lyricStatsRequestDto: LyricStatsRequestDto): ResponseEntity<LyricStatsResponseDto>{
        val words = lyricStatsRequestDto.split(" ")
                                        .dropLastWhile { it.isEmpty() }
                                        .toTypedArray().size - 2
        val lines = lyricStatsRequestDto.split("\n")
                                        .dropLastWhile { it.isEmpty() }
                                        .toTypedArray().size - 1

        return ResponseEntity<LyricStatsResponseDto>(LyricStatsResponseDto(wordsm lines),
                                                     HttpStatus.OK)
    }
} 