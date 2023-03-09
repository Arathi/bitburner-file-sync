package com.undsf.bitburner.filesync.controllers

import com.google.gson.reflect.TypeToken
import com.undsf.bitburner.filesync.exceptions.JsonRpcException
import com.undsf.bitburner.filesync.messages.jsonrpc.JsonRpcError
import com.undsf.bitburner.filesync.messages.jsonrpc.JsonRpcRequest
import mu.KotlinLogging
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("")
class BitBurnerRemoteApiController : JsonRpcController() {
    override fun handleByMethod(method: String, reqJson: String) : Any? {
        return try {
            val response: Any? = when (method) {
                "pushFile" -> pushFile(reqJson)
                else -> JsonRpcError<Void>(JsonRpcError.CodeMethodNotFound)
            }
            response
        }
        catch (ex: JsonRpcException) {
            ex.error
        }
        catch (_: Exception) {
            JsonRpcError<Void>(JsonRpcError.CodeInternalError)
        }
    }

    data class PushFileRequestParams(
            val filename: String? = null,
            val content: String? = null,
            val server: String? = null
    )

    fun pushFile(reqJson: String) : String {
        val type = object : TypeToken<JsonRpcRequest<PushFileRequestParams>>(){}.type
        val request: JsonRpcRequest<PushFileRequestParams> = gson.fromJson(reqJson, type)
        val params = request.params
        if (params == null) {
            val error = JsonRpcError<Void>(JsonRpcError.CodeInvalidParams)
            throw JsonRpcException(error)
        }
        logger.info { "接收到推送文件，服务器：${params.server}，文件名：${params.filename}，内容：${params.content}" }
        return "OK"
    }
}