package com.simpleviralgamesassingment.repository

import com.simpleviralgamesassingment.common.BaseRepository
import com.simpleviralgamesassingment.network.FormService
import com.simpleviralgamesassingment.utils.nullSafeErrorLogging
import java.lang.Exception

class GenerateDogRepository (
    private val formService: FormService
    ):  BaseRepository() {

    suspend fun generateDogImages(): Any {

        return try {
            return apiRequest { formService.generateDogImages() }
        } catch (ex: Exception) {
            nullSafeErrorLogging(ex.localizedMessage)
        }
    }
}