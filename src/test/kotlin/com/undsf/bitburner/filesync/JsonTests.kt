package com.undsf.bitburner.filesync

import com.google.gson.Gson
import com.undsf.bitburner.filesync.controllers.JsonRpcController
import com.undsf.bitburner.filesync.messages.jsonrpc.JsonRpcRequestBasic
import mu.KotlinLogging
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

typealias Request = JsonRpcRequestBasic

private val logger = KotlinLogging.logger {}

@SpringBootTest
class JsonTests {
    @Autowired
    lateinit var gson: Gson

    @Test
    fun testParse() {
        val json01 = """{"jsonrpc": "2.0", "method": "subtract", "params": [42, 23], "id": 1}"""
        val obj01: Request = gson.fromJson(json01, JsonRpcController.typeRequestBasic)
        logger.info { "method = ${obj01.method}" }

        val json02 = """{}"""
        val obj02: Request = gson.fromJson(json02, JsonRpcController.typeRequestBasic)
        logger.info { "method = ${obj02.method}" }
    }
}