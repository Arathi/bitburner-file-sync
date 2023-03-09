package com.undsf.bitburner.filesync.configs

import com.google.gson.Gson
import org.springframework.context.annotation.Bean

class JsonConfiguration {
    @Bean
    fun createGson() : Gson {
        return Gson()
    }
}