package com.undsf.bitburner.filesync.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.undsf.bitburner.filesync.messages.jsonrpc.JsonRpcError
import com.undsf.bitburner.filesync.messages.jsonrpc.JsonRpcRequestBasic
import com.undsf.bitburner.filesync.messages.jsonrpc.JsonRpcResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.lang.reflect.Type

open class JsonRpcController {
    @Autowired
    lateinit var gson: Gson

    @PostMapping("")
    fun handle(@RequestBody body: String) : JsonRpcResponse<*> {
        val request: JsonRpcRequestBasic = gson.fromJson(body, typeRequestBasic)

        var error: JsonRpcError<*>? = null
        var result: Any? = null
        do {
            if (request.method == null) {
                error = JsonRpcError<Void>(JsonRpcError.CodeMethodNotFound)
                break
            }

            result = handleByMethod(request.method, body)
            if (result is JsonRpcError<*>) {
                error = result
                result = null
            }
        } while (false)

        return JsonRpcResponse(
                result = result,
                error = error,
                id = request.id
        )
    }

    open fun handleByMethod(method: String, reqJson: String) : Any? {
        return null
    }

    companion object {
        val typeRequestBasic: Type = object : TypeToken<JsonRpcRequestBasic>(){}.type
    }
}